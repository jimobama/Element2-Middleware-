/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import beans.IEntryStructureRemote;
import entities.Site;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PostRemove;


/**
 *
 * @author Obaro I. Johnson
 */
@Stateless
public class SiteListener {

  //create an object to the structure bean interface
    @EJB
    IEntryStructureRemote remoteSructure=null; 
     Context ctx;
    
    // Now will are going to initialised it as the entity is created using at @C
   @PostConstruct
    void postConstrution()
    {
         
        try {
            ctx = new InitialContext();
            remoteSructure = (IEntryStructureRemote) ctx.lookup(helps.EJBServerConstants.Beans.IEntryStructure);                   
              } catch (NamingException ex) {
            Logger.getLogger(SiteListener.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    @PostRemove
    void cleanUp(Object o)
    {
        if(remoteSructure !=null)
        {
            //type cast the object to the site object
            Site site=(Site)o;            
           this.remoteSructure.deleteBySiteID(site.getId());            
            javax.swing.JOptionPane.showMessageDialog(null,"Is running");
            //delete the object if the site is about to be delete from the server
            
        }
      
    }
}
