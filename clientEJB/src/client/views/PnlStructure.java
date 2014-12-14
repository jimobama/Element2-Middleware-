/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.views;

import client.controllers.ClientController;
import client.models.ClientModel;
import client.models.StructureModel;
import entities.Site;
import entities.Structure;
import helps.View;
import javax.swing.JOptionPane;

/**
 *
 * @author Obaro I. Johnson
 */
public class PnlStructure extends javax.swing.JPanel {

    private ClientView parentView;
    private ClientController controller;
    public static final String CREATE_STRUCTURE="Create Structure";
     public static final String IN_PROGRESS_CREATE_STRUCTURE="In progress...";
    /**
     * Creates new form PnlStructure
     */
    public PnlStructure() {
        initComponents();
        this.txtSiteID.setVisible(false);
        disabled(false);
        this.txtSiteName.setEditable(false);
        this.txtSiteRegion.setEditable(false);
        this.txtFlag.setEditable(false);
        this.btnCreateStructure.setText(CREATE_STRUCTURE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlFrmSite = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFlag = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSiteName = new javax.swing.JTextField();
        txtSiteRegion = new javax.swing.JTextField();
        btnInsertSite = new javax.swing.JButton();
        txtSiteID = new javax.swing.JTextField();
        pnlStruture = new javax.swing.JPanel();
        txtStructureID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStructureTitle = new javax.swing.JTextField();
        cboStructureID = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaDescription_s = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCreateStructure = new javax.swing.JButton();
        btnDone = new javax.swing.JButton();
        txtStructureLocation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStrutureInformation = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        jPanel1.setName("pnlLeftSide"); // NOI18N

        pnlFrmSite.setBorder(javax.swing.BorderFactory.createTitledBorder("Site Information..."));

        jLabel1.setText("Region");

        txtFlag.setName("txtFlags"); // NOI18N

        jLabel2.setText("Site Name:");

        jLabel3.setText("Flags");

        txtSiteName.setName("txtName"); // NOI18N

        txtSiteRegion.setName("txtRegion"); // NOI18N

        btnInsertSite.setLabel("Insert site...");
        btnInsertSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertSiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFrmSiteLayout = new javax.swing.GroupLayout(pnlFrmSite);
        pnlFrmSite.setLayout(pnlFrmSiteLayout);
        pnlFrmSiteLayout.setHorizontalGroup(
            pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFrmSiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFrmSiteLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSiteName))
                    .addGroup(pnlFrmSiteLayout.createSequentialGroup()
                        .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSiteRegion)
                            .addComponent(txtFlag)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFrmSiteLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtSiteID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInsertSite, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFrmSiteLayout.setVerticalGroup(
            pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFrmSiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSiteName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSiteRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlFrmSiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertSite)
                    .addComponent(txtSiteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlStruture.setBorder(javax.swing.BorderFactory.createTitledBorder("Structure Information"));

        txtStructureID.setName("txtName"); // NOI18N

        jLabel4.setText("Structure ID:");

        txtStructureTitle.setName("txtName"); // NOI18N

        cboStructureID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Building", "Garden", "Monuments" }));

        txtaDescription_s.setColumns(20);
        txtaDescription_s.setRows(5);
        jScrollPane2.setViewportView(txtaDescription_s);

        jLabel5.setText("Description");

        jLabel6.setText("Name:");

        jLabel7.setText("Type");

        btnCreateStructure.setText("Create New Structure");
        btnCreateStructure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateStructureActionPerformed(evt);
            }
        });

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        txtStructureLocation.setName("txtName"); // NOI18N

        jLabel8.setText("Location Address:");

        javax.swing.GroupLayout pnlStrutureLayout = new javax.swing.GroupLayout(pnlStruture);
        pnlStruture.setLayout(pnlStrutureLayout);
        pnlStrutureLayout.setHorizontalGroup(
            pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStrutureLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlStrutureLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStructureID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStrutureLayout.createSequentialGroup()
                        .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlStrutureLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStructureTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboStructureID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStrutureLayout.createSequentialGroup()
                        .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateStructure, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlStrutureLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStructureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlStrutureLayout.setVerticalGroup(
            pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStrutureLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStructureID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStructureTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboStructureID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStructureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlStrutureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateStructure, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStruture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFrmSite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlFrmSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlStruture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(tblStrutureInformation);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        disabled(false);
        Site site = new Site();
        this.txtSiteID.setText(site.getId().toString());
        this.txtSiteName.setText(site.getName());
        this.txtSiteRegion.setText(site.getRegion());
        this.txtFlag.setText(site.getRegion());

    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnInsertSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertSiteActionPerformed
        controller = (ClientController) this.parentView.xhsCallController();
        controller.xhsInsertionSettings(false);

    }//GEN-LAST:event_btnInsertSiteActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.controller.xhsDeleteStructure();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCreateStructureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateStructureActionPerformed

        int siteID = this.getSiteId();
        Structure structure = this.getStructureInfo();
        if(structure !=null){
        structure.setSiteId((long)siteID);
        this.controller.xhsCreateNewStructure(structure);}
        else
            JOptionPane.showMessageDialog(null,"Enter valid information of the structure and make such id is a number greater than  1");
    }//GEN-LAST:event_btnCreateStructureActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
       this.controller.xhsRefreshStructureTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateStructure;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnInsertSite;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cboStructureID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlFrmSite;
    private javax.swing.JPanel pnlStruture;
    private javax.swing.JTable tblStrutureInformation;
    private javax.swing.JTextField txtFlag;
    private javax.swing.JTextField txtSiteID;
    private javax.swing.JTextField txtSiteName;
    private javax.swing.JTextField txtSiteRegion;
    private javax.swing.JTextField txtStructureID;
    private javax.swing.JTextField txtStructureLocation;
    private javax.swing.JTextField txtStructureTitle;
    private javax.swing.JTextArea txtaDescription_s;
    // End of variables declaration//GEN-END:variables

    public void attach(View observer) {
        parentView = (ClientView) observer;
        controller = (ClientController) this.parentView.xhsCallController();
        ClientModel model = (ClientModel) controller.getModel();
        tblStrutureInformation.setModel(model.getStructureTableModel());
         this.controller.xhsRefreshStructureTable();
        tblStrutureInformation.repaint();
        

    }

    private void disabled(boolean abool) {
        this.txtStructureTitle.setEnabled(abool);
        this.txtaDescription_s.setEnabled(abool);
        this.cboStructureID.setEnabled(abool);
        this.txtStructureID.setEnabled(abool);
        this.pnlStruture.setEnabled(abool);
        this.btnCreateStructure.setEnabled(abool);
        this.btnDone.setEnabled(abool);

    }

    void setSiteInformation(Site site) {
        this.txtSiteID.setText(site.getId().toString());
        this.txtSiteName.setText(site.getName());
        this.txtSiteRegion.setText(site.getRegion());
        this.txtFlag.setText(site.getRegion());
        disabled(true);

    }

    Structure getStructureDetails() {
        Structure struct = new Structure();
        long id = 0;
        long idSite = 0;

        try {
            id = Long.parseLong(this.txtStructureID.getText().trim());
            idSite = Long.parseLong(this.txtSiteID.getText().trim());
        } catch (Exception err) {
            err.printStackTrace();
        }
        struct.setId(id);
        // struct.setLocation(this.txtLocation.getText());
        struct.setSiteId(idSite);
        struct.setStatus(false);
        struct.setDescription(this.txtaDescription_s.getText());
        struct.setType(this.cboStructureID.getSelectedItem().toString());
        return struct;

    }

    private int getSiteId() {
        int id= 0;
        try {
            id = Integer.parseInt(this.txtSiteID.getText().trim());
        } catch (Exception err) {
            javax.swing.JOptionPane.showMessageDialog(null, "Site information must be added before you can create a structure please!");
        }

        return id;
    }

    private Structure getStructureInfo() {
        Structure s= new Structure();
        
        int Id =  this.getStructureID();
        if(Id <= 0){return null;}
        
       s.setId((long)Id);
       s.setDescription(this.txtaDescription_s.getText());
       s.setLocation(this.txtStructureLocation.getText());
       s.setName(this.txtStructureTitle.getText());
       s.setStatus(false);
       s.setType(this.cboStructureID.getSelectedItem().toString());
       return s;
    }

    public void setCreateButton(String s)
    {
        this.btnCreateStructure.setText(s);
    }
    private int getStructureID() {
         int id =-1;
        try{
            id = (int) Long.parseLong(this.txtStructureID.getText().trim());
        }catch(Exception err)
        {
           
        }
        
        return id;
       
    }

}
