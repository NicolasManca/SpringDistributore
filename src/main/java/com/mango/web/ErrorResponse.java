package com.mango.web;

public class ErrorResponse {
    String errorCode;
    Integer code;

    public ErrorResponse(String errorCode, Integer code) {
        this.errorCode = errorCode;
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
