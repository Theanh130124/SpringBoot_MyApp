package com.theanh1301.myapp.exception;


import com.theanh1301.myapp.dto.request.NormalizeApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Quan ly exception
@ControllerAdvice // dung chung
public class GlobalExceptionHandler {

    //Các exception không phải các loại đã bắt bên dưới
    @ExceptionHandler(value =Exception.class)
    public ResponseEntity<NormalizeApiResponse> handleException(Exception ex){
        NormalizeApiResponse response = new NormalizeApiResponse();
        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<NormalizeApiResponse> handleAppException(AppException myexception){

        ErrorCode errorCode = myexception.getErrorCode(); // lấy cái EnumError ra
        NormalizeApiResponse response = new NormalizeApiResponse();
        response.setCode(errorCode.getCode()); //code này là lỗi mình tự quy định
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
        //Sẽ in ra nhưng lỗi theo trong message của runtimeexception không cần
        //try catch

    }

    //Phần exception cho dto.request  ->MethodArgumentNotValidException do mình in ra trước khi có lỗi này nên
    //biet duoc la exception nay
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<NormalizeApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();//lấy key "USERNAME_INVALID"
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        //Nếu lỡ mình nhập key có bị sai (thiếu sót sai chính tả bên dto.request) thì bắt ở đây
        try{
            errorCode = ErrorCode.valueOf(enumKey);

        }catch (IllegalArgumentException e){

        }
        NormalizeApiResponse response = new NormalizeApiResponse();
        response.setCode(errorCode.getCode()); //code này là lỗi mình tự quy định
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response); // lay mesaage tren vailidation
    }

}
