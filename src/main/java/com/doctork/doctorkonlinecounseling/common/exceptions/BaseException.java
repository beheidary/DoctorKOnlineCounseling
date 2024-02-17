package com.doctork.doctorkonlinecounseling.common.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {


    protected int errorCode;
    protected String message;
    protected HttpStatus status;

    public BaseException() {

        this.errorCode=100;
        this.message="Unknown Error";
        this.status= HttpStatus.BAD_REQUEST;

    }


    public BaseException(int errorCode, String message, HttpStatus status){

        this.errorCode = errorCode;
        this.message = message;
        this.status = status;

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }




}
