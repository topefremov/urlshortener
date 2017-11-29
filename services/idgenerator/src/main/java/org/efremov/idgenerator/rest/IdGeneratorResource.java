package org.efremov.idgenerator.rest;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.efremov.idgenerator.ejb.IDGenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author efrem
 */
@Path("/newId")
@Produces(MediaType.APPLICATION_JSON)
public class IdGeneratorResource {
    
    @Inject
    IDGenerator idGenerator;
    
    @GET
    public Response getNewId() {
        return Response.ok().entity(idGenerator.getId()).build();
    }
}
