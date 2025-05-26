package cn.jeefast.tokenization;

import cn.jeefast.SearchedApplication;
import cn.jeefast.common.tokenization.TokenizationMode;
import cn.jeefast.common.tokenization.TokenizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchedApplication.class)
public class TokenizationServiceTest {

    @Autowired
    private TokenizationService tokenizationService;

    @Test
    public void testTokenizeWithDefaultSettings() {
        String text = "我想要修改保存数据的分词规则";
        List<String> tokens = tokenizationService.tokenize(text);
        
        System.out.println("Default tokenization results:");
        for (String token : tokens) {
            System.out.println(token);
        }
        
        // Verify that tokens are returned
        assertFalse("Tokenization should return results", tokens.isEmpty());
    }
    
    @Test
    public void testTokenizeWithSearchMode() {
        String text = "我想要修改保存数据的分词规则";
        List<String> tokens = tokenizationService.tokenize(text, TokenizationMode.SEARCH);
        
        System.out.println("SEARCH mode tokenization results:");
        for (String token : tokens) {
            System.out.println(token);
        }
        
        // Verify that tokens are returned
        assertFalse("Tokenization should return results", tokens.isEmpty());
    }
    
    @Test
    public void testTokenizeWithIndexMode() {
        String text = "我想要修改保存数据的分词规则";
        List<String> tokens = tokenizationService.tokenize(text, TokenizationMode.INDEX);
        
        System.out.println("INDEX mode tokenization results:");
        for (String token : tokens) {
            System.out.println(token);
        }
        
        // Verify that tokens are returned
        assertFalse("Tokenization should return results", tokens.isEmpty());
    }
    
    @Test
    public void testCustomDictionary() {
        // Add a custom word
        tokenizationService.addWord("分词规则");
        
        String text = "我想要修改保存数据的分词规则";
        List<String> tokens = tokenizationService.tokenize(text);
        
        System.out.println("Tokenization results with custom dictionary:");
        for (String token : tokens) {
            System.out.println(token);
        }
        
        // Verify that the custom word is recognized as a single token
        assertTrue("Custom word should be recognized", tokens.contains("分词规则"));
    }
}