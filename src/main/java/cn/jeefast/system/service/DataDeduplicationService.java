package cn.jeefast.system.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface DataDeduplicationService {
    <T> List<T> filterDuplicates(Collection<T> items, Function<T, String> idExtractor);
    <T> List<T> filterAndRegisterItems(Collection<T> items, Function<T, String> idExtractor, String sessionId);
    void clearCache(String sessionId);
    void clearAllCaches();
}