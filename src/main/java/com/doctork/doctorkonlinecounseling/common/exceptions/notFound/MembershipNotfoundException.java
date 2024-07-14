package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;

public class MembershipNotfoundException extends NotFoundException{

    public MembershipNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public MembershipNotfoundException()
    {
        super(90,"Membership.NotFound", HttpStatus.BAD_REQUEST);
    }
}
