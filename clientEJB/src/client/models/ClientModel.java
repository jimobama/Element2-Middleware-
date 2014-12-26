/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import client.controllers.ClientController;
import entities.Structure;
import helps.IObserver;
import helps.ISubject;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 21187498
 */
public class ClientModel implements ISubject {

    ClientController controller;
    StructureModel modelStructure;
    private String error;

    public ClientModel() {
        modelStructure = new StructureModel();
    }

    @Override
    public void attach(IObserver observer) {
        controller = (ClientController) observer;
        modelStructure.attach(controller);
    }

    public boolean createStructure(Structure struc) {

        return this.modelStructure.createStructure(struc);

    }

    public String getErrorMessage() {
        return this.error;
    }

    public AbstractTableModel getStructureTableModel() {

        return this.modelStructure.getAbstractTableModel();
    }

    public void reloadStructure() {
        this.modelStructure.loadStructure();
    }

    public List<Structure> getSelectedStructures() {
        return this.modelStructure.getSelectedStructures();
    }

    public void deleteStructures(List<Structure> structures) {
        this.modelStructure.deleteStructures(structures);
    }

    public void reloadStructure(Long id) {
        this.modelStructure.loadStructure(id);
    }

    public void setStructures(List<Structure> s) {
        this.modelStructure.setStructures(s);
    }

}
