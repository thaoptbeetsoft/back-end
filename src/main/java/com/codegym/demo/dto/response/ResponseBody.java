package com.codegym.demo.dto.response;


public class ResponseBody {
    private String responseCode;
    private String responseMessage;
    private Object responseData;
    public ResponseBody(Response response, Object data) {
        this.responseCode = response.getResponseCode();
        this.responseMessage = response.getResponseMessage();
        this.responseData = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
}
