package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhysicianNotFoundException extends NotFoundException {

    public PhysicianNotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public PhysicianNotFoundException()
    {
        super(46,"Physician.NotFound", HttpStatus.BAD_REQUEST);
    }

}