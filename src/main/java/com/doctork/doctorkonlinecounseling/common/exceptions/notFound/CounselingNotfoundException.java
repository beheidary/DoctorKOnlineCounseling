package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;

public class CounselingNotfoundException extends NotFoundException{

    public CounselingNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public CounselingNotfoundException()
    {
        super(91,"Counseling.NotFound", HttpStatus.BAD_REQUEST);
    }

}
