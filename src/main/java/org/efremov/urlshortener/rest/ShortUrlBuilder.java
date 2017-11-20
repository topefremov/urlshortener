/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import org.efremov.urlshortener.domain.URLEntity;

/**
 *
 * @author efrem
 */
public class ShortUrlBuilder {

    String baseUri;

    public ShortUrlBuilder(String baseUri) {
        this.baseUri = baseUri;
    }

    public UrlResponse fromURLEntity(URLEntity urlObj) {
        return new UrlResponse(urlObj.getLongURL(), genShortUrl(urlObj));
    }

    private String genShortUrl(URLEntity urlObj) {
        return baseUri + urlObj.getId();
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

}
