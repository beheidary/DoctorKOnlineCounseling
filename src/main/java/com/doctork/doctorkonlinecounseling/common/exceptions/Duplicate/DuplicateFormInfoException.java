package com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate;

import org.springframework.http.HttpStatus;

public class DuplicateFormInfoException extends DuplicateException{

    public DuplicateFormInfoException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public DuplicateFormInfoException()
    {
        super(41,"formInfo.Duplicate", HttpStatus.BAD_REQUEST);
    }


}
