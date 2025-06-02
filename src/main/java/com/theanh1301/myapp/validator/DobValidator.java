package com.theanh1301.myapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

//Annotation ta validate , và kiểu dữ liệu của field ta validate
public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {


    //Nhắn Ctrl + I để thấy các method cần implement


    //Kiểm tra data có đúng hay không
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }


    //Khởi tạo mỗi khi constraint được khởi tạo -> nghĩa là nó lấy giá trị 
    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
