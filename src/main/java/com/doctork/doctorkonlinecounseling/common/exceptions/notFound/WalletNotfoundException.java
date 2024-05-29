package com.doctork.doctorkonlinecounseling.common.exceptions.notFound;

import org.springframework.http.HttpStatus;

public class WalletNotfoundException extends NotFoundException{


    public WalletNotfoundException(int errorCode, String message, HttpStatus status)
    {
        super(errorCode, message, status);
    }

    public WalletNotfoundException()
    {
        super(46,"Wallet.NotFound", HttpStatus.BAD_REQUEST);
    }

}
