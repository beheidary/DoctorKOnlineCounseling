package com.doctork.doctorkonlinecounseling.common.exceptions.temporary;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class TemporaryException extends BaseException {



    public TemporaryException(int errorCode, String message, HttpStatus status)
    {

        super(errorCode, message, status);

    }

    public TemporaryException(){

        super(50,"Action is not valid", HttpStatus.BAD_REQUEST);

    }



}
