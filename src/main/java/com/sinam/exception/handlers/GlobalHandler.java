package com.sinam.exception.handlers;

import com.sinam.exception.WrongPermissionException;
import com.sinam.model.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongPermissionException.class)
    public ErrorInfo noSuchBillingCodeExceptionHandler(
            HttpServletRequest request, WrongPermissionException exception) {
        return new ErrorInfo(request.getRequestURL().toString(), exception.getLocalizedMessage());
    }

}