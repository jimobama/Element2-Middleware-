/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import client.controllers.ClientController;
import entities.Structure;
import helps.IObserver;
import helps.ISubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Obaro I. Johnson
 */
public class StructureModel implements ISubject {

    private ClientController controller;
    private List<Structure> structures;
    private beans.IEntryStructureRemote entryStructure;
    private InitialContext cxt;
    private Properties prop;

    StructureModel() {
            structures=new ArrayList<>();
    }

    @Override
    public void attach(IObserver observer) {
        controller = (ClientController) observer;
    }

    
    // the method that will call the remote stub and intialised it 
    
    private boolean makeConnection()
    {
        boolean isOkay=false;
        helps.NetworkInfo info =new  helps.NetworkInfo();
        info.setHost(helps.EJBServerConstants.Remote.INTERNET_HOST);
        info.setPort(helps.EJBServerConstants.Remote.INTERNET_PORT);        
        this.prop= client.Client.getProperties(info);
        try
        {
            this.cxt= new InitialContext(prop);
            this.entryStructure = (beans.IEntryStructureRemote)this.cxt.lookup(helps.EJBServerConstants.Beans.IEntryStructure);
            isOkay=true;
        }catch(NamingException err)
        {
            isOkay = false;
            err.printStackTrace();
        }
        
        return isOkay;
        
    }
    
    
    
    
    
}
