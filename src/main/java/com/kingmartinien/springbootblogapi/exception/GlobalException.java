package com.kingmartinien.springbootblogapi.exception;

import com.kingmartinien.springbootblogapi.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                    WebRequest webRequest) {
        return new ErrorDto(HttpStatus.NOT_FOUND.value(),
                Collections.singletonList(resourceNotFoundException.getMessage()),
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }

    @ExceptionHandler(BlogApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBlogApiException(BlogApiException blogApiException, WebRequest webRequest) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), Collections.singletonList(blogApiException.getMessage()),
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGlobalException(Exception exception, WebRequest webRequest) {
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), Collections.singletonList(exception.getMessage()),
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest) {
        List<String> errorMessages = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors()
                .forEach(error -> errorMessages.add(error.getDefaultMessage()));
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), errorMessages,
                new java.util.Date().toInstant(), webRequest.getDescription(false));
    }
}
