/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

/**
 *
 * @author efrem
 */
public class UrlResponse {
    
    private String LongUrl;
    private String ShortUrl;
    
    public UrlResponse() {
    }

    public UrlResponse(String LongUrl, String ShortUrl) {
        this.LongUrl = LongUrl;
        this.ShortUrl = ShortUrl;
    }
    
    public String getLongUrl() {
        return LongUrl;
    }

    public void setLongUrl(String LongUrl) {
        this.LongUrl = LongUrl;
    }

    public String getShortUrl() {
        return ShortUrl;
    }

    public void setShortUrl(String ShortUrl) {
        this.ShortUrl = ShortUrl;
    }
}
