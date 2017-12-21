/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.efremov.urlshortener.domain.Url;

/**
 *
 * @author efrem
 */
@Transactional
public class UrlRepository {
    
    @PersistenceContext
    EntityManager em;
    
    public Url create(Url url) {
        em.persist(url);
        return url;
    }
    
    public Url findById(Long id) {
        return em.find(Url.class, id);
    }
    
    public Url findByLongUrl(String longUrl) {
        TypedQuery<Url> query = em.createNamedQuery(Url.FIND_BY_LONG_URL, Url.class)
                .setParameter("longUrl", longUrl);
        try {
            return query.getSingleResult();
        }
        catch(NoResultException exception) {
            return null;
        }        
    }
}
