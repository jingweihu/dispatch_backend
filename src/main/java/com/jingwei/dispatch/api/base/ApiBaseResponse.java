package com.jingwei.dispatch.api.base;

public class ApiBaseResponse<T> {
    public String status;
    public T response;

    public ApiBaseResponse(String status, T response) {
        this.status = status;
        this.response = response;
    }
}
