package com.doctork.doctorkonlinecounseling.common.exceptions.invalid;

import org.springframework.http.HttpStatus;

public class InvalidLoginTypeException extends InvalidException{

    public InvalidLoginTypeException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public InvalidLoginTypeException()
    {
        super(72,"Invalid Login type", HttpStatus.FORBIDDEN);
    }



}
