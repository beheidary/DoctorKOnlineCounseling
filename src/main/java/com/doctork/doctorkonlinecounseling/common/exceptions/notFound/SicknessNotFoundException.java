package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class SicknessNotFoundException extends BaseException {

    public SicknessNotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public SicknessNotFoundException()
    {
        super(37,"Sickness.NotFound", HttpStatus.BAD_REQUEST);
    }
}
