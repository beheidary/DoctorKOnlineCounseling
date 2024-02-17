package com.doctork.doctorkonlinecounseling.common.exceptions.temporary;

import org.springframework.http.HttpStatus;

public class DatabaseTimeOutException extends TemporaryException{



    public DatabaseTimeOutException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public DatabaseTimeOutException()
    {
        super(50,"Temporary.Database", HttpStatus.BAD_REQUEST);
    }


}
