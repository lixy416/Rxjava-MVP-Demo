package com.rxjava.mvp.httputil;

/**
 * @version 1.0
 *          created by lxf on 2016/8/17 15:07.
 */
public class BaseHttpResult<T> {
    public String status;

    private T results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
