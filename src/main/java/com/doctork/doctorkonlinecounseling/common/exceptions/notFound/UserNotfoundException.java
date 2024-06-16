package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;

public class UserNotfoundException extends NotFoundException{


    public UserNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public UserNotfoundException()
    {
        super(46,"User.NotFound", HttpStatus.BAD_REQUEST);
    }


}
