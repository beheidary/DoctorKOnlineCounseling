package com.doctork.doctorkonlinecounseling.common.exceptions.invalid;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends InvalidException{


    public InvalidDataException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public InvalidDataException()
    {
        super(101,"Data.Invalid", HttpStatus.BAD_REQUEST);
    }


}
