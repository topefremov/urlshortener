/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.efremov.urlshortener.commons.Error;
import org.efremov.urlshortener.commons.Errors;

/**
 *
 * @author efrem
 */
public class NotFoundErrorsBuilder {
    private static String defaultMessage = "URL Not Found";
    private static String defaultErrorType = "NotFoundError";
    
    public static Errors<Error> create(String[] messages) {
        List<Error> errors = Arrays.stream(messages).map(NotFoundErrorsBuilder::toNotFoundError).
                collect(Collectors.toList());
        return new Errors<>(errors);        
    }
    
    public static Errors<Error> create(String message) {
        List<Error> errors = new ArrayList<>();
        errors.add(toNotFoundError(message));
        return new Errors<>(errors);
    }
    
    public static Errors<Error> create() {
        return create(defaultMessage);
    }
    
    public static Error toNotFoundError(String message) {
        return new Error(message, defaultErrorType);
    }
}
