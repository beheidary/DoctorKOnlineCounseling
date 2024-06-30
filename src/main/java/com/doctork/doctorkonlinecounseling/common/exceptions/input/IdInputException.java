package com.doctork.doctorkonlinecounseling.common.exceptions.input;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdInputException extends InputException
{
    public IdInputException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public IdInputException()
    {
        super(61,"id.null", HttpStatus.BAD_REQUEST);
    }
}
