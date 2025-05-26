package cn.jeefast.common.utils;

import cn.jeefast.system.service.DataDeduplicationService;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

/**
 * Utility class for applying deduplication to Page objects
 */
public class PageDeduplicationUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(PageDeduplicationUtil.class);
    
    private PageDeduplicationUtil() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Apply deduplication to a Page object
     * 
     * @param <T> The type of data items in the page
     * @param page The original page object
     * @param deduplicationService The deduplication service
     * @param idExtractor Function to extract the unique identifier from an item
     * @param sessionId Optional session identifier for user-specific deduplication
     * @return A new Page object with duplicates removed
     */
    public static <T> Page<T> deduplicatePage(
            Page<T> page, 
            DataDeduplicationService deduplicationService,
            Function<T, String> idExtractor,
            String sessionId) {
        
        if (page == null || page.getRecords() == null || page.getRecords().isEmpty()) {
            return page;
        }
        
        // Create a new page with the same properties
        Page<T> newPage = new Page<>(page.getCurrent(), page.getSize());
        newPage.setTotal(page.getTotal());
        
        // Filter out duplicates
        List<T> filteredRecords = deduplicationService.filterAndRegisterItems(
                page.getRecords(), idExtractor, sessionId);
        
        // Set the filtered records
        newPage.setRecords(filteredRecords);
        
        // Adjust total count if needed
        if (filteredRecords.size() < page.getRecords().size()) {
            // If we filtered out some records, adjust the total count
            long removedCount = page.getRecords().size() - filteredRecords.size();
            newPage.setTotal((int) Math.max(0, page.getTotal() - removedCount));
            
            logger.debug("Removed {} duplicate items from page, adjusted total from {} to {}", 
                    removedCount, page.getTotal(), newPage.getTotal());
        }
        
        return newPage;
    }
}