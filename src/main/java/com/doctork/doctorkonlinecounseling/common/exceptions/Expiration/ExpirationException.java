package com.doctork.doctorkonlinecounseling.common.exceptions.Expiration;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ExpirationException extends BaseException {

    public ExpirationException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public ExpirationException()
    {
        super(50,"Expired", HttpStatus.BAD_REQUEST);
    }


}
