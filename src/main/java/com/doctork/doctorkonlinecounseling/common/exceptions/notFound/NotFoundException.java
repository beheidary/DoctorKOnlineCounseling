package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends BaseException {

    public NotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public NotFoundException()
    {
        super(80,"Not.Found", HttpStatus.BAD_REQUEST);
    }

}
