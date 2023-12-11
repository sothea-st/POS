package com.example.pos.constant;


import com.example.pos.util.exception.customeException.JavaDataAlreadyExists;

public class JavaValidation {
    public static void checkDataAlreadyExists(boolean value){
        if( value ) throw new JavaDataAlreadyExists(JavaMessage.nameAlreadyExits());
    }
}
