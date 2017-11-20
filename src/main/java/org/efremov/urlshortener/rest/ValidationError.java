/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import org.efremov.urlshortener.commons.Error;

/**
 *
 * @author aaefr
 */
public class ValidationError extends Error {
    private String propertyPath;
    private Object invalidValue;

    public ValidationError() {
    }

    public ValidationError(String propertyPath, Object invalidValue, String message, String errorType) {
        super(message, errorType);
        this.propertyPath = propertyPath;
        this.invalidValue = invalidValue;
    }
    
    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }
}
