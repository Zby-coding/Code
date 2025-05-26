package cn.jeefast.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PageDeduplicationUtil
 */
public class PageDeduplicationUtilTest {
    
    @Mock
    private DataDeduplicationService deduplicationService;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testDeduplicatePage() {
        // Create test data
        TestItem item1 = new TestItem("1", "Item 1");
        TestItem item2 = new TestItem("2", "Item 2");
        TestItem item3 = new TestItem("3", "Item 3");
        
        List<TestItem> items = Arrays.asList(item1, item2, item3);
        
        // Create page
        Page<TestItem> page = new Page<>(1, 10);
        page.setRecords(items);
        page.setTotal(3);
        
        // Define ID extractor
        Function<TestItem, String> idExtractor = TestItem::getId;
        
        // Mock deduplication service to filter out item2
        List<TestItem> filteredItems = Arrays.asList(item1, item3);
        when(deduplicationService.filterAndRegisterItems(eq(items), eq(idExtractor), anyString()))
                .thenReturn(filteredItems);
        
        // Test deduplication
        Page<TestItem> dedupPage = PageDeduplicationUtil.deduplicatePage(page, deduplicationService, idExtractor, "session1");
        
        // Verify results
        assertNotNull("Result page should not be null", dedupPage);
        assertEquals("Result page should have 2 items", 2, dedupPage.getRecords().size());
        assertEquals("Result page should have adjusted total", 2, dedupPage.getTotal());
        assertEquals("Result page should have same current page", page.getCurrent(), dedupPage.getCurrent());
        assertEquals("Result page should have same page size", page.getSize(), dedupPage.getSize());
        
        // Verify deduplication service was called
        verify(deduplicationService).filterAndRegisterItems(items, idExtractor, "session1");
    }
    
    @Test
    public void testDeduplicateEmptyPage() {
        // Create empty page
        Page<TestItem> page = new Page<>(1, 10);
        page.setRecords(Arrays.asList());
        page.setTotal(0);
        
        // Define ID extractor
        Function<TestItem, String> idExtractor = TestItem::getId;
        
        // Test deduplication
        Page<TestItem> dedupPage = PageDeduplicationUtil.deduplicatePage(page, deduplicationService, idExtractor, "session1");
        
        // Verify results
        assertNotNull("Result page should not be null", dedupPage);
        assertEquals("Result page should have 0 items", 0, dedupPage.getRecords().size());
        assertEquals("Result page should have total 0", 0, dedupPage.getTotal());
        
        // Verify deduplication service was not called
        verify(deduplicationService, never()).filterAndRegisterItems(anyList(), any(), anyString());
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