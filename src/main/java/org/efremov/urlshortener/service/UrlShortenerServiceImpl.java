/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.service;

import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import org.efremov.urlshortener.domain.Url;
import java.util.function.Function;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import org.efremov.urlshortener.commons.Util;
import org.efremov.urlshortener.newpackage.client.IdGeneratorClient;
import org.efremov.urlshortener.repository.UrlRepository;

/**
 *
 * @author efrem
 */
@Stateless
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Inject
    private UrlRepository urlRepository;
    
    @Inject
    private IdGeneratorClient idGeneratorClient;
    
    @Override
    public Url createShortUrl(String longUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public CompletableFuture<Url> createShrotUrlAsync(String longUrl, String baseUri) {
        Function<String, Url> persistUrl
                = shortUrl -> {
                    return urlRepository.create(new Url(baseUri + shortUrl, longUrl));
                };
        
        return idGeneratorClient.retrieveIdAsync()
                .thenApplyAsync(persistUrl);
    }

    @Override
    public CompletableFuture<Response> createShortUrlIfNotExistAsync(String longUrl, String baseUri) {
        Function<Url, CompletableFuture<Response>> returnFoundedUrlOrCreate
                = url -> {
                    if (url == null) {
                        return createShrotUrlAsync(longUrl, baseUri)
                                .thenApply(this::buildCreatedResponse);
                    }
                    return CompletableFuture.completedFuture(Util.buildResponse(url, Response.Status.OK));
                };
        return findByLongUrl(longUrl)
                .thenCompose(returnFoundedUrlOrCreate);
    }
    
    private Response buildCreatedResponse(Url url) {
        return Util.buildResponse(url, Response.Status.CREATED);
    }
    
    @Override
    public CompletableFuture<Url> findByLongUrl(String longUrl) {
        return CompletableFuture.supplyAsync(() -> urlRepository.findByLongUrl(longUrl));
    }

}
