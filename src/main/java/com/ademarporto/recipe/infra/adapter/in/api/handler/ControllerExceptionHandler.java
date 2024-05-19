package com.ademarporto.recipe.infra.adapter.in.api.handler;

import com.ademarporto.recipe.domain.exception.RecipeNotFoundException;
import com.ademarporto.recipe.rest.spec.spec.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

import static com.ademarporto.recipe.domain.exception.ErrorMessage.METHOD_ARGUMENT_NOT_VALID_CODE;
import static com.ademarporto.recipe.domain.exception.ErrorMessage.METHOD_ARGUMENT_NOT_VALID_CODE_MESSAGE;
import static com.ademarporto.recipe.domain.exception.ErrorMessage.NOT_READABLE_REQUEST_BODY_CODE;
import static com.ademarporto.recipe.domain.exception.ErrorMessage.NOT_READABLE_REQUEST_BODY_MESSAGE;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error(String.format("Request body exception. %n Cause [ %s ]", ex.getMessage()));

        return createResponseEntity(NOT_READABLE_REQUEST_BODY_CODE, NOT_READABLE_REQUEST_BODY_MESSAGE, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return createResponseEntity(METHOD_ARGUMENT_NOT_VALID_CODE,
                String.format(METHOD_ARGUMENT_NOT_VALID_CODE_MESSAGE,
                        Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField(),
                        Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage()),
                status);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    protected ResponseEntity<Object> recipeNotFoundException(RecipeNotFoundException ex, WebRequest request) {
        return createResponseEntity(ex.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> createResponseEntity(String errorCode, String errorMessage, HttpStatusCode status) {
        var error = new Error();
        error.setCode(errorCode);
        error.setMessage(errorMessage);
        return new ResponseEntity<>(error, status);
    }

}
