/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.SiteInfo;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Obaro I. Johnson
 */
@Stateless
@Remote(IEntrySiteRemote.class)
public class IEntrySite implements IEntrySiteRemote {

  //all private fields
 
   private static boolean isCreate;
   @PersistenceContext(unitName="ServerEJBPU")
   private EntityManager siteManager;
    private String error;


    
public IEntrySite()
    {//call the super class constructor
        super();        
        //initialised the fields;
        isCreate = false;
     
      
    }

//Using the synchronized modifier to avoid prevent interference of client trying to modifier or create a site with same id or accessing
//the same variable
    @Override
    public synchronized boolean createSite(SiteInfo site) {
      
        isCreate =false;
        if(this.isValidate(site))
        {
            this.createNewRecord(site);
            return isCreate;
        }
        return false;
                  
    }//end method
    
    
    //function that will check if site details are valid
    
    private boolean isValidate(SiteInfo siteinfo)
    {
       return siteinfo.validate();
    }
    
    private void createNewRecord(SiteInfo siteinfo) 
    {
        if(this.isExists(siteinfo))
        {
          
           isCreate= false;
           this.error = "Oops this site already exists with the given name or identity number";
           return ;
        }
        //add the site to the persistance database      
        this.siteManager.persist(siteinfo);
        isCreate= true;       
                        
    }
    
    
    private boolean isExists(SiteInfo info)
    { boolean isOkay=false;
     SiteInfo infoT=null ;
       String eql= "Select s from SiteInfo s where s.id ='"+info.getId().toLowerCase()+"' OR s.name= '"+info.getName().toLowerCase().trim()+"'";
       try
       {
        infoT = (SiteInfo) this.siteManager.createQuery(eql).getSingleResult();
       }
     catch(NoResultException err)
      {
         this.error =err.getCause().toString();
      }
      if(infoT !=null) 
      {
          //iterator the object and compare the name and the id if they matched 
         isOkay=true;
      }
    
      return isOkay;
        
    }

    //this return the current error message
    @Override
    public final synchronized String  getErrorMessage()  {
        
        return this.error;
    }

    @Override
    public final synchronized List<SiteInfo> getSites() {
        
        String sql="From SiteInfo";
       return (List<SiteInfo>) this.siteManager.createQuery(sql).getResultList();
    }
    
}
