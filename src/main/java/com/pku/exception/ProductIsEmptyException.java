package com.pku.exception;

public class ProductIsEmptyException extends RuntimeException{
    public ProductIsEmptyException(){
        super();
    }
    public ProductIsEmptyException(String message){
        super(message);
    }
}
