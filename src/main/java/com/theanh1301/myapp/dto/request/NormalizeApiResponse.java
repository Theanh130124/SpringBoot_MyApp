package com.theanh1301.myapp.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;

//Chuẩn hóa response cho tất cả api trong hệ thống thay vì chỉ in ra dòng lỗi
//-> Nữa đổi tên class này thành ApiResponse
@JsonInclude(JsonInclude.Include.NON_NULL) //cái nào null bỏ khỏi json
public class NormalizeApiResponse<T> {

    private int code = 1000; //1000 là thành công mình tự quy định trong tài liệu
    private String message;
    private T result ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
