package com.wordpress.fcosfc.test.jpa.embedded.sample.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Paco Saucedo
 */
@Entity
@Table(name = "LOCATIONS")
public class Location implements Serializable {
    
    @Id
    @Column(name = "LOCATION_ISO_CODE", length = 5)
    @Size(min = 5, max = 5)
    private String isoCode;
    
    @Column(length = 100, nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;
    
    @JoinColumn(name = "COUNTRY_ISO_CODE", referencedColumnName = "COUNTRY_ISO_CODE")
    @ManyToOne(optional = false)
    private Country country;

    public Location() {
    }

    public Location(String isoCode, String name, Country country) {
        this.isoCode = isoCode;
        this.name = name;
        this.country = country;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.isoCode != null ? this.isoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if ((this.isoCode == null) ? (other.isoCode != null) : !this.isoCode.equals(other.isoCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "isoCode=" + isoCode + ", name=" + name + '}';
    }
        
}

