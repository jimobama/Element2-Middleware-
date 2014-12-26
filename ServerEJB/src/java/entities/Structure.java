/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import helps.Validator;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Obaro I. Johnson
 */
@Entity
@Table(name = "Structure")
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String type;
    private String description;
    private String location;
    private String name;

    private Long siteId;
    @Transient
    private boolean status;
    @Transient
    private String error;

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
        if (!(object instanceof Structure)) {
            return false;
        }
        Structure other = (Structure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Structure[ id=" + id + " ]";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public boolean validated() {
        boolean isOkay = false;
        if (this.getName().trim().isEmpty() || !Validator.isMatch("^[a-zA-Z\\_0-9 ]+$", name)) {
            this.error = "Invalid structure name please specify a valid one please!";
        } else if (!Validator.isMatch("^[a-zA-Z\\_ 0-9]+$", this.getType().trim()) || this.getType().trim().isEmpty()) {
            this.error = "The character entered in the type field is invalid!";

        } else if (this.getDescription().isEmpty()) {
            this.error = "Please enter a description for the structure!";
        } else if (this.getLocation().isEmpty()) {
            this.error = "Please enter a valid location address for the site including the postcode!";
        } else {
            isOkay = true;
        }

        return isOkay;
    }

    public String getErrorMessage() {
        return error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
