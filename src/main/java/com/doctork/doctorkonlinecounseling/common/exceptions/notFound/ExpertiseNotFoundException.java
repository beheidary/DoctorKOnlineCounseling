package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExpertiseNotFoundException extends NotFoundException{



    public ExpertiseNotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public ExpertiseNotFoundException()
    {
        super(83,"Physician.NotFound", HttpStatus.BAD_REQUEST);
    }

}
