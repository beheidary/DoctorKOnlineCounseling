package com.doctork.doctorkonlinecounseling.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import jakarta.annotation.Resource;
import java.util.Locale;


public class Messages
{


    @Resource(name = "messageSource")
    private MessageSource messageSource;

    private Logger logger = LogManager.getLogger(Messages.class);

    public String getMessage(String key)
    {
        Locale locale = LocaleContextHolder.getLocale();

        String message = key;
        try
        {

            message =messageSource.getMessage(key,null,locale);

        }catch(Exception ex)
        {

            logger.debug(ex.getMessage());

        }

        return message;
    }

}
