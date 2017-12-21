/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.efremov.urlshortener.domain.Url;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author aaefr
 */
@Entity
@Table(name = "url")
@NamedQuery(name = Url.FIND_BY_LONG_URL, query = "select u from Url u where u.longURL = :longUrl")
public class Url implements Serializable {
    
    public static final String FIND_BY_LONG_URL = "URL.findByLongUrl";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, unique = true, name = "LONG_URL")
    @URL
    private String longURL;

    @Column(name = "SHORT_URL")
    private String shortURL;
    
    
    /** constructors **/

    public Url() {
    }
    
    public Url(String shortUrl, String longUrl) {
        this.shortURL = shortUrl;
        this.longURL = longUrl;
    }
    
    public Url(String longURL) {
        this.longURL = longURL;
    }
        
    /** getters and setters **/
    
    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /** hashCode and equals **/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Url)) {
            return false;
        }
        Url other = (Url) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "org.efremov.urlshortener.domain.URL[ id=" + id + " ]";
    }
}
