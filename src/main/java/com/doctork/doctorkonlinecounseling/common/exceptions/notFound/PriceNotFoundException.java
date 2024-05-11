package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PriceNotFoundException extends NotFoundException{
    public PriceNotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public PriceNotFoundException()
    {
        super(47,"Patient.NotFound", HttpStatus.BAD_REQUEST);
    }
}
