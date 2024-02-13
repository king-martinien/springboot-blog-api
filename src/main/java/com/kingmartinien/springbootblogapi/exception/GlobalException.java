package com.kingmartinien.springbootblogapi.exception;

import com.kingmartinien.springbootblogapi.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                    WebRequest webRequest) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), resourceNotFoundException.getMessage(),
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }

    @ExceptionHandler(BlogApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBlogApiException(BlogApiException blogApiException, WebRequest webRequest) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), blogApiException.getMessage(),
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGlobalException(Exception exception, WebRequest webRequest) {
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(),
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }

}
