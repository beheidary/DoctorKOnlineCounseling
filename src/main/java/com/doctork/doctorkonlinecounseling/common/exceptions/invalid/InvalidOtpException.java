package com.doctork.doctorkonlinecounseling.common.exceptions.invalid;

import org.springframework.http.HttpStatus;

public class InvalidOtpException extends InvalidException{


    public InvalidOtpException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public InvalidOtpException()
    {
        super(73,"Otp.Invalid or Expired", HttpStatus.BAD_REQUEST);
    }


}
