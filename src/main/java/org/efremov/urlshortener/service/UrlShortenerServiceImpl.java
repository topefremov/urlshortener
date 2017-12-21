/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.efremov.urlshortener.commons.Property;
import org.efremov.urlshortener.domain.Url;
import java.util.function.Function;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import org.efremov.urlshortener.commons.Util;
import org.efremov.urlshortener.repository.UrlRepository;

/**
 *
 * @author efrem
 */
@RequestScoped
@Transactional
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private Client client;
    private WebTarget idGeneratorTarget;
    private Logger logger;

    @Inject
    @Property(required = true, value = "idGeneratorServiceUrl")
    private String idGeneratorServiceUrl;

    @Inject
    private UrlRepository urlRepository;
    
    @PostConstruct
    private void setUp() {
        logger = Logger.getLogger(UrlShortenerServiceImpl.class.getName());
        client = ClientBuilder.newClient();
        idGeneratorTarget = client.target(idGeneratorServiceUrl);
    }

    @PreDestroy
    private void destroy() {
        client.close();
    }

    @Override
    public Url createShortUrl(String longUrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createShortUrlAsync(String longUrl, AsyncResponse ar) {
        Future<String> queryIdGeneratorService = idGeneratorTarget
                .request(MediaType.TEXT_PLAIN)
                .async()
                .post(null, new InvocationCallback<String>() {
                    @Override
                    public void completed(String shortUrl) {
                        ar.resume(Util.buildResponse(shortUrl, Response.Status.CREATED));
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }

    ;

    @Override
    public CompletableFuture<Url> createShrotUrlAsync(String longUrl, String baseUri) {
        Function<String, Url> persistUrl
                = shortUrl -> {
                    System.out.println("before resume");
                    return urlRepository.create(new Url(baseUri + shortUrl, longUrl));
                };

        return CompletableFuture.supplyAsync(
                () -> idGeneratorTarget
                        .request(MediaType.TEXT_PLAIN)
                        .post(null, String.class))
                .thenApply(persistUrl);
    }

    @Override
    public CompletableFuture<Url> createShortUrlIfNotExistAsync(String longUrl, String baseUri) throws InterruptedException, ExecutionException {
        Function<Url, CompletableFuture<Url>> returnFoundedUrlOrCreate
                = url -> {
                    if (url == null) {
                        return createShrotUrlAsync(longUrl, baseUri);
                    }
                    return CompletableFuture.completedFuture(url);
                };
        return CompletableFuture.supplyAsync(() -> urlRepository.findByLongUrl(longUrl))
                .thenApply(returnFoundedUrlOrCreate).get();
    }

}
