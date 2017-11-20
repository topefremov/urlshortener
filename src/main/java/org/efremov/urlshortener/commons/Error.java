/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.commons;

/**
 *
 * @author efrem
 */
public class Error {
    private String message;
    private String errorType;
    
    public Error() {
    }

    public Error(String message, String errorType) {
        this.message = message;
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
    
    
}
