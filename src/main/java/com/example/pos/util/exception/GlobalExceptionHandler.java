package com.example.pos.util.exception;

import com.example.pos.util.exception.customeException.JavaDataAlreadyExists;
import com.example.pos.util.exception.customeException.JavaNotFoundByIdGiven;
import com.example.pos.util.exception.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> requiredField(MethodArgumentNotValidException ex){
        HashMap<String,String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return map;
    }


    @ExceptionHandler(JavaDataAlreadyExists.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError dataAlreadyExist(JavaDataAlreadyExists ex) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(JavaNotFoundByIdGiven.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError dataNotFoundByIdGiven(JavaNotFoundByIdGiven ex){
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
    }



//    @ExceptionHandler(value= FieldIsRequiredException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse fieldIsRequired(FieldIsRequiredException ex){
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
//    }

}
