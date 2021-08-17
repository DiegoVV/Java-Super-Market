package com.diego.SuperMarket.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message){
        super(message);
    }
}
