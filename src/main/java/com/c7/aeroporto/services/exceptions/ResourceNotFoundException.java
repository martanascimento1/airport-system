package com.c7.aeroporto.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object e){
        super("Not found, " + e + ".");
    }
}
