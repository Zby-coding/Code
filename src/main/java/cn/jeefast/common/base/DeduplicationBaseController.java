package cn.jeefast.common.base;

import cn.jeefast.common.utils.PageDeduplicationUtil;
import cn.jeefast.common.utils.SessionUtils;
import cn.jeefast.system.service.DataDeduplicationService;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.function.Function;

/**
 * Base controller with data deduplication support
 * Extends this class to add data deduplication functionality to your controllers
 */
public class DeduplicationBaseController extends BaseController {
    
    private static final Logger logger = LoggerFactory.getLogger(DeduplicationBaseController.class);
    
    @Resource
    protected DataDeduplicationService dataDeduplicationService;
    
    /**
     * Apply data deduplication to a page of results
     * 
     * @param <T> The type of data items in the page
     * @param page The original page object
     * @param idExtractor Function to extract the unique identifier from an item
     * @return A new Page object with duplicates removed
     */
    protected <T> Page<T> applyDeduplication(Page<T> page, Function<T, String> idExtractor) {
        String userDeduplicationKey = SessionUtils.getUserDeduplicationKey();
        
        Page<T> dedupPage = PageDeduplicationUtil.deduplicatePage(
                page, 
                dataDeduplicationService, 
                idExtractor, 
                userDeduplicationKey
        );
        
        logger.debug("Applied data deduplication: original items={}, filtered items={}", 
                page.getRecords().size(), dedupPage.getRecords().size());
        
        return dedupPage;
    }
    
    /**
     * Clear the deduplication cache for the current user
     */
    protected void clearDeduplicationCache() {
        String userDeduplicationKey = SessionUtils.getUserDeduplicationKey();
        dataDeduplicationService.clearCache(userDeduplicationKey);
        logger.info("Cleared deduplication cache for user key: {}", userDeduplicationKey);
    }
}