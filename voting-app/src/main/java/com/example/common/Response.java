package com.example.common;

public class Response {
    public int responseCode;
    public String responseDesc;
    public Object data;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(int responseCode, String responseDesc, Object data) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.data = data;
    }

}
