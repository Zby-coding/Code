package cn.jeefast.common.utils;

public class StringUtils {
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}