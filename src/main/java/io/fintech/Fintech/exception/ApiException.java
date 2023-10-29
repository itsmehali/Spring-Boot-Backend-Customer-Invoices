package io.fintech.Fintech.exception;

public class ApiException extends  RuntimeException {
    public ApiException(String message){
        super(message);
    }
}
