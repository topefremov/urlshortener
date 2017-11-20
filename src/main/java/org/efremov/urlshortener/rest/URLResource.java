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
import org.efremov.urlshortener.domain.URLEntity;
import org.efremov.urlshortener.repository.UrlRepository;

/**
 *
 * @author aaefr
 */
@Api(description = "Root REST Endpoint", tags = {"urlshortner"})
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class URLResource {

    @Inject
    UrlRepository urlRepository;
    
    @Context
    UriInfo uriInfo;
    
    ShortUrlBuilder sub;
    
    @PostConstruct
    public void init() {
        sub = new ShortUrlBuilder(uriInfo.getBaseUri().toString());
        
    }
    
    @ApiOperation(value = "Redirect client to URL associeted with provided shortUrl id")
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Short URL id not found"),
        @ApiResponse(code = 401, message = "Invalid login/password")
    })
    @GET
    @Path("/{id}")
    public Response redirectToLongUrl(@PathParam("id") Long id) {
        URLEntity urlObj = urlRepository.findById(id);
        if (urlObj != null) {
            return Response.temporaryRedirect(URI.create(urlObj.getLongURL())).build();
        } else {
            return Util.buildResponse(NotFoundErrorsBuilder.create(), Status.NOT_FOUND);
        }
    }

    /**
     * ENDPOINTS
     *
     * @param url
     * @param uriInfo
     * @return *
     */
    @GET
    @Path("/new/{url}")
    public Response getShortUrl(@PathParam("url")
            @URL(message = "Should be proper URL format") String url,
            @Context UriInfo uriInfo) {
        URLEntity urlObj = urlRepository.findByLongUrl(url);
        if (urlObj == null) {
            urlObj = new URLEntity(url);
            urlRepository.create(urlObj);
            return Util.buildResponse(sub.fromURLEntity(urlObj), Status.CREATED);
        }
        return Util.buildResponse(sub.fromURLEntity(urlObj), Status.OK);
    }
}
