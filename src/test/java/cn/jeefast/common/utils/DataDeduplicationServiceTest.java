package cn.jeefast.common.utils;

import cn.jeefast.system.service.DataDeduplicationService;
import cn.jeefast.system.service.impl.DataDeduplicationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Unit tests for DataDeduplicationService
 */
public class DataDeduplicationServiceTest {
    
    private DataDeduplicationService deduplicationService;
    
    @Before
    public void setUp() {
        deduplicationService = new DataDeduplicationServiceImpl();
    }
    
    @Test
    public void testFilterDuplicates() {
        // Create test data
        TestItem item1 = new TestItem("1", "Item 1");
        TestItem item2 = new TestItem("2", "Item 2");
        TestItem item3 = new TestItem("3", "Item 3");
        
        List<TestItem> items = Arrays.asList(item1, item2, item3);
        
        // Define ID extractor
        Function<TestItem, String> idExtractor = TestItem::getId;
        
        // First call should return all items
        List<TestItem> result1 = deduplicationService.filterAndRegisterItems(items, idExtractor, "session1");
        assertEquals("First call should return all items", 3, result1.size());
        
        // Second call with same items should return empty list
        List<TestItem> result2 = deduplicationService.filterAndRegisterItems(items, idExtractor, "session1");
        assertEquals("Second call should return no items", 0, result2.size());
        
        // Call with different session should return all items
        List<TestItem> result3 = deduplicationService.filterAndRegisterItems(items, idExtractor, "session2");
        assertEquals("Different session should return all items", 3, result3.size());
        
        // Add a new item and verify only it comes through
        TestItem item4 = new TestItem("4", "Item 4");
        List<TestItem> newItems = Arrays.asList(item1, item2, item4);
        
        List<TestItem> result4 = deduplicationService.filterAndRegisterItems(newItems, idExtractor, "session1");
        assertEquals("Should return only the new item", 1, result4.size());
        assertEquals("Should return item4", "4", result4.get(0).getId());
    }
    
    @Test
    public void testClearCache() {
        // Create test data
        TestItem item1 = new TestItem("1", "Item 1");
        TestItem item2 = new TestItem("2", "Item 2");
        
        List<TestItem> items = Arrays.asList(item1, item2);
        
        // Define ID extractor
        Function<TestItem, String> idExtractor = TestItem::getId;
        
        // Register items
        deduplicationService.filterAndRegisterItems(items, idExtractor, "session1");
        
        // Verify items are filtered
        List<TestItem> result1 = deduplicationService.filterAndRegisterItems(items, idExtractor, "session1");
        assertEquals("Items should be filtered", 0, result1.size());
        
        // Clear cache
        deduplicationService.clearCache("session1");
        
        // Verify items are no longer filtered
        List<TestItem> result2 = deduplicationService.filterAndRegisterItems(items, idExtractor, "session1");
        assertEquals("Items should not be filtered after cache clear", 2, result2.size());
    }
    
    /**
     * Test item class for unit tests
     */
    private static class TestItem {
        private final String id;
        private final String name;
        
        public TestItem(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
    }
}