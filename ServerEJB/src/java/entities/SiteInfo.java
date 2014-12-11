/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import helps.Validator;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Obaro I. Johnson
 */
@Entity
@Table(name="SiteInfo")
public class SiteInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column
  // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
   @Column  
   private String name;
   @Column 
   private String flag;
    @Column 
   private String region;
    private String error;
    
    
     //setters and getters
    public void set(String id, String name, String reg, String f) {
        this.setFlag(f);
        this.setId(id);
        this.setName(name);
        this.setRegion(reg);
        error="";
    }

    public void setFlag(String flg) {
        this.flag = flg;
    }

     public void setName(String aname) {
        this.name = aname;
    }

    public void setRegion(String aregion) {
        this.region = aregion;
    }

    //getters
    public String getFlag() {
        return this.flag;
    }

 
    public String getName() {
        return this.name;
    }

    public String  getRegion() {
        return this.region;
    }

    //the method to check if the class object are same
   
    public int compareTo(SiteInfo t) {
        return this.getId().compareToIgnoreCase(t.getId());
    }

    // the method will validate the object attributes
    public boolean validate() {
        boolean isOkay = false;
        if (!Validator.isMatch("^[a-zA-Z0-9\\_\\.]+$", this.getId())) {
            this.setErrorMessage("Enter a valid site identity in the right format!");
        } else if (!Validator.isMatch("^[a-zA-Z\\.]+[a-zA-Z\\.\\_ 0-9]+$", this.getName())) {
            this.setErrorMessage("Enter a valid site name please!");
        } else if (!Validator.isMatch("^[a-zA-Z 0-9]+$", this.getRegion())) {            
            this.setErrorMessage("Select or enter a valid region please!");
        }else if(!Validator.isMatch("^[a-zA-Z 0-9 \\.\\_]+$", this.getFlag()))
        {
            this.setErrorMessage("Select or enter site flag");
        }
       else
        {
            isOkay=true;
        }


        return isOkay;
    }
   

    public String getId() {
        return id;
    }

    public void setId(String  id) {
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
        if (!(object instanceof SiteInfo)) {
            return false;
        }
        SiteInfo other = (SiteInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
   

    @Override
    public String toString() {
        return "entities.SiteInfo[ id=" + id + " ]";
    }
    
    
    // methods
    public String getErrorMessage() {
        return error;
    }

    private void setErrorMessage(String e) {
        this.error = e;
    }
    
    
}