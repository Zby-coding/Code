package cn.jeefast.common.tokenization;

import java.util.List;

/**
 * Interface for text tokenization services
 */
public interface TokenizationService {
    
    /**
     * Tokenize a text string using default settings
     * 
     * @param text The text to tokenize
     * @return A list of tokens
     */
    List<String> tokenize(String text);
    
    /**
     * Tokenize a text string using specified mode
     * 
     * @param text The text to tokenize
     * @param mode The tokenization mode (SEARCH or INDEX)
     * @return A list of tokens
     */
    List<String> tokenize(String text, TokenizationMode mode);
    
    /**
     * Add words to the custom dictionary
     * 
     * @param word The word to add
     * @param frequency The frequency of the word (optional)
     * @param tag The part-of-speech tag (optional)
     */
    void addWord(String word, Integer frequency, String tag);
    
    /**
     * Add words to the custom dictionary
     * 
     * @param word The word to add
     */
    void addWord(String word);
    
    /**
     * Load custom dictionary from a file
     * 
     * @param filePath The path to the dictionary file
     */
    void loadDictionary(String filePath);
    
    /**
     * Get the minimum token length for filtering
     * 
     * @return The minimum token length
     */
    int getMinTokenLength();
    
    /**
     * Set the minimum token length for filtering
     * 
     * @param length The minimum token length
     */
    void setMinTokenLength(int length);
}