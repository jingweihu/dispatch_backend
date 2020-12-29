package com.jingwei.dispatch.api.base;

public class ApiErrorResponse {
    public String status;
    public String error;

    public ApiErrorResponse(String status, String error) {
        this.status = status;
        this.error = error;
    }
}
