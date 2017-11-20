/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.commons;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author efrem
 */
public class Util {
    public static Response buildResponse(Object entity, Status status, String mediaType) {
        return Response.status(status).entity(entity).type(mediaType)
                .build();
    }
    
    public static Response buildResponse(Object entity, Status status) {
        return Response.status(status).entity(entity).build();
    }
}
