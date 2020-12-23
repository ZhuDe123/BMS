package com.zhude.entity;

public class Page {
    private Integer pageIndex;//当前页码
    private Integer pageSize;//页大小
    private Integer totalCounts;//数据总条数
    private Integer totalPages;//总页数
    private Integer startRows;//起始行

    @Override
    public String toString() {
        return "Page{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalCounts=" + totalCounts +
                ", totalPages=" + totalPages +
                ", startRows=" + startRows +
                '}';
    }

    public Page(Integer pageIndex) {
        this(pageIndex, 8);
    }

    public Page(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.setStartRows((pageIndex - 1) * pageSize);
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        this.setStartRows((pageIndex - 1) * pageSize);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
        //计算总页数
        this.setTotalPages(this.totalCounts % this.pageSize == 0 ? this.getTotalCounts() / this.pageSize : this.getTotalCounts() / this.pageSize + 1);
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getStartRows() {
        return startRows;
    }

    public void setStartRows(Integer startRows) {
        this.startRows = startRows;
    }
}
