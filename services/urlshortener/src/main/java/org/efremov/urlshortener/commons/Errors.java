/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.commons;

import java.util.List;

/**
 *
 * @author aaefr
 */
public class Errors<T> {
    List<T> errors;

    public Errors(List<T> errors) {
        this.errors = errors;
    }

    public List<T> getErrors() {
        return errors;
    }

    public void setErrors(List<T> errors) {
        this.errors = errors;
    }
}
