package cn.jeefast.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Utility class for session-related operations
 */
public class SessionUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);
    
    private SessionUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Get the current session ID
     * 
     * @return The session ID, or null if no session exists
     */
    public static String getCurrentSessionId() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                HttpSession session = request.getSession(false);
                if (session != null) {
                    return session.getId();
                }
            }
        } catch (Exception e) {
            logger.warn("Error getting current session ID", e);
        }
        return null;
    }
    
    /**
     * Get a user-specific deduplication key
     * This combines the session ID with the user ID (if available)
     * 
     * @return A deduplication key for the current user
     */
    public static String getUserDeduplicationKey() {
        String sessionId = getCurrentSessionId();
        String userId = null;
        
        try {
            // Try to get the user ID from ShiroUtils
            userId = ShiroUtils.getUserId() != null ? ShiroUtils.getUserId().toString() : null;
        } catch (Exception e) {
            // Ignore exceptions, just use session ID
        }
        
        if (sessionId != null && userId != null) {
            return "dedup:" + userId + ":" + sessionId;
        } else if (sessionId != null) {
            return "dedup:session:" + sessionId;
        } else if (userId != null) {
            return "dedup:user:" + userId;
        } else {
            return "dedup:anonymous";
        }
    }
}