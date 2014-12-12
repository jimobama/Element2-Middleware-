/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import client.controllers.SiteController;
import client.models.SiteModel;
import entities.Site;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import helps.IObserver;
import helps.ISubject;
import helps.EJBServerConstants;

import helps.View;
import java.awt.Dimension;
import java.util.List;

/**
 *
 * @author 21187498
 */
public class SiteView extends View implements ISubject {

    private static final String CMD_CREATE_SITE = "Create";
    private static final String CMD_CREATE_SITE_RUNNING = "Cancel";
    private static final String CMD_REFERSH_SITE = "Refresh";
     private static final String CMD_UPDATE_SITE = "Update";
      private static final String CMD_DELETE_SITE = "Delete";
       private static final String CMD_SEARCH_SITE = "Search";
    private static final String CMD_IN_PROGRESS_RUNNING = "In Progress...";
    
   public static final int RELOAD= 1;
   public static final int DELETE =2;
   public static final int SEARCH=3;
   public static final int UPDATE =4;
    //defined the form object for the sites
    private JPanel pnlTable;
    private JTable tblSites;
    private JScrollPane jsclSite;
    private JPanel pnlForm;
    private JTextField txtName;
    private JTextField txtSiteId;
    private JComboBox<String> cboRegion;
    private JTextField txtFlag;
    //labels
    private JLabel lblName;
    private JLabel lblSiteId;
    private JLabel lblRegion;
    private JLabel lblFlags;
    //buttons
    private JButton btnCreateSite;
    private JButton btnSearch;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnreferesh;    
    SiteController controller;

    // The constuctor
    public SiteView(String title) {
        super();
        this.setTitle(title);
        this.setModal(true);
        initGui();
        this.pack();
        

    }

