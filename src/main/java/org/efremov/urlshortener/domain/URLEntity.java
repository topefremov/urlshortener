/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.efremov.urlshortener.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aaefr
 */
@Entity
@Table(name = "url")
@NamedQuery(name = URLEntity.FIND_BY_LONG_URL, query = "select u from URLEntity u where u.longURL = :longUrl")
public class URLEntity implements Serializable {
    
    public static final String FIND_BY_LONG_URL = "URL.findByLongUrl";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String longURL;

    public URLEntity() {
    }
    
    public URLEntity(String longURL) {
        this.longURL = longURL;
    }
    
    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof URLEntity)) {
            return false;
        }
        URLEntity other = (URLEntity) object;
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
