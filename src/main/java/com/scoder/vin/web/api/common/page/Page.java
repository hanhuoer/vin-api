package com.scoder.vin.web.api.common.page;

public interface Page<T> {

    void setPageIndex(int pageIndex);

    int getPageIndex();

    void setPageSize(int pageSize);

    int getPageSize();

    int getLimitStart();

    int getLimitEnd();

    void setTotalSize(int totalSize);

    int getTotalSize();

    void setData(T data);

    T getData();
}
