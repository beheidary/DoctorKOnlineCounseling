package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;

public class ExperienceNotfoundException extends NotFoundException{

    public ExperienceNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public ExperienceNotfoundException()
    {
        super(89,"Experiences.NotFound", HttpStatus.BAD_REQUEST);
    }

}
