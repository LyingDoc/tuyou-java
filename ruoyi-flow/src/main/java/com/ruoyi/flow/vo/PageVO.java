package com.ruoyi.flow.vo;

import java.io.Serializable;

public class PageVO<T> implements Serializable {
    private T param;
    private  int pagesize=5000;
    private  int page;

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
