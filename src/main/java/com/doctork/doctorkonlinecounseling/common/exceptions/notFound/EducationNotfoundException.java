package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EducationNotfoundException extends NotFoundException {

    public EducationNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public EducationNotfoundException()
    {
        super(88,"Education.NotFound", HttpStatus.BAD_REQUEST);
    }
}
