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
import entities.SiteInfo;
import java.util.Properties;
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
    private List<SiteInfo> sites;
    private final TableModel tablemodel;
    private Properties prop;
    private InitialContext cxt;
    
    
    public  SiteModel ()
    {
        sites= null;
        tablemodel = new TableModel(this);
    }
    @Override
    public void attach(IObserver observer) {
        controller  = (SiteController)observer;
    }
    
   
  public  AbstractTableModel getTableModel()
    {
        return this.tablemodel;
    }
   


  public  String getErrorMessage() {     
        
        return  messageError;
    }
  
  //The method will load the sites from the server to the client
 public synchronized void loadSites()
 {
        
              if(makeConnection())
                {
                 JOptionPane.showMessageDialog(null, "Hello World");
                 sites = entrySite.getSites();
                  
              }
    
    
 }//end loadsite message

    //The method call the rmi 
   public void createSite(SiteInfo info) {
       boolean isokay =false;
       int status =0;
       //validation here
       if(this.makeConnection())
       {
           try
           {
            isokay = this.entrySite.createSite(info);
           
            if(isokay)
            {
               
               status =1;
               
            }
            else
                this.messageError = this.entrySite.getErrorMessage(); 
           }
           catch(Exception ex)
           {
            this.messageError = ex.getMessage();
            status =0;
           }         
       } 
       this.loadSites();
       this.tablemodel.fireTableDataChanged();
       this.controller.update(status);
    
    }

 public boolean makeConnection() {
     boolean isOkay=false;
     NetworkInfo netInfo= new NetworkInfo();
     netInfo.setHost(helps.EJBServerConstants.Remote.INTERNET_HOST);
     //catch any Number format exceptions
     try
     {
     int port =Integer.parseUnsignedInt(helps.EJBServerConstants.Remote.INTERNET_HOST.trim());
         if(port ==0)
         netInfo.setPort(port);
         
     }catch(NumberFormatException  err)
     {
      this.messageError=err.getMessage();   
     }
     
     this.prop= client.Client.getProperties(netInfo); 
     try
     {
         
         this.cxt= new InitialContext(prop);
         this.entrySite = (IEntrySiteRemote)cxt.lookup(helps.EJBServerConstants.Beans.IEntrySite);
         isOkay=true;
         
     }catch(NamingException err)
     {
         this.messageError=err.getMessage();
     }
     
     return isOkay;
     
    }

  private  List<SiteInfo> getSites() {
        
            if(this.makeConnection())
            {
             return this.entrySite.getSites();
            }
            
           return null;       
       
    }
 
 
 //the inner class for the table model
 
 private class TableModel extends AbstractTableModel
 {
     
     private SiteModel parent= null;
     public  TableModel(SiteModel aparent)
     {
         this.parent = aparent;
         
     }
     
     /**
     
      * 
      */
    final  private static int SITE_ID =0;
    final  private static int SITE_NAME =1;
    final private static int SITE_FLAG =3;
    final private static int SITE_REGION =2;
    
     
     
        @Override
        public int getRowCount() {
          int rows =0;
          if(this.parent.sites != null)
              return this.parent.sites.size();
          return 0;
        
        }

        @Override
        public int getColumnCount() {
           return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) 
        {
            
           
			SiteInfo info = this.parent.sites.get(rowIndex);
			// check if the return car is null
			if (info == null)
				return null;

			// else
			switch (columnIndex) {
			case SITE_ID:
				return info.getId();
			case SITE_NAME:
				return info.getName();
			case  SITE_REGION:
				return info.getRegion();
                        case SITE_FLAG :
				return info.getFlag();
			default:
				return null;
			}

        }
     
        
         @Override
	  public Class<?> getColumnClass(int col) {
			switch (col) {
			case   SITE_ID:
				return String.class;
			case  SITE_NAME:
				return String.class;
			case SITE_REGION:
				return String.class;
			case  SITE_FLAG :
				return String.class;
			default:
				return null;
			}
                }
         
        
         
              @Override
		public String getColumnName(int col) {
			switch (col) {
			case SITE_ID:
				return "Identity No";
			case SITE_NAME:
				return "Name";
			case SITE_REGION:
				return "Location";
			case  SITE_FLAG:
				return "Flag Rank";
			default:
				return null;
			}

		}
                  
 }
}
