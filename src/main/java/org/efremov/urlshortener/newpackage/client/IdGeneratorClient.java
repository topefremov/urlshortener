/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.newpackage.client;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author efrem
 */
public interface IdGeneratorClient {
   public String retrieveId();
   public CompletableFuture<String> retrieveIdAsync();
}
