package com.doctork.doctorkonlinecounseling.common.exceptions.Expiration;

import org.springframework.http.HttpStatus;

public class BillExpiredException extends ExpirationException{

    public BillExpiredException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public BillExpiredException()
    {
        super(51,"Bill.Expired", HttpStatus.BAD_REQUEST);
    }

}
