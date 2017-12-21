/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import org.efremov.urlshortener.domain.Url;

/**
 *
 * @author efrem
 */
public interface UrlShortenerService {
    Url createShortUrl(String longUrl);
    void createShortUrlAsync(String longUrl, AsyncResponse ar);
    CompletableFuture<Url> createShrotUrlAsync(String longUrl, String baseUri);
    CompletableFuture<Response> createShortUrlIfNotExistAndGetResponseAsync(String longUrl, String baseUri);
    CompletableFuture<Url> findByLongUrl(String longUrl);
}
