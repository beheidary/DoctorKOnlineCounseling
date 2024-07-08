package com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate;

import org.springframework.http.HttpStatus;

public class DuplicateExpertiseException extends DuplicateException{

    public DuplicateExpertiseException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public DuplicateExpertiseException()
    {
        super(42,"Expertise.Duplicate", HttpStatus.BAD_REQUEST);
    }


}