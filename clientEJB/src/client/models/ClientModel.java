/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import client.controllers.ClientController;
import helps.IObserver;
import helps.ISubject;

/**
 *
 * @author 21187498
 */
public class ClientModel implements ISubject {

    ClientController controller;

    public ClientModel() {

    }

    @Override
    public void attach(IObserver observer) {
        controller = (ClientController) observer;
    }

}
