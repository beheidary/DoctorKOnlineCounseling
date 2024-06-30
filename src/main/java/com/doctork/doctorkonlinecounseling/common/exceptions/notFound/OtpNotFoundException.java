package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class OtpNotFoundException extends BaseException {

    public OtpNotFoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public OtpNotFoundException()
    {
        super(81,"Otp.NotFound", HttpStatus.BAD_REQUEST);
    }

}
