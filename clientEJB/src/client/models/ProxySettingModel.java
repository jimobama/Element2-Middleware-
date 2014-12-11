/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import beans.IConnectionRemote;
import client.controllers.ProxySettingController;
import helps.IObserver;
import helps.ISubject;
import helps.NetworkInfo;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author 21187498
 */
public class ProxySettingModel implements ISubject {
 private ProxySettingController controller;
 
 public ProxySettingModel()
 {
     controller=null;
 }
  public boolean testConnection(String host, int port)
    {
        NetworkInfo info = new NetworkInfo();
        info.setHost(host);
        info.setPort(port);
        Properties evr= client.Client.getProperties(info);
        try
        {
            InitialContext cxt= new InitialContext(evr);
            IConnectionRemote icon = (IConnectionRemote)cxt.lookup(helps.EJBServerConstants.Beans.ICONNECTION);
            if(icon.connect("Testing connection 1"))
            {
                return true;
            }
            
            
        }
        catch(NamingException err)
        {
          err.printStackTrace();
        }
       
         return false;
      
    }
  
  
    @Override
    public void attach(IObserver observer) {
        this.controller = (ProxySettingController)controller;
        }
    
}
