package com.qzj.facial.api;

public class ApiException extends RuntimeException {

    // 网络不可用
    public static final int NETWORK_NOT_AVAILABLE = -101;
    // 未知异常
    public static final int NETWORK_UNKNOWN = -102;

    private int code;
    private String message;

    public ApiException(){}

    public ApiException(int code) {
        this(code, "");
    }

    public ApiException(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
        setMessage(detailMessage);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
