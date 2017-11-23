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
import java.net.URI;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.hibernate.validator.constraints.URL;

import org.efremov.urlshortener.commons.Util;
import org.efremov.urlshortener.domain.URLS;
import org.efremov.urlshortener.repository.UrlRepository;

/**
 *
 * @author aaefr
 */
@Api
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class URLResource {

    @Inject
    UrlRepository urlRepository;
    
    @Context
    UriInfo uriInfo;
    
    private String baseUri;
    
    @PostConstruct
    public void init() {
        baseUri = uriInfo.getBaseUri().toString();
        
    }
    
    @ApiOperation(value = "Redirect client to URL associeted with provided shortUrl id")
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Short URL id not found or provided path is incorrect"),
        @ApiResponse(code = 304, message = "Request has been processed successfuly. Short URL found and request will be redirected to corresponding URL")
    })
    @GET
    @Path("/{id}")
    public Response redirectToLongUrl(@PathParam("id") Long id) {
        URLS urlObj = urlRepository.findById(id);
        if (urlObj != null) {
            return Response.temporaryRedirect(URI.create(urlObj.getLongURL())).build();
        } else {
            return Util.buildResponse(Util.createNotFoundErrors(), Status.NOT_FOUND);
        }
    }

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
        @ApiResponse(code = 400, message = "Invalid URL provided"),
        @ApiResponse(code = 200, message = "Request has been processed successfully"),
        @ApiResponse(code = 201, message = "Request has been processed successfully. New short URL created.")
    })
    @GET
    @Path("/new/{lUrl}")
    public Response getShortUrl(@PathParam("lUrl") @URL(message = "Should be proper URL format") String lUrl) {
        Status status = Status.OK;
        URLS urlsObj = urlRepository.findByLongUrl(lUrl);
        if (urlsObj == null) {
            urlsObj = new URLS(lUrl);
            urlsObj = urlRepository.create(urlsObj);
            status = Status.CREATED;
        }
        urlsObj.setShortURL(baseUri + urlsObj.getId());
        return Util.buildResponse(urlsObj, status);
    }
}
