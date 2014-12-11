/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.controllers.ClientController;
import client.models.ClientModel;
import client.views.ClientView;
import helps.NetworkInfo;
import java.util.Properties;
import javax.naming.Context;

/**
 *
 * @author 21187498
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ClientController controller = new ClientController(new ClientModel(),new ClientView("Saxon Heritage"));
      controller.launch();
    }
    
    public static Properties getProperties(NetworkInfo info)
    {
         Properties  p=new Properties();
         p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
         p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
         p.put(Context.PROVIDER_URL, info.getHost());        
         return p;              
    }
}
