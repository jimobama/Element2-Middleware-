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
        this.view.center();
        this.view.setResizable(false);
        this.view.pack();
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
        try {
            createThread.join();
            createThread.start();
        } catch (Exception err) {
            this.view.errorMessage(err.getMessage());
        }

    }

    @Override
    public void update(int status) {
        if (status == 1) {
            this.view.successMessage("Site successfully created.");
        } else {
            this.view.errorMessage("Fatel error: "+this.model.getErrorMessage());
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

}
