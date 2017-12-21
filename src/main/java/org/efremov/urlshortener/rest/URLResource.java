/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.efremov.urlshortener.commons.Util;

import org.efremov.urlshortener.repository.UrlRepository;
import org.efremov.urlshortener.domain.Url;
import org.efremov.urlshortener.service.UrlShortenerService;

/**
 *
 * @author aaefr
 */
@Api
@Path("url")
@Produces(MediaType.APPLICATION_JSON)
public class URLResource {

    @Inject
    UrlRepository urlRepository;

    @Inject
    UrlShortenerService urlShortenerService;

    @Context
    UriInfo uriInfo;

    private String baseUri;

    @PostConstruct
    public void init() {
        baseUri = uriInfo.getBaseUri().toString();

    }

//    @ApiOperation(value = "Redirect client to URL associeted with provided shortUrl id")
//    @ApiResponses(value = {
//        @ApiResponse(code = 404, message = "Short URL id not found or provided path is incorrect"),
//        @ApiResponse(code = 304, message = "Request has been processed successfuly. Short URL found and request will be redirected to corresponding URL")
//    })
//    @GET
//    @Path("/{id}")
//    public Response redirectToLongUrl(@PathParam("id") Long id) {
//        URLS urlObj = urlRepository.findById(id);
//        if (urlObj != null) {
//            return Response.temporaryRedirect(URI.create(urlObj.getLongURL())).build();
//        } else {
//            return Util.buildResponse(Util.createNotFoundErrors(), Status.NOT_FOUND);
//        }
//    }
    /**
     * ENDPOINTS
     *
     * @param url
     * @param uriInfo
     * @return *
     */
    @ApiOperation(value = "Creates short URL",
            notes = "Correct URL must be provided")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid URL provided")
        ,
        @ApiResponse(code = 201, message = "Request has been processed successfully. New short URL created.")
    })
    @POST
    @Asynchronous
    @Consumes("application/json")
    public void createShortUrl(@Suspended final AsyncResponse ar, @Valid Url url) {
        urlShortenerService.createShortUrlIfNotExistAndGetResponseAsync(url.getLongURL(), baseUri)
                .thenAccept(ar::resume);
    }
}
