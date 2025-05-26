package cn.jeefast.common.tokenization;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.WordDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of TokenizationService using Jieba Chinese word segmentation
 */
@Service
public class JiebaTokenizationService implements TokenizationService {
    
    private static final Logger logger = LoggerFactory.getLogger(JiebaTokenizationService.class);
    
    private JiebaSegmenter segmenter;
    
    @Value("${tokenization.min-token-length:2}")
    private int minTokenLength;
    
    @Value("${tokenization.default-mode:SEARCH}")
    private String defaultMode;
    
    @Value("${tokenization.custom-dictionary:}")
    private String customDictionaryPath;
    
    @PostConstruct
    public void init() {
        segmenter = new JiebaSegmenter();
        
        // Load custom dictionary if specified
        if (customDictionaryPath != null && !customDictionaryPath.isEmpty()) {
            loadDictionary(customDictionaryPath);
        }
        
        logger.info("JiebaTokenizationService initialized with minTokenLength={}, defaultMode={}", 
                minTokenLength, defaultMode);
    }
    
    @Override
    public List<String> tokenize(String text) {
        TokenizationMode mode = TokenizationMode.valueOf(defaultMode);
        return tokenize(text, mode);
    }
    
    @Override
    public List<String> tokenize(String text, TokenizationMode mode) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        SegMode segMode = (mode == TokenizationMode.SEARCH) ? 
                SegMode.SEARCH : SegMode.INDEX;
        
        List<String> tokens;
        if (segMode == SegMode.SEARCH) {
            // Use sentenceProcess for SEARCH mode (compatible with existing code)
            tokens = segmenter.sentenceProcess(text);
        } else {
            // Use process for INDEX mode with more precise segmentation
            tokens = segmenter.process(text, segMode)
                    .stream()
                    .map(term -> term.word)
                    .collect(Collectors.toList());
        }
        
        // Filter tokens by length
        return tokens.stream()
                .filter(token -> token.length() >= minTokenLength)
                .collect(Collectors.toList());
    }

    // 删除原有addWord方法，改为通过字典文件加载
    @Override
    public void addWord(String word, Integer frequency, String tag) {
        try {
            // 创建临时词典文件并写入新词
            Path tempDict = Files.createTempFile("dynamic_dict_", ".txt");
            Files.write(tempDict, Collections.singletonList(String.format("%s %s %s",
                    word,
                    (frequency != null) ? frequency : 1000,
                    (tag != null) ? tag : "n")));

            // 通过公共API加载
            WordDictionary.getInstance().loadUserDict(tempDict);
            logger.debug("Added word via temp dict: {} (freq={}, tag={})", word, frequency, tag);
        } catch (IOException e) {
            logger.error("Failed to add word dynamically", e);
        }
    }



    @Override
    public void addWord(String word) {
        addWord(word, null, null);
    }
    
    @Override
    public void loadDictionary(String filePath) {
        try {
            // 从类路径加载资源
            Path tempFile = Files.createTempFile("custom_dict", ".txt");
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("dict/custom_dict.txt")) {
                if (is == null) {
                    logger.error("Custom dictionary file not found in classpath: dict/custom_dict.txt");
                    return;
                }
                Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            WordDictionary dictionary = WordDictionary.getInstance();
            dictionary.loadUserDict(tempFile);
            logger.info("Loaded custom dictionary from temporary file: {}", tempFile);
        } catch (Exception e) {
            logger.error("Failed to load custom dictionary from classpath: dict/custom_dict.txt", e);
        }
    }
    @Override
    public int getMinTokenLength() {
        return minTokenLength;
    }
    
    @Override
    public void setMinTokenLength(int length) {
        if (length >= 0) {
            this.minTokenLength = length;
            logger.info("Set minimum token length to: {}", length);
        }
    }
}