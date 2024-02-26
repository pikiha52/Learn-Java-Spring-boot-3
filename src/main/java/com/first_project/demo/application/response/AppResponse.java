package com.first_project.demo.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppResponse<T> {
    @JsonProperty("responseCode")
    private Number code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("error")
    private String error;

    @JsonProperty("data")
    private Object data;

    public Number getResponseCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public void setCode(Number number) {
        this.code = number;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrorMessage(String error) {
        this.error = error;
    }
}
