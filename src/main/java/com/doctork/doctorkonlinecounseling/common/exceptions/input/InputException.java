package com.doctork.doctorkonlinecounseling.common.exceptions.input;


import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputException extends BaseException
{

    private List<ObjectError> errors;

    public InputException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public InputException()
    {
        super(60,"Input is incorrect", HttpStatus.BAD_REQUEST);
    }

    public InputException(String message)
    {
        super(60,message, HttpStatus.BAD_REQUEST);
    }

    public InputException(List<ObjectError> errors) {

        super(60,"Input is incorrect", HttpStatus.BAD_REQUEST);
        this.errors = errors;

    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}