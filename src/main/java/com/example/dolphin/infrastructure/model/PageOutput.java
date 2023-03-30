package com.example.dolphin.infrastructure.model;

import java.util.Collections;
import java.util.List;

/**
 * 分页输出
 *
 * @author ankelen
 * @date 2022-10-14 13:27
 */
public class PageOutput<T> {
    /**
     * 元素
     */
    private List<T> content;
    /**
     * 页长
     */
    private int pageSize;
    /**
     * 页码
     */
    private int pageNumber;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 总个数
     */
    private long totalElements;

    /**
     * 不分页(作一页返回)
     * pageSize = content.size()
     * pageNumber = 0
     * totalPages = 1
     * totalElements = content.size()
     */
    public static <T> PageOutput<T> unPaged(List<T> content) {
        int total = content.size();

        PageOutput<T> output = new PageOutput<>();
        output.setContent(content);
        output.setPageSize(total);
        output.setPageNumber(0);
        output.setTotalPages(1);
        output.setTotalElements(total);
        return output;
    }

    /**
     * 空页 元素为[],其他内容跟unPaged一致
     */
    public static <T> PageOutput<T> empty() {
        return PageOutput.unPaged(Collections.emptyList());
    }

    //region gt&st...

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "PageOutput{" +
                "content=" + content +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }

    //endregion
}
