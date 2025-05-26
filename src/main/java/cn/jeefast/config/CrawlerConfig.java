package cn.jeefast.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Configuration
@ConfigurationProperties(prefix = "crawler")
@Data
public class CrawlerConfig {
    private int connectTimeout = 5000;
    private int readTimeout = 5000;
    private int maxRetries = 3;
    private long retryDelay = 1000;
    private long cacheDuration = 300000; // 5分钟
}