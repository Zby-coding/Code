package cn.jeefast.system.service.impl;

import cn.jeefast.system.service.DataDeduplicationService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataDeduplicationServiceImpl implements DataDeduplicationService {

    // 会话缓存（线程安全）
    private final ConcurrentHashMap<String, CacheEntry> sessionCache = new ConcurrentHashMap<>();
    
    // 缓存配置参数
    private static final long DEFAULT_EXPIRATION = 30 * 60 * 1000; // 30分钟
    private static final int MAX_ITEMS_PER_SESSION = 10000;

    // 清理线程池
    private final ScheduledExecutorService cleanupExecutor = 
        Executors.newScheduledThreadPool(1);

    public DataDeduplicationServiceImpl() {
        // 启动定时清理任务
        cleanupExecutor.scheduleAtFixedRate(this::cleanExpiredEntries, 
            DEFAULT_EXPIRATION, DEFAULT_EXPIRATION, TimeUnit.MILLISECONDS);
    }

    @Override
    public <T> List<T> filterDuplicates(Collection<T> items, Function<T, String> idExtractor) {
        return filterAndRegisterItems(items, idExtractor, "default_session");
    }

    @Override
    public <T> List<T> filterAndRegisterItems(Collection<T> items, 
                                            Function<T, String> idExtractor,
                                            String sessionId) {
        CacheEntry cache = sessionCache.computeIfAbsent(sessionId, 
            k -> new CacheEntry(new ConcurrentLinkedDeque<>(), System.currentTimeMillis()));

        return items.stream()
            .filter(item -> {
                String id = idExtractor.apply(item);
                synchronized (cache.ids) {
                    if (!cache.ids.contains(id)) {
                        // 维护缓存大小
                        if (cache.ids.size() >= MAX_ITEMS_PER_SESSION) {
                            cache.ids.pollFirst();
                        }
                        cache.ids.addLast(id);
                        return true;
                    }
                    return false;
                }
            })
            .collect(Collectors.toList());
    }

    @Override
    public void clearCache(String sessionId) {
        sessionCache.remove(sessionId);
    }

    @Override
    public void clearAllCaches() {
        sessionCache.clear();
    }

    // 内部缓存条目类
    private static class CacheEntry {
        ConcurrentLinkedDeque<String> ids;
        long lastAccessTime;

        CacheEntry(ConcurrentLinkedDeque<String> ids, long lastAccessTime) {
            this.ids = ids;
            this.lastAccessTime = lastAccessTime;
        }
    }

    // 过期缓存清理
    private void cleanExpiredEntries() {
        long now = System.currentTimeMillis();
        sessionCache.entrySet().removeIf(entry -> 
            (now - entry.getValue().lastAccessTime) > DEFAULT_EXPIRATION);
    }
}