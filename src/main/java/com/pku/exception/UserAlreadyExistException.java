package com.pku.exception;

public class UserAlreadyExistException extends BaseException{
    public UserAlreadyExistException(){
        super();
    }
    public UserAlreadyExistException(String message){
        super(message);
    }
}
