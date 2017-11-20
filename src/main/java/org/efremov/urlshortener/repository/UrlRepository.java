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

import org.efremov.urlshortener.domain.URLEntity;

/**
 *
 * @author efrem
 */
@Transactional
public class UrlRepository {
    
    @PersistenceContext
    EntityManager em;
    
    public URLEntity create(URLEntity url) {
        em.persist(url);
        return url;
    }
    
    public URLEntity findById(Long id) {
        return em.find(URLEntity.class, id);
    }
    
    public URLEntity findByLongUrl(String longUrl) {
        TypedQuery<URLEntity> query = em.createNamedQuery(URLEntity.FIND_BY_LONG_URL, URLEntity.class)
                .setParameter("longUrl", longUrl);
        try {
            return query.getSingleResult();
        }
        catch(NoResultException exception) {
            return null;
        }
        
    }
}
