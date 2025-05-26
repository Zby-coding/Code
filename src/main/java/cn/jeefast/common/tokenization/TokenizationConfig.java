package cn.jeefast.common.tokenization;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for tokenization
 */
@Configuration
@ConfigurationProperties(prefix = "tokenization")
public class TokenizationConfig {
    
    /**
     * Minimum token length to consider (default: 2)
     */
    private int minTokenLength = 2;
    
    /**
     * Default tokenization mode (SEARCH or INDEX)
     */
    private String defaultMode = "SEARCH";
    
    /**
     * Path to custom dictionary file
     */
    private String customDictionary;
    
    /**
     * Whether to filter stop words
     */
    private boolean filterStopWords = false;
    
    /**
     * Path to stop words file
     */
    private String stopWordsFile;

    public int getMinTokenLength() {
        return minTokenLength;
    }

    public void setMinTokenLength(int minTokenLength) {
        this.minTokenLength = minTokenLength;
    }

    public String getDefaultMode() {
        return defaultMode;
    }

    public void setDefaultMode(String defaultMode) {
        this.defaultMode = defaultMode;
    }

    public String getCustomDictionary() {
        return customDictionary;
    }

    public void setCustomDictionary(String customDictionary) {
        this.customDictionary = customDictionary;
    }

    public boolean isFilterStopWords() {
        return filterStopWords;
    }

    public void setFilterStopWords(boolean filterStopWords) {
        this.filterStopWords = filterStopWords;
    }

    public String getStopWordsFile() {
        return stopWordsFile;
    }

    public void setStopWordsFile(String stopWordsFile) {
        this.stopWordsFile = stopWordsFile;
    }
}