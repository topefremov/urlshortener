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

import org.efremov.urlshortener.domain.URLS;

/**
 *
 * @author efrem
 */
@Transactional
public class UrlRepository {
    
    @PersistenceContext
    EntityManager em;
    
    public URLS create(URLS url) {
        em.persist(url);
        return url;
    }
    
    public URLS findById(Long id) {
        return em.find(URLS.class, id);
    }
    
    public URLS findByLongUrl(String longUrl) {
        TypedQuery<URLS> query = em.createNamedQuery(URLS.FIND_BY_LONG_URL, URLS.class)
                .setParameter("longUrl", longUrl);
        try {
            return query.getSingleResult();
        }
        catch(NoResultException exception) {
            return null;
        }
        
    }
}
