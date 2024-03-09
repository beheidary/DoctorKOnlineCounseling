package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DoctorNotFoundException extends NotFoundException {

    public DoctorNotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public DoctorNotFoundException()
    {
        super(46,"Doctor.NotFound", HttpStatus.BAD_REQUEST);
    }

}