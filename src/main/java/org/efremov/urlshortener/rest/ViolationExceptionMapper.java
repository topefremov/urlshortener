/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response.Status;

import org.efremov.urlshortener.commons.Errors;
import org.efremov.urlshortener.commons.Util;


/**
 *
 * @author aaefr
 */
@Provider
public class ViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ValidationError> errors = exception.getConstraintViolations().stream()
                .map(this::toValidationError)
                .collect(Collectors.toList());
        return Util.buildResponse(new Errors<ValidationError>(errors), Status.BAD_REQUEST, MediaType.APPLICATION_JSON);

    }

    protected ValidationError toValidationError(ConstraintViolation constraintViolation) {
        ValidationError error = new ValidationError();
        error.setErrorType("ValidationError");
        error.setPropertyPath(constraintViolation.getPropertyPath().toString());
        error.setMessage(constraintViolation.getMessage());
        error.setInvalidValue(constraintViolation.getInvalidValue());
        return error;
    }
}
