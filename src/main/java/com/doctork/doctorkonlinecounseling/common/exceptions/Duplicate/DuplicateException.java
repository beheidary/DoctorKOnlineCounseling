package com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate;

import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class DuplicateException extends BaseException {


    public DuplicateException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public DuplicateException()
    {
        super(40,"Duplicate", HttpStatus.BAD_REQUEST);
    }


}
