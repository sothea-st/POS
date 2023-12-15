package com.example.pos.util.exception.customeException;

public class InValidEmail extends RuntimeException{
    public InValidEmail(){}
    public InValidEmail(String message) {
        super(message);
    }
}
