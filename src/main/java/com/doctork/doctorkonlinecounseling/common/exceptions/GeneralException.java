package com.doctork.doctorkonlinecounseling.common.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends BaseException{



    public GeneralException() {
        super();
    }

    public GeneralException(int errorCode, String message, HttpStatus status) {
        super(errorCode, message, status);
    }

}
