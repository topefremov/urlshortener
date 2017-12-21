/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author efrem
 */
public class Util {
    
    /** Constants **/
    
    private static final String DEFAULT_NOT_FOUND_ERROR_MESSAGE = "URL Not Found";
    private static final String DEFAULT_NOT_FOUND_ERROR_TYPE = "NotFoundError";
    private static final String DEFAULT_VALIDATION_ERROR_TYPE = "ValidationError";
    
    /** Utility methods **/
    
    public static Response buildResponse(Object entity, Status status, String mediaType) {
        return Response.status(status).entity(entity).type(mediaType)
                .build();
    }
    
    public static Response buildResponse(Object entity, Status status) {
        return Response.status(status).entity(entity).build();
    }
    
    public static Errors<Error> createNotFoundErrors(String[] messages) {
        List<Error> errors = Arrays.stream(messages).map(Util::toNotFoundError).
                collect(Collectors.toList());
        return new Errors<>(errors);        
    }
    
    public static Errors<Error> createNotFoundErrors(String message) {
        List<Error> errors = new ArrayList<>();
        errors.add(toNotFoundError(message));
        return new Errors<>(errors);
    }
    
    public static Errors<Error> createNotFoundErrors() {
        return createNotFoundErrors(DEFAULT_NOT_FOUND_ERROR_MESSAGE);
    }
    
    public static Errors<ValidationError> createValidationErrors(ConstraintViolationException exception) {
        List<ValidationError> validationErrors =  exception.getConstraintViolations().stream()
                .map(Util::createValidationError)
                .collect(Collectors.toList());
        
        return new Errors<>(validationErrors);
    }
    
    public static ValidationError createValidationError(String message, String propertyPath, Object invalidValue) {
        return new ValidationError(message, propertyPath, invalidValue, DEFAULT_VALIDATION_ERROR_TYPE);
    }
    
    public static ValidationError createValidationError(ConstraintViolation cv) {
        return createValidationError(cv.getMessage(), cv.getPropertyPath().toString(), cv.getInvalidValue());
    }
    
    public static Error toNotFoundError(String message) {
        return new Error(message, DEFAULT_NOT_FOUND_ERROR_TYPE);
    }
}
