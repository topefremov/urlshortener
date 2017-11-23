/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author aaefr
 */
@Entity
@Table(name = "url")
@NamedQuery(name = URLS.FIND_BY_LONG_URL, query = "select u from URLS u where u.longURL = :longUrl")
public class URLS implements Serializable {
    
    public static final String FIND_BY_LONG_URL = "URL.findByLongUrl";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Column(nullable = false, unique = true)
    private String longURL;
    @Transient
    private String shortURL;
    
    
    /** constructors **/

    public URLS() {
    }
    
    public URLS(String longURL) {
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
        if (!(object instanceof URLS)) {
            return false;
        }
        URLS other = (URLS) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.efremov.urlshortener.domain.URL[ id=" + id + " ]";
    }
}
