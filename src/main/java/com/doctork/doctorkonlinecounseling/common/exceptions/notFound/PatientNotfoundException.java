package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;

public class PatientNotfoundException extends NotFoundException{


    public PatientNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public PatientNotfoundException()
    {
        super(46,"Patient.NotFound", HttpStatus.BAD_REQUEST);
    }

}
