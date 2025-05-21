package com.theanh1301.myapp.exception;


import com.theanh1301.myapp.dto.request.NormalizeApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Quan ly exception
@ControllerAdvice // dung chung
public class GlobalExceptionHandler {

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

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage()); // lay mesaage tren vailidation
    }

}
