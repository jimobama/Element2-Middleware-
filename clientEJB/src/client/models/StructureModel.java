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
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.AbstractTableModel;

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
    private String error;
    private TableModel tablemodel;

    StructureModel() {
            structures=new ArrayList<>();
            tablemodel= new TableModel(this);
           
    }

    @Override
    public void attach(IObserver observer) {
        controller = (ClientController) observer;
    }

    public AbstractTableModel getAbstractTableModel()
    {
        
        return this.tablemodel;
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

    boolean createStructure(Structure s) {
     
        boolean isokay = false;
        int status = 0;
        //validation here
        if (this.makeConnection()) {
            try {
                isokay = this.entryStructure.createStructure(s);
                if (isokay == true) {
                    status = 1;
                }
            } catch (FinderException err) {
                this.error = err.getMessage();
            }

        }

        this.loadStructure();
        return isokay;

    }

    void loadStructure() {
         this.structures = this.getStructures();
         this.tablemodel.fireTableDataChanged();
    }

    private List<Structure> getStructures() {
        List<Structure> structs = null;
        if (this.makeConnection()) {
            //wrapper the find method with try block
            try {
                structs = this.entryStructure.getStructures();

            } catch (FinderException err) {
                this.error= "Site database is empty!";
            }
        }

        return structs ;
    }

    List<Structure> getSelectedStructures() {
      List<Structure> structs=new ArrayList<>();
      
      Iterator<Structure> iter= this.structures.iterator();     
      
      while(iter.hasNext())
      {
          Structure s=  iter.next();
          if(s.isStatus())
              structs.add(s);
      }
        return structs;
    }

    void deleteStructures(List<Structure> structures) {
        
        if(this.makeConnection())
        {
            Iterator<Structure> iter= structures.iterator();
            while(iter.hasNext())
            {
                try{
                this.entryStructure.delete(iter.next());
                this.loadStructure();
                }catch(FinderException err)
                {
                    err.printStackTrace();
                }
            }
            
        }
     
    }
    
    
    
    
    
    private class TableModel extends AbstractTableModel
    {
        private static final int  COLUMN_COUNT=6;
        private static final int ID=0;
         private static final int TYPE=1;
          private static final int DESCRIPTION=2;
           private static final int LOCATION=3;
            private static final int SITE_ID=4;
             private static final int STATUS=5;
        
        private final StructureModel parent;
   TableModel(StructureModel  mParent)
   {
      parent= mParent;
   }
        @Override
        public int getRowCount() {
           return this.parent.structures.size();
        }

        @Override
        public int getColumnCount() {
           return  COLUMN_COUNT;
        }
        
        @Override
        public Class<?> getColumnClass(int col) {
            switch (col) {
                case TableModel.ID:
                       return long.class;
                   case TableModel.TYPE:
                       return String.class;
                   case TableModel.DESCRIPTION:
                       return String.class;
                   case TableModel.LOCATION:
                       return String.class;
                   case TableModel.SITE_ID:
                       return String.class;
                   case TableModel.STATUS:
                       return Boolean.class;
                   default:
                       return null;
            }
        }
        
          @Override
        public boolean isCellEditable(int row, int col) {
            switch (col) {
                case TableModel.STATUS:
                    return true;
                default:
                    return false;
            }
        }
        @Override
         public String getColumnName(int col)
         {
              switch(col)
               {
                   case TableModel.ID:
                       return "ID";
                   case TableModel.TYPE:
                       return "Type";
                   case TableModel.DESCRIPTION:
                       return "Description";
                   case TableModel.LOCATION:
                       return "Address";
                   case TableModel.SITE_ID:
                       return "Site Id";
                   case TableModel.STATUS:
                       return "Options";
                   default:
                       return null;
               }
         }
         
             @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            Structure s = this.parent.structures.get(rowIndex);
            if (s != null) {
                switch (columnIndex) {
                    case TableModel.STATUS: {
                        s.setStatus((boolean) value);

                    }
                    default:
                        break;
                }

                this.parent.structures.set(rowIndex, s);
                this.fireTableCellUpdated(rowIndex, columnIndex);
            }

        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
           Structure s =(Structure) this.parent.structures.get(rowIndex);
           if(s ==null)
            return null;
           
               switch(columnIndex)
               {
                   case TableModel.ID:
                       return s.getId();
                   case TableModel.TYPE:
                       return s.getType();
                   case TableModel.DESCRIPTION:
                       return s.getDescription();
                   case TableModel.LOCATION:
                       return s.getLocation();
                   case TableModel.SITE_ID:
                       return s.getSiteId();
                   case TableModel.STATUS:
                       return s.isStatus();
                   default:
                       return null;
               }
           
        }
        
    }
    
}
