package com.doctork.doctorkonlinecounseling.common.exceptions;

import java.time.LocalDateTime;

public class ErrorDetail
{
    private LocalDateTime timeStamp;
    private int errorCode;
    private String message;


    public ErrorDetail(LocalDateTime timeStamp, int errorCode, String message) {
        this.timeStamp = timeStamp;
        this.errorCode = errorCode;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
