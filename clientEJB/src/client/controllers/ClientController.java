/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import client.models.ClientModel;
import client.views.ClientView;
import client.views.PnlStructure;
import client.views.SiteView;
import entities.Site;
import entities.Structure;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import helps.IObserver;
import helps.Controller;
import helps.ISubject;
import java.util.List;

/**
 *
 * @author 21187498
 */
public class ClientController extends IObserver implements Controller {

    private ClientModel model;
    private ClientView view;
    private ProxySettingController proxyController;
    private SiteController siteController;
  

    static public boolean IsConnected = false;

    public ClientController(ClientModel aModel, ClientView aView) {
        this.model = aModel;
        this.view = aView;
        //the attachment of the controller
        this.model.attach(this);
        this.view.attach(this);
        
        SiteController.GetInstance().attach(this);

    }

    @Override
    public void launch() {

        //this.view.pack();
        this.view.setSize(new Dimension(800, 600));
        this.view.center();
        this.view.pack();
        this.view.setResizable(false);
      
        this.view.setVisible(true);

    }
    
    private void createNewStructure()
    {
        Structure struc= this.view.getStructure();
        if(!struc.validated())
        {
            this.displayMessageType(0,struc.getErrorMessage());
            return ;
        }
        //every thing is okay
        Thread t= new Thread()
                {
                    @Override
                   public void run()
                   {
                    boolean status = model.createStructure(struc);
                    if(status)
                        {
                          displayMessageType(1,"Structure as be successfully add to server database!");
                        }else
                        displayMessageType(0,model.getErrorMessage());
                   }
                };
        
        
        //now run the thread
        this.handleThread(t);
        
        
    }

    @Override
    public ISubject getModel() {
        return this.model;
    }

    @Override
    public ISubject getView() {
        return this.view;
    }

    final public void xhsTestConnection() {

        proxyController = ProxySettingController.getInstance();
        boolean status = proxyController.xhsTestConnection();
        if (status) {
            JOptionPane.showMessageDialog(view, proxyController.getErrorMessage());
            this.view.repaint();
            return;
        }

        JOptionPane.showMessageDialog(view, proxyController.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);

    }

    final public void xhsExitApplication() {
        System.exit(0);
    }

    final public void xhsOpenNewForm() {

        siteController = SiteController.GetInstance();
        SiteView.IsSelectMode = false;
        this.siteController.enable();
        siteController.launch();
    }

    final public void xhsProxySettingOpenView() {
        ProxySettingController proxySettingController = ProxySettingController.getInstance();
        proxySettingController.launch();
    }

    void xhsSelectStructureSite(Site site) {

        this.view.setSiteInformation(site);
    }

    public void xhsInsertionSettings(boolean b) {
        siteController = SiteController.GetInstance();
        this.siteController.callFromInsertSite(b);
        siteController.launch();
    }

    private void displayMessageType(int i, String errorMessage) {
       if(i==1)
       {
           JOptionPane.showMessageDialog(this.view, errorMessage,"Information",JOptionPane.INFORMATION_MESSAGE);
       }else
       {
            JOptionPane.showMessageDialog(this.view, errorMessage,"Error",JOptionPane.ERROR_MESSAGE);
       }
    }

    private void handleThread(Thread t) {
        
        try
        {
            t.join();
            t.start();
        }catch(Exception err)
        {
            this.displayMessageType(0,err.getMessage());
        }
    }

    public void xhsCreateNewStructure(Structure structure) {
        
        if(!structure.validated()){
               this.displayMessageType(0, structure.getErrorMessage());
               return ;
        }
        this.view.setCreateText(PnlStructure.IN_PROGRESS_CREATE_STRUCTURE);
        Thread t= new Thread(){
            
            @Override
            public void run(){
                     boolean isOkay=   model.createStructure(structure);
                     view.setCreateText(PnlStructure.CREATE_STRUCTURE);
                     if(isOkay)
                     {
                         displayMessageType(1,"Site successfully created!");
                     }else
                     {
                         displayMessageType(0,"Site fail to created");
                     }
                  }
       
        };
        
        //run the thread
        this.handleThread(t);
        
        
    }

    public void xhsRefreshStructureTable() {
        
       Thread t= new Thread(){
           public void run(){
                model.reloadStructure();
                
           }
       };
       
       // execute the thread
       this.handleThread(t);
    }


    public void xhsDeleteStructure() {
        
         List<Structure> structures=(List<Structure>)  this.model.getSelectedStructures();
         if(structures ==null)
         {
             this.displayMessageType(0, "Please select structure to delete!");
             return ;
         }
         
         //create and rull the delete on a thread
         Thread t= new Thread()
         {
             public void run()
             {
               model.deleteStructures(structures); 
             }
         };
         //Handle the thread
         this.handleThread(t);
        
    }
    
 

}
