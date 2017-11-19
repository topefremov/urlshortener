/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aaefr
 */
@Entity
public class URL implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shortUrl;
    String LongURL;

    public Long getShortUrl() {
        return shortUrl;
    }

    public void setId(Long shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortUrl != null ? shortUrl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof URL)) {
            return false;
        }
        URL other = (URL) object;
        if ((this.shortUrl == null && other.shortUrl != null) || (this.shortUrl != null && !this.shortUrl.equals(other.shortUrl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.efremov.urlshortener.domain.URL[ id=" + shortUrl + " ]";
    }
    
}
