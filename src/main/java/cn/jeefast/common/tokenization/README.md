# 分词服务 (Tokenization Service)

本模块提供了中文分词服务，用于将文本分割成单词或短语，以便进行搜索和数据存储。

## 功能特点

1. 支持多种分词模式：
   - SEARCH 模式：适用于搜索查询，分词粒度较粗
   - INDEX 模式：适用于索引内容，分词粒度较细

2. 自定义词典支持：
   - 可以添加自定义词语到词典中
   - 支持从文件加载自定义词典
   - 可以为词语指定词频和词性

3. 分词过滤：
   - 可以设置最小词长进行过滤
   - 支持停用词过滤

## 配置选项

在 `application.yml` 中可以配置以下选项：

```yaml
tokenization:
  min-token-length: 2            # 最小词长，默认为2
  default-mode: SEARCH           # 默认分词模式，可选 SEARCH 或 INDEX
  custom-dictionary: classpath:dict/custom_dict.txt  # 自定义词典路径
  filter-stop-words: false       # 是否过滤停用词
  stop-words-file: classpath:dict/stop_words.txt    # 停用词文件路径
```

## 使用方法

### 基本用法

```java
@Autowired
private TokenizationService tokenizationService;

// 使用默认设置进行分词
List<String> tokens = tokenizationService.tokenize("需要分词的文本");

// 使用指定模式进行分词
List<String> searchTokens = tokenizationService.tokenize("搜索查询", TokenizationMode.SEARCH);
List<String> indexTokens = tokenizationService.tokenize("索引内容", TokenizationMode.INDEX);
```

### 自定义词典

```java
// 添加单个词语
tokenizationService.addWord("自定义词语");

// 添加带词频和词性的词语
tokenizationService.addWord("自定义词语", 10, "n");

// 从文件加载词典
tokenizationService.loadDictionary("/path/to/dictionary.txt");
```

### 调整分词参数

```java
// 设置最小词长
tokenizationService.setMinTokenLength(3);
```

## 自定义词典格式

自定义词典文件的格式为：

```
词语 词频 词性
```

例如：

```
人工智能 10 n
机器学习 10 n
深度学习 10 n
```

词频和词性是可选的，可以只提供词语。

## 停用词格式

停用词文件的格式为每行一个停用词：

```
的
了
和
与
```

## 实现细节

本服务基于 Jieba 中文分词库实现，提供了更加灵活和可配置的分词功能。