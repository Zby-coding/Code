package cn.jeefast.common.utils;

import java.util.List;

public class PageResponse<T> {
    private int total;
    private List<T> records;

    public PageResponse() {
    }

    public PageResponse(int total, List<T> records) {
        this.total = total;
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
