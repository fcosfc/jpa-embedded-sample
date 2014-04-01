package com.wordpress.fcosfc.test.jpa.embedded.sample.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Paco Saucedo
 */
@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {
    
    @Id
    @Column(name = "COUNTRY_ISO_CODE", length = 2)
    @Size(min = 2, max = 2)
    private String isoCode;
    
    @Column(length = 100, nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Location> locationsList;
    
    public Country() {
    }

    public Country(String isoCode, String name) {
        this.isoCode = isoCode;
        this.name = name;
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

    public List<Location> getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(List<Location> locationsList) {
        this.locationsList = locationsList;
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
        final Country other = (Country) obj;
        if ((this.isoCode == null) ? (other.isoCode != null) : !this.isoCode.equals(other.isoCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "isoCode=" + isoCode + ", name=" + name + '}';
    }
        
}
