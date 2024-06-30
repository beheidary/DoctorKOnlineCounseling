package com.doctork.doctorkonlinecounseling.api.controllers.exceptionHandler;

import com.doctork.doctorkonlinecounseling.common.Messages;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.ErrorDetail;
import com.doctork.doctorkonlinecounseling.common.exceptions.FieldItemError;
import com.doctork.doctorkonlinecounseling.common.exceptions.InputErrorDetail;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.InputException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Messages messages;

    public RestExceptionHandler(Messages messages) {
        this.messages = messages;
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {


        String message = ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), 500, message);
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(InputException.class)
    protected ResponseEntity<InputErrorDetail> handleInputException(InputException ex, WebRequest request) {



        List<FieldItemError> errorList = new ArrayList<>();

        ex.getErrors().
                forEach(error->
                        errorList.add(
                                new FieldItemError(error.getObjectName(), error.getDefaultMessage(), null)));

        InputErrorDetail inputErrorDetail = new InputErrorDetail(LocalDateTime.now(), 1 ,"input error", errorList);

        return new ResponseEntity<>(inputErrorDetail, HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorDetail> handleGeneral(BaseException ex, WebRequest request) {

        String message = messages.getMessage(ex.getMessage());

        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getErrorCode(), message);

        return new ResponseEntity<>(errorDetail, ex.getStatus());

    }




    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<InputErrorDetail> handleViolation(ConstraintViolationException ex, WebRequest request) {

        List<FieldItemError> errorList = new ArrayList<>();

        ex.getConstraintViolations().
                forEach(error->
                        errorList.add(
                                new FieldItemError(error.getPropertyPath().toString(), error.getMessage(), error.getInvalidValue())));

        InputErrorDetail inputErrorDetail = new InputErrorDetail(LocalDateTime.now(), 1 ,"input error", errorList);

        return new ResponseEntity<>(inputErrorDetail, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorDetail> handleAccessDenied(Exception ex, WebRequest request) {

        String message = "You do not have the required access";

        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), 2, message);

        return new ResponseEntity<>(errorDetail, HttpStatus.FORBIDDEN);

    }


    //@ExceptionHandler(MethodArgumentNotValidException.class)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDetail> handle(Exception ex, WebRequest request) {

        String message = "this one is uncaught, please immediately inform Behnam";

        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), 1000, message);

        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);

    }







}

