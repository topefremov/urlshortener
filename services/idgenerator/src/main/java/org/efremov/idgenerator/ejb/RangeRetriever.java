/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.idgenerator.ejb;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import com.ecwid.consul.v1.ConsulClient;
import javax.annotation.PostConstruct;

/**
 *
 * @author efrem
 */
@Startup
@Singleton
public class RangeRetriever {
    ConsulClient client = new ConsulClient("localhost");
    
    @PostConstruct
    private void init() {
        
    }
}
