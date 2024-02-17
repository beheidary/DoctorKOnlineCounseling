package com.doctork.doctorkonlinecounseling.common.exceptions.invalid;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidException extends BaseException {


    public InvalidException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public InvalidException()
    {
        super(80,"not valid error", HttpStatus.BAD_REQUEST);
    }



}
