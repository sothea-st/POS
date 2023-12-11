package com.example.pos.util.exception.customeException;

public class FieldIsRequiredException extends RuntimeException{
    public FieldIsRequiredException() {}

    public FieldIsRequiredException(String msg)
    {
        super(msg);
    }
}
