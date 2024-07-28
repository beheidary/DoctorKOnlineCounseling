package com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate;

import org.springframework.http.HttpStatus;

public class DuplicateActivePriceException extends DuplicateException{
    public DuplicateActivePriceException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public DuplicateActivePriceException()
    {
        super(43,"PriceSlot.Duplicate", HttpStatus.BAD_REQUEST);
    }

}
