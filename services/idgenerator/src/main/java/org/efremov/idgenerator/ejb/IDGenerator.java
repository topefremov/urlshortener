/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.idgenerator.ejb;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author efrem
 */
@Startup
@Singleton
public class IDGenerator {
    
    IdGenerator idGen;
    
    /** Life-cycle methods **/

    @PostConstruct
    private void init() {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        idGen = hazelcastInstance.getIdGenerator("newId");
        idGen.init(100);
    }
    
    /** Business logic **/
    
    public long getId() {
        return idGen.newId();
    }
    
    /** Getters and Setters **/
    
    public IdGenerator getIdGen() {
        return idGen;
    }
}
