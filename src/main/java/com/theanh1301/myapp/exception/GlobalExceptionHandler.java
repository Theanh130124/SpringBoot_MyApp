package com.theanh1301.myapp.exception;


import com.theanh1301.myapp.dto.request.NormalizeApiRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Quan ly exception
@ControllerAdvice // dung chung
public class GlobalExceptionHandler {

    //Các exception không phải các loại đã bắt bên dưới
    @ExceptionHandler(value =Exception.class)
    public ResponseEntity<NormalizeApiRequest> handleException(Exception ex){

        NormalizeApiRequest response = new NormalizeApiRequest();
        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // STATUS mình tự fix
    }

    //Exception trong GlobalException mình bắt
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<NormalizeApiRequest> handleAppException(AppException myexception){

        ErrorCode errorCode = myexception.getErrorCode(); // lấy cái EnumError ra
        NormalizeApiRequest response = new NormalizeApiRequest();
        response.setCode(errorCode.getCode()); //code này là lỗi mình tự quy định
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(response);
        //Sẽ in ra nhưng lỗi theo trong message của runtimeexception không cần
        //try catch
    }


    //Phần exception cho dto.request  ->MethodArgumentNotValidException do mình in ra trước khi có lỗi này nên
    //biet duoc la exception nay
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<NormalizeApiRequest> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();//lấy key "USERNAME_INVALID"
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        //Nếu lỡ mình nhập key có bị sai (thiếu sót sai chính tả bên dto.request) thì bắt ở đây
        try{
            errorCode = ErrorCode.valueOf(enumKey);

        }catch (IllegalArgumentException e){

        }
        NormalizeApiRequest response = new NormalizeApiRequest();
        response.setCode(errorCode.getCode()); //code này là lỗi mình tự quy định
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response); // lay mesaage tren vailidation
    }


    //Phần exception này là 403 cho cái api lấy tất cả người dùng -> admin chỉ có quyền (user sẽ bị 403
    @ExceptionHandler(value= AccessDeniedException.class)
    public ResponseEntity<NormalizeApiRequest> handleAccessDeniedException(AccessDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED; // lấy cái EnumError ra
        NormalizeApiRequest response = new NormalizeApiRequest();
        response.setCode(errorCode.getCode()); //code này là lỗi mình tự quy định
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(response);
    }

}
