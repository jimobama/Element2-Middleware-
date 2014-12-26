/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.models;

import client.controllers.SiteController;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import beans.IEntrySiteRemote;
import helps.IObserver;
import helps.ISubject;
import helps.NetworkInfo;
import entities.Site;
import entities.Structure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author 21187498
 */
public class SiteModel implements ISubject {

    private SiteController controller;
    private IEntrySiteRemote entrySite;
    private String messageError;
    private List<Site> sites;
    private final TableModel tablemodel;
    private Properties prop;
    private InitialContext cxt;

    public SiteModel() {
        sites = null;
        tablemodel = new TableModel(this);
    }

    @Override
    public void attach(IObserver observer) {
        controller = (SiteController) observer;
    }

    public AbstractTableModel getTableModel() {
        return this.tablemodel;
    }

    public String getErrorMessage() {

        return messageError;
    }

    //The method will load the sites from the server to the client
    public synchronized void loadSites() {

        sites = this.getSites();
        this.tablemodel.fireTableDataChanged();

    }//end loadsite message

    //The method call the rmi 
    public void createSite(Site info) {

        boolean isokay = false;
        int status = 0;
        //validation here
        if (this.makeConnection()) {
            try {
                isokay = this.entrySite.createSite(info);
                if (isokay == true) {
                    status = 1;
                }
            } catch (FinderException err) {
                this.messageError = err.getMessage();
            }

        }

        this.loadSites();

        this.controller.update(status);

    }

    public boolean makeConnection() {
        boolean isOkay = false;
        NetworkInfo netInfo = new NetworkInfo();
        netInfo.setHost(helps.EJBServerConstants.Remote.INTERNET_HOST);
        netInfo.setPort(helps.EJBServerConstants.Remote.INTERNET_PORT);
        //catch any Number format exceptions
        this.prop = client.Client.getProperties(netInfo);
        try {

            this.cxt = new InitialContext(prop);
            this.entrySite = (IEntrySiteRemote) cxt.lookup(helps.EJBServerConstants.Beans.IEntrySite);
            isOkay = true;

        } catch (NamingException err) {
            this.messageError = err.getMessage();
        }

        return isOkay;

    }

    private List<Site> getSites() {
        List<Site> sites = null;
        if (this.makeConnection()) {
            //wrapper the find method with try block
            try {
                sites = this.entrySite.getSites();

            } catch (FinderException err) {
                this.messageError = "Site database is empty!";
            }
        }

        return sites;

    }

    public void deleteSites(List<Site> sites) {
        this.makeConnection();
        if (sites.size() > 0) {
            Iterator<Site> iter = sites.iterator();

            while (iter.hasNext()) {
                Site site = iter.next();
                this.entrySite.deleteSite(site);
                this.loadSites();
            }

            this.controller.update(1, "Sites successfully deleted");
        }
    }

    public void findWidth(Site site) {
        boolean status = this.makeConnection();
        if (status) {

            try {
                sites = this.entrySite.searchSites(site);

            } catch (FinderException err) {
                this.messageError = err.getMessage();
            }
        }
        this.tablemodel.fireTableDataChanged();

    }

    public List<Site> getSelectedSites() {
        Iterator<Site> iter = this.sites.iterator();
        List<Site> filterSites = new ArrayList<>();
        while (iter.hasNext()) {
            Site site = iter.next();
            if (site.getStatus()) {
                filterSites.add(site);
            }
        }

        return filterSites;
    }

    public void updateSite(Site s) {
        if (s != null) {
            if (this.makeConnection()) {
                if (this.entrySite.updateSite(s.getId().intValue(), s)) {
                    this.loadSites();
                    this.controller.update(1, "Site as be successfully updated");
                } else {
                    this.controller.update(0, "Site update fails please check your fields to make such the right information is passed");
                }
            }
        }

    }

    public List<Structure> getStructures(Long id) {

        List<Structure> structures = null;
        if (this.makeConnection()) {
            Integer ID = (int) (long) id;

            try {
                structures = this.entrySite.getStructures(ID);

            } catch (FinderException ex) {
                Logger.getLogger(SiteModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(SiteModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return structures;
    }

    //the inner class for the table model
    private class TableModel extends AbstractTableModel {

        private SiteModel parent = null;

        public TableModel(SiteModel aparent) {
            this.parent = aparent;

        }

        /**
         *
         *
         */
        final private static int SITE_ID = 0;
        final private static int SITE_NAME = 1;
        final private static int SITE_REGION = 2;
        final private static int SITE_FLAG = 3;
        final private static int SITE_STATUS = 4;
        final private static int COLUMN_COUNTS = 5;

        @Override
        public int getRowCount() {
            int rows = 0;
            if (this.parent.sites != null) {
                return this.parent.sites.size();
            }
            return 0;

        }

        @Override
        public int getColumnCount() {
            return COLUMN_COUNTS;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            Site site = this.parent.sites.get(rowIndex);
            if (site != null) {
                switch (columnIndex) {
                    case TableModel.SITE_STATUS: {
                        site.setStatus((boolean) value);

                    }
                    default:
                        break;
                }

                this.parent.sites.set(rowIndex, site);
                this.fireTableCellUpdated(rowIndex, columnIndex);
            }

        }

        @Override
        public boolean isCellEditable(int row, int col) {
            switch (col) {
                case TableModel.SITE_STATUS:
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            Site info = this.parent.sites.get(rowIndex);
            // check if the return car is null
            if (info == null) {
                return null;
            }

            // else
            switch (columnIndex) {
                case SITE_STATUS:
                    return info.getStatus();
                case SITE_ID:
                    return info.getId();
                case SITE_NAME:
                    return info.getName();
                case SITE_REGION:
                    return info.getRegion();
                case SITE_FLAG:
                    return info.getFlag();
                default:
                    return null;
            }

        }

        @Override
        public Class<?> getColumnClass(int col) {
            switch (col) {
                case SITE_STATUS:
                    return Boolean.class;
                case SITE_ID:
                    return String.class;
                case SITE_NAME:
                    return String.class;
                case SITE_REGION:
                    return String.class;
                case SITE_FLAG:
                    return String.class;

                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int col) {
            switch (col) {
                case SITE_STATUS:
                    return "Option(s)";
                case SITE_ID:
                    return "Identity No";
                case SITE_NAME:
                    return "Name";
                case SITE_REGION:
                    return "Location";
                case SITE_FLAG:
                    return "Flag Rank";

                default:
                    return null;
            }

        }

    }
}
