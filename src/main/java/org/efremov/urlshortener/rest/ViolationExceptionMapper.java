/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response.Status;

import org.efremov.urlshortener.commons.Util;


/**
 *
 * @author aaefr
 */
@Provider
public class ViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        
        return Util.buildResponse(Util.createValidationErrors(exception), Status.BAD_REQUEST, MediaType.APPLICATION_JSON);

    }
}
