package com.pku.exception;

public class SeckillHasEndedException extends RuntimeException{
    public SeckillHasEndedException(){
        super();
    }
    public SeckillHasEndedException(String message){
        super(message);
    }
}