    private void initGui() {
        //initialised the form objects
        txtName = new JTextField(30);
        txtSiteId = new JTextField(30);
        cboRegion = new JComboBox<>();
        txtFlag = new JTextField(30);
        //labels
        lblName = new JLabel("Name:");
        lblSiteId = new JLabel("Identity Number:");
        lblRegion = new JLabel("Region:");
        lblFlags = new JLabel("Rank:");
        //buttons

        btnCreateSite = new JButton(CMD_CREATE_SITE);
        this.btnDelete= new JButton(CMD_DELETE_SITE);
        this.btnSearch = new JButton(CMD_SEARCH_SITE);
        this.btnUpdate= new JButton(CMD_UPDATE_SITE);
        this.btnreferesh= new JButton(SiteView.CMD_REFERSH_SITE);
        

        //add the controls to the panel
        this.pnlForm = new JPanel(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //add to the first row
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(4, 4, 4, 4);
        this.pnlForm.add(this.lblSiteId, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 2;
        this.pnlForm.add(this.txtSiteId, gc);

        //add to the first row
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 1;
        this.pnlForm.add(this.lblName, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 2;
        this.pnlForm.add(this.txtName, gc);

        //add to the first row
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 2;
        this.pnlForm.add(this.lblRegion, gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 2;
        this.cboRegion.addItem("London");
        this.cboRegion.addItem("South West");
        this.cboRegion.addItem("Midlands");
        this.cboRegion.addItem("South East");
        this.cboRegion.addItem("North East");
        this.cboRegion.addItem("North West");
        this.pnlForm.add(this.cboRegion, gc);

        //add to the first row
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 3;
        this.pnlForm.add(this.lblFlags, gc);

        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 2;
        this.txtFlag.setEditable(false);

        this.txtFlag.setText(helps.EJBServerConstants.SiteFlags.BRONZE);
        this.pnlForm.add(this.txtFlag, gc);

        //the buttons pnl
        this.pnlTable = new JPanel();
        this.tblSites = new JTable();
        this.jsclSite = new JScrollPane(this.tblSites);
        this.jsclSite.setPreferredSize(new Dimension(500,300));
        this.pnlTable.add(this.jsclSite);

        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 3;
        this.pnlForm.add(this.pnlTable, gc);

        //add the site table to
        JPanel pnlButtons = new JPanel();
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 4;
        gc.gridwidth = 2;
        pnlButtons.add( this.btnCreateSite);
        pnlButtons.add(this.btnUpdate);
         pnlButtons.add(this.btnDelete);
         pnlButtons.add(this.btnSearch);
         pnlButtons.add(this.btnreferesh);
           
        EventHandler btnEvt =new EventHandler(this);
        this.btnCreateSite.addActionListener(btnEvt);
        this.btnDelete.addActionListener(btnEvt);
        this.btnSearch.addActionListener(btnEvt);
        this.btnUpdate.addActionListener(btnEvt);
        this.btnreferesh.addActionListener(btnEvt);
        this.pnlForm.add( pnlButtons, gc);

        //add the controls to the window       
        GridBagConstraints mainGC = new GridBagConstraints();
        mainGC.anchor = GridBagConstraints.NORTHWEST;
        mainGC.fill = GridBagConstraints.HORIZONTAL;
        mainGC.gridx = 0;
        mainGC.gridy = 0;
         
        this.add(this.pnlForm, mainGC);

    }

    @Override
    public void attach(IObserver observer) {
        controller = (SiteController) observer;
        SiteModel aModel = (SiteModel) controller.getModel();
        this.tblSites.setModel(aModel.getTableModel());
        this.controller.xhsReloadSites();

    }

    public Site getSiteInfo() {
        Site info = new Site();
        info.setName(this.txtName.getText());
        info.setRegion(this.cboRegion.getSelectedItem().toString());
        info.setFlag(this.txtFlag.getText());
        try{
        long id= Long.parseLong(this.txtSiteId.getText());
        info.setId(id);
        }catch(Exception err){
        JOptionPane.showMessageDialog(null,"Invalid id empty , only interge value are allow");
        }

        return info;
    }
    
     public Site getSiteInfo(boolean ignore) {
        Site info = new Site();
      
        // an inner function class
        class CheckInput
        {
            private boolean ignore;
            CheckInput(boolean abool)
            {
                this.ignore=abool;
            }
            Site fillSiteInfo()
            {
                 Site aInfo=new Site();
                 aInfo.setName(txtName.getText());
                 aInfo.setRegion(cboRegion.getSelectedItem().toString());
                 aInfo.setFlag(txtFlag.getText());
        
        
                if(this.ignore)
                {
                    //check if there is a value in the site field
                    String val=txtSiteId.getText().trim();
                    //check if there is a value in the id fields
                   if(!val.isEmpty())
                    {
                        
                        try{
                            long id= Long.parseLong(txtSiteId.getText().trim());
                            aInfo.setId(id);
                            }catch(Exception err){
                            JOptionPane.showMessageDialog(null,"Enter a valid site identity number please!");
                            }
                   }else
                   aInfo.setId((long)0);
                   
                    
                    
                }
                
                return aInfo;
            }
            
            boolean isIgnore()
            {
                return ignore;
            }
        }//end classes
        
      
        CheckInput input=new CheckInput(ignore);
        info = input.fillSiteInfo();
        return info;
    }

    public void setChangeCreateButtonStatus(boolean b) {
        if (b) {
            this.btnCreateSite.setText(CMD_CREATE_SITE_RUNNING);
        } else {
            this.btnCreateSite.setText(CMD_CREATE_SITE);
        }
    }
    // the  event handler

    public void errorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void successMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void changeCreateStatus(int i) {
        if (i == 1) {
            this.btnCreateSite.setText(CMD_CREATE_SITE);
        } else {
            this.btnCreateSite.setText(CMD_CREATE_SITE_RUNNING);

        }
    }

    public List<Site> getSelectedSites() {
       return null;
    }

    public void changeProgressStatus(int what, int i) {
      
        class StatusChanger
        {
            String value;
            StatusChanger(String changeValue)
            {
                value=changeValue;
            }
            void toNormal()
            {
               btnDelete.setText(CMD_DELETE_SITE);
               btnSearch.setText(CMD_SEARCH_SITE);
               btnUpdate.setText(CMD_UPDATE_SITE);
               btnreferesh.setText(SiteView.CMD_REFERSH_SITE);
            }
            void change(int awhat)
            {
               switch(awhat)
                    {
                        case SiteView.RELOAD:
                            btnreferesh.setText(value);
                            break;
                         case SiteView.DELETE:
                            btnDelete.setText(value);
                            break;
                         case SiteView.UPDATE:
                            btnUpdate.setText(value);
                            break;
                         case SiteView.SEARCH:
                             btnSearch.setText(value);
                             break;
                         default:
                             break;                     
                   
                    } //end switch
            }
            
        }//end fucntion class
        
       //check if to change back from progress or not
        StatusChanger changer = new StatusChanger(SiteView.CMD_IN_PROGRESS_RUNNING);
        if(i==1)
        {
           changer.change(what);
        }else
        {
            changer.toNormal();
        }
        
    }

    private class EventHandler implements ActionListener {

        private SiteView view;

        EventHandler(SiteView aView) {
            this.view = aView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == null ? CMD_CREATE_SITE == null : e.getActionCommand().equals(CMD_CREATE_SITE)) {
                this.view.controller.xhsCreateSite();
            } else if (e.getActionCommand().equalsIgnoreCase(CMD_CREATE_SITE_RUNNING)) {
                this.view.controller.xhsCancelCreateSite();
            } else if(e.getActionCommand().equalsIgnoreCase(SiteView.CMD_REFERSH_SITE))
            {
                this.view.controller.xhsReloadSites();
            }else if(e.getActionCommand().equalsIgnoreCase(SiteView.CMD_DELETE_SITE))
            {
                this.view.controller.xhsDeleteSites();
            }else if(e.getActionCommand().equalsIgnoreCase(SiteView.CMD_UPDATE_SITE))
            {
                this.view.controller.xhsUpdateSites(this.view.getSiteInfo());
            }else if(e.getActionCommand().equalsIgnoreCase(SiteView.CMD_SEARCH_SITE))
            {
                this.view.controller.xhsFindSites(this.view.getSiteInfo(true));
            }
             

        }

    }

}
