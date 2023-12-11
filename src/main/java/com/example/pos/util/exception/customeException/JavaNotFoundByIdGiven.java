package com.example.pos.util.exception.customeException;

import com.example.pos.constant.JavaMessage;

public class JavaNotFoundByIdGiven extends RuntimeException{
//    public JavaNotFoundByIdGiven() {
//
//    }

    public JavaNotFoundByIdGiven(){
        super(JavaMessage.notFoundById);
    }
}
