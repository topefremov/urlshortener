/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.newpackage.client;

import java.util.concurrent.CompletableFuture;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.efremov.urlshortener.commons.Property;

/**
 *
 * @author efrem
 */
@Stateless
public class IdGeneratorClientImpl implements IdGeneratorClient {
    private Client client;
    private WebTarget idGeneratorTarget;

    @Inject
    @Property(required = true, value = "idGeneratorServiceUrl")
    private String idGeneratorServiceUrl;
    
    @PostConstruct
    private void setUp() {
        client = ClientBuilder.newClient();
        idGeneratorTarget = client.target(idGeneratorServiceUrl);
    }

    @PreDestroy
    private void destroy() {
        client.close();
    }

    @Override
    public String retrieveId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompletableFuture<String> retrieveIdAsync() {
        return CompletableFuture.supplyAsync(
                () -> idGeneratorTarget
                        .request(MediaType.TEXT_PLAIN)
                        .post(null, String.class));
    }

}
