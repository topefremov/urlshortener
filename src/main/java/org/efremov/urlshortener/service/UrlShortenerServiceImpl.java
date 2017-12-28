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
        
        System.out.println("Inside createShortUrlAsync");

        return idGeneratorClient.retrieveIdAsync()
                .thenApply(persistUrl);
    }

    @Override
    public CompletableFuture<Response> createShortUrlIfNotExistAndGetResponseAsync(String longUrl, String baseUri) {
        Function<Url, CompletableFuture<Response>> returnFoundedUrlOrCreate
                = url -> {
                    if (url == null) {
                        return createShrotUrlAsync(longUrl, baseUri)
                                .thenApply(_url -> Util.buildResponse(_url, Response.Status.CREATED));
                    }
                    return CompletableFuture.completedFuture(url)
                            .thenApply(_url -> Util.buildResponse(_url, Response.Status.OK));
                };
        System.out.println("Inside createShortUrlIfNotExistAndGetResponseAsync");
        return findByLongUrl(longUrl)
                .thenCompose(returnFoundedUrlOrCreate);
    }

    @Override
    public CompletableFuture<Url> findByLongUrl(String longUrl) {
        return CompletableFuture.supplyAsync(() -> urlRepository.findByLongUrl(longUrl))
                .thenApply(url -> url);
    }

}
