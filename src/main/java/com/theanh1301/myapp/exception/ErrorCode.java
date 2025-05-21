package com.theanh1301.myapp.exception;

//Là enum
public enum ErrorCode {

    USER_EXISTS(1001, "User name da ton tai");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
