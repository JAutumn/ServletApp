package com.servlet.app.core;

public class Paging {
    private final long allCount;
    private final long pagesCount;
    private final int limit;
    private final int pageNumber;
    private final String sortField;
    private final String sortType;

    public Paging(long allCount, int limit, int pageNumber, String sortField, String sortType) {
        this.allCount = allCount;
        long residue =allCount % limit;
        this.pagesCount = residue == 0 ? (allCount / limit)
                                       : (allCount / limit) + 1;
        this.limit = limit;
        this.pageNumber = pageNumber;
        this.sortField = sortField;
        this.sortType = sortType;
    }

    public long getAllCount() {
        return allCount;
    }

    public int getLimit() {
        return limit;
    }

    public long getPagesCount() {
        return pagesCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getSortField() {
        return sortField;
    }

    public String getSortType() {
        return sortType;
    }
}
