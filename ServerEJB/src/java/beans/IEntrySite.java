/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Site;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.ejb.FinderException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

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
   private EntityManager siteManager ;
    private String error;


    
public IEntrySite()
    {    
     
        isCreate = false;
     
      
    }

//Using the synchronized modifier to avoid prevent interference of client trying to modifier or create a site with same id or accessing
//the same variable
    @Override
    public synchronized  boolean  createSite(Site site) throws FinderException {
     try
     {
        if(this.isExists(site) == false){
             this.createNewRecord(site);    
             return isCreate;
        }
     }
     catch(FinderException err)
     {
         throw err;
     }
    
     return false;
                  
    }//end method
    
    
    //function that will check if site details are valid
    
    private boolean isValidate(Site siteinfo)
    {
       return siteinfo.validate();
    }
    
    private void createNewRecord(Site info) 
    {
        isCreate=false;
        this.siteManager.persist(info);
        isCreate=true;
                                   
    }



    //this return the current error message
    @Override
    public synchronized String   getErrorMessage()  {
        
        return this.error;
    }

    @Override
    public synchronized  List<Site> getSites() throws FinderException
    {
                   
           Query query= this.siteManager.createQuery("Select s From Site s ");
                         
           List<Site>  sites=query.getResultList();           
                      
           return sites;
       
    }

    @Override
    public boolean deleteSite(Site info) {
       boolean isOkay=false;       
       String sql="DELETE FROM Site as s Where s.id=:id OR s.name=:name";     
       
       Query query = this.siteManager.createNamedQuery(sql);
       query.setParameter("id", info.getId().toString());
       query.setParameter("name",info.getName().toLowerCase());
       
       if(query.executeUpdate()>0)
           isOkay=true;
       return isOkay;
    }

    @Override
    public boolean updateSite(int id, Site info) {
         boolean isOkay=false;
         
         
       
       return isOkay;
    }
    
   @Override
  
    public List<Site> searchSites(Site site)throws FinderException {
        List<Site> sites=null;
       
        
        String sql="SELECT s FROM Site s Where s.id like '"+site.getId()+"' OR s.name='"+site.getName().toLowerCase()+"' OR s.flag='"+site.getFlag().toLowerCase()+"' OR s.region='"+site.getRegion().toLowerCase()+"' ";
         Query query = this.siteManager.createNamedQuery(sql);
         sites = query.getResultList();       
        
        return sites;
        
    }

    @Override
    public boolean isExists(Site site) throws FinderException {
      
        boolean isOkay = false;
        String sql="Select s From Site s WHERE s.name ='"+site.getName().toLowerCase()+"'";
         Query q = this.siteManager.createQuery(sql);    
        
         if(q.getResultList().size() > 0)
            isOkay=true;
       
           return isOkay;    
      
        
    }

  
    
}
