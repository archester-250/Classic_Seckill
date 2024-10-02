package com.pku.exception;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(){
        super();
    }
    public PasswordIncorrectException(String message){
        super(message);
    }
}
