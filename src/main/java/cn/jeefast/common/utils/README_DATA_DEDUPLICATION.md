# 数据脱敏与去重功能

本文档描述了JeeFast框架中新增的数据脱敏与去重功能，该功能可以确保前端展示的数据没有已经加载出的重复数据。

## 功能概述

在爬取数据传输到前端时，可能会出现重复数据的情况，这会导致用户体验下降。数据脱敏与去重功能通过以下方式解决这个问题：

1. 跟踪已经发送到前端的数据项
2. 在后续请求中过滤掉已经发送过的数据项
3. 确保前端只展示新的、未曾展示过的数据

## 核心组件

### 1. DataDeduplicationService

数据去重服务接口，定义了数据去重的核心方法：

```java
public interface DataDeduplicationService {
    <T> List<T> filterDuplicates(Collection<T> items, Function<T, String> idExtractor);
    <T> List<T> filterAndRegisterItems(Collection<T> items, Function<T, String> idExtractor, String sessionId);
    void clearCache(String sessionId);
    void clearAllCaches();
}
```

### 2. DataDeduplicationServiceImpl

数据去重服务的实现类，使用内存缓存来跟踪已发送的数据项：

- 使用ConcurrentHashMap存储已发送数据的ID
- 支持按会话隔离数据去重缓存
- 提供缓存过期和清理机制
- 支持缓存大小限制和自动清理

### 3. PageDeduplicationUtil

用于对MyBatis-Plus的Page对象进行去重处理的工具类：

```java
public static <T> Page<T> deduplicatePage(
        Page<T> page, 
        DataDeduplicationService deduplicationService,
        Function<T, String> idExtractor,
        String sessionId)
```

### 4. DeduplicationBaseController

提供数据去重功能的基础控制器，其他控制器可以继承此类以获得数据去重能力：

```java
public class DeduplicationBaseController extends BaseController {
    protected <T> Page<T> applyDeduplication(Page<T> page, Function<T, String> idExtractor);
    protected void clearDeduplicationCache();
}
```

## 使用方法

### 1. 在控制器中使用数据去重功能

```java
@RestController
@RequestMapping("/yourController")
public class YourController extends DeduplicationBaseController {
    
    @Autowired
    private YourService yourService;
    
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        // 查询数据
        Page<YourEntity> page = yourService.queryPage(params);
        
        // 应用数据脱敏，过滤掉已经加载过的重复数据
        Page<YourEntity> dedupPage = applyDeduplication(page, YourEntity::getId);
        
        return R.ok().put("page", dedupPage);
    }
    
    @RequestMapping("/clearCache")
    public R clearCache() {
        clearDeduplicationCache();
        return R.ok("数据脱敏缓存已清除");
    }
}
```

### 2. 直接使用DataDeduplicationService

```java
@Service
public class YourService {
    
    @Autowired
    private DataDeduplicationService dataDeduplicationService;
    
    public List<YourEntity> getFilteredData(List<YourEntity> data, String sessionId) {
        return dataDeduplicationService.filterAndRegisterItems(
            data,
            YourEntity::getId,
            sessionId
        );
    }
}
```

## 配置选项

数据去重服务提供以下配置选项：

- 缓存过期时间：默认30分钟
- 每个会话最大缓存项数：默认10000项
- 缓存清理频率：与缓存过期时间相同

## 注意事项

1. 数据去重是基于会话的，不同用户之间不会互相影响
2. 清除浏览器缓存或重新登录会重置去重状态
3. 可以通过调用clearDeduplicationCache()方法手动清除缓存
4. 数据去重会略微增加服务器内存使用量

## 测试

数据去重功能包含完整的单元测试：

- DataDeduplicationServiceTest：测试数据去重服务的核心功能
- PageDeduplicationUtilTest：测试Page对象去重功能