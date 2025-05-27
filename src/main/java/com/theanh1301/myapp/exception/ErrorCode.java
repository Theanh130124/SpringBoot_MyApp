package com.theanh1301.myapp.exception;

//Là enum
public enum ErrorCode {

    //1 enum có key:code và value: message
    INVALID_KEY(0001,"Sai sót error code truyền vào"),
    UNCATEGORIZED_EXCEPTION(9999, "Chưa xác định ngoại lệ"),
    USER_EXISTS(1001, "Tên tài khoản đã tồn tại"),
    //Phần validate trong dto
    USERNAME_INVALID(1003,"Tên tài khoản không được nhỏ hơn 3 ký tự"),
    PASSWORD_INVALID(1004,"Mật khẩu không được nhỏ hơn 8 ký tự"),
    USER_NOT_EXISTS(1005,"User không tồn tại"),
    UNAUTHENTICATED(1006,"Không đăng nhập thành công");

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
