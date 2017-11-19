/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author aaefr
 */
@Path("/url")
@Produces(MediaType.APPLICATION_JSON)
public class URLResource {

    /**
     * ENDPOINTS
     *
     * @param url
     * @return *
     */
    @GET
    @Path("/new/{url}")
    public Response getShortUrl(@PathParam("url")
            @URL(message = "Should be proper URL format") String url) {
        System.out.println(url);
        return Response.ok().build();
    }
}
