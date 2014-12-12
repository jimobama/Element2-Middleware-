/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import client.models.SiteModel;
import client.views.SiteView;
import helps.Controller;
import helps.IObserver;
import helps.ISubject;
import entities.Site;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 21187498
 */
public class SiteController extends IObserver implements Controller {

    private final SiteModel model;
    private final SiteView view;
    private static SiteController instance = null;

    //threads
    private SiteController(SiteModel aModel, SiteView aView) {
        this.model = aModel;
        this.view = aView;
        this.createThread = null;
        //Attach the controller to the model and the view 
        this.model.attach(this);
        this.view.attach(this);

        //createThread initialisation
    }

    public static SiteController GetInstance() {

        if (instance == null) {
            instance = new SiteController(new SiteModel(), new SiteView("New site"));
        }

        return instance;
    }

    @Override
    public ISubject getModel() {
        return this.model;
    }

    @Override
    public ISubject getView() {
        return this.view;
    }
    //a thread to create a new site information to the server
    Thread createThread;

    @Override
    public void launch() {
        
        this.view.pack();
        this.view.setResizable(false);        
        this.view.center();
        this.view.setVisible(true);
    }

    boolean makeConnectionRequest() {
        return this.model.makeConnection();
    }

    String getErrorMessage() {
        return this.model.getErrorMessage();
    }

    public void xhsCreateSite() {

        Site site = view.getSiteInfo();
        if (!site.validate()) {
            this.view.errorMessage(site.getErrorMessage());
            return;
        }
        //create a new thread that it will run on ;    
        if (createThread != null) {
            createThread.interrupt();
            createThread = null;
        }

        createThread = new Thread() {
            public void run() {

                Site info = view.getSiteInfo();
                if (info == null) {

                    return;
                }
                view.changeCreateStatus(0);
                model.createSite(info);

            }
        };
        handleThread(createThread);
       

    }

    @Override
    public void update(int status) {
        if (status == 1) {
            this.view.successMessage("Site successfully created.");
        } else {
            this.view.errorMessage("Error: This site name or identity already exits");
        }
        view.changeCreateStatus(1);
    }
 public void update(int status,String msg) {
        if (status == 1) {
            this.view.successMessage(msg);
        } else {
            this.view.errorMessage(msg);
        }
        view.changeCreateStatus(1);
    }
    public void xhsCancelCreateSite() {

        try {
            if (createThread.isAlive()) {
                createThread.interrupt();
                this.view.successMessage("Create site operation aborted");
            }
        } catch (Exception err) {
            this.view.errorMessage("Create Site Aborted: " + err.getMessage());
        }

        this.view.changeCreateStatus(1);
    }

    public void xhsReloadSites() {
       
        this.view.changeProgressStatus(SiteView.RELOAD,1);
        Thread threadLoads= new Thread()
        {
            
         @Override
         public  void run()
            {
                
                model.loadSites();
                view.changeProgressStatus(SiteView.RELOAD,0);
            }
        };
       
        this.handleThread(threadLoads);
        
    }

    public void xhsDeleteSites() {
     
        this.view.changeProgressStatus(SiteView.DELETE, 1);
        Thread tDel= new Thread(){
        
            public void run(){
            List<Site> sites = model.getSelectedSites();       
            if(sites !=null)
            {
                model.deleteSites(sites);
                view.changeProgressStatus(SiteView.DELETE, 0);

            }else
             view.errorMessage("Please select a site to delete");
            }
        };
        this.handleThread(tDel);
    }

    public void xhsUpdateSites(Site siteInfo) {
        this.view.changeProgressStatus(SiteView.UPDATE, 1);
        if(siteInfo ==null)
            return ;
        //update the site information with the following ID;
        Thread tUpdate= new Thread()
        {
            public void run()
            {
               model.updateSite(siteInfo);
               view.changeProgressStatus(SiteView.DELETE, 0);
            }
        };
        
        this.handleThread(tUpdate);
       
    }

    public void xhsFindSites(Site site) {        
  
        if(site !=null)
        {  
            this.view.changeProgressStatus(SiteView.SEARCH, 1);
            Thread t= new Thread()
            {
                public void run(){
                model.findWidth(site);
                view.changeProgressStatus(SiteView.SEARCH, 0);
                }
            };
            this.handleThread(t);
        }     
    }

    private void handleThread(Thread t) {
        
       try
        {
         t.join();
         t.start();      
        }
        catch(Exception err)
        {
            this.view.errorMessage(err.getMessage());
        }
    }

}
