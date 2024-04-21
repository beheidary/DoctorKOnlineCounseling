package com.doctork.doctorkonlinecounseling.common.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class InputErrorDetail
{
    private LocalDateTime timeStamp;
    private int errorCode;
    private String message;
    private List<FieldItemError> fieldErrors;


    public InputErrorDetail(LocalDateTime timeStamp, int errorCode, String message, List<FieldItemError> fieldErrors) {
        this.timeStamp = timeStamp;
        this.errorCode = errorCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }


    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldItemError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldItemError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}

