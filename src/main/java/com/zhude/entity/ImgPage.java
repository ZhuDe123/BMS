package com.zhude.entity;

public class ImgPage {
    private Integer pageIndex;//当前页码
    private Integer pageSize;//页大小
    private Integer totalCounts;//数据总条数
    private Integer totalPages;//总页数
    private Integer startRows;//起始行
    private String contentType;
    private String filename;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ImgPage(Integer pageIndex) {
        this(pageIndex, 8);
    }

    public ImgPage(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.setStartRows((pageIndex - 1) * pageSize);
        this.setContentType("primary");

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
