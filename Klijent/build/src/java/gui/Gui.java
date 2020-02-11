package gui;

import entiteti.Documentrequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ObjectMessage;
import javax.json.Json;
import javax.json.JsonReader;
import noviprojekat.Klijent;
import noviprojekat.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author makib
 */
public class Gui extends javax.swing.JFrame {

    /**
     * Creates new form Gui
     */
    
    Klijent k;
    
    public Gui(Klijent klijent) {
        k = klijent;
        initComponents();
        setDeliverEnable(false);
        bracnoStanjeComboBox.addItem("neozenjen/neudata");
        bracnoStanjeComboBox.addItem("ozenjen/udata");
        bracnoStanjeComboBox.addItem("udovac/udovica");
        
        polComboBox.addItem("muski");
        polComboBox.addItem("zenski");
        polComboBox.addItem("drugi");
        this.setVisible(true);
    }
   
    
    
  
    //geteri
    public String getJMBG(){
        return jbmgTxt.getText();
    }
    public String getIme(){
        return imeTxt.getText();
    }
    public String getPrezime(){
        return prezimeTxt.getText();
    }
    public String getImeOca(){
        return imeOcaTxt.getText();
    }
    public String getImeMajke(){
        return imeMajkeTxt.getText();
    }
    public String getPreizmeOca(){
        return prezOcaTxt.getText();
    }
    public String getPrezimeMajke(){
        return prezMajkeTxt.getText();
    }
    public String getNacionalnost(){
        return nacTxt.getText();
    }
    public String getProfesija(){
        return profTxt.getText();
    }
    public String getUlica(){
        return ulicaTxt.getText();
    }
    public String getBroj(){
        return brojTxt.getText();
    }
    public String getOpstina(){
        return opstinaTxt.getText();
    }
    public String getDan(){
        return danTxt.getText();
    }
    public String getMesec(){
        return mesecTxt.getText();
    }
    public String getGodina(){
        return godinaTxt.getText();
    }
    public String getPol(){
        return polComboBox.getSelectedItem().toString();
    }
    public String getBracnoStanje(){
        return bracnoStanjeComboBox.getSelectedItem().toString();
    }
    public String getDatum(){
        return danTxt.getText() + "-" + mesecTxt.getText() + "-" + godinaTxt.getText();
    }
    
   
    public void setDeliverEnable(boolean flag){
        deliverBtn.setEnabled(flag);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        opstinaTxt = new javax.swing.JTextField();
        brojTxt = new javax.swing.JTextField();
        ulicaTxt = new javax.swing.JTextField();
        profTxt = new javax.swing.JTextField();
        nacTxt = new javax.swing.JTextField();
        prezOcaTxt = new javax.swing.JTextField();
        prezMajkeTxt = new javax.swing.JTextField();
        imeOcaTxt = new javax.swing.JTextField();
        imeMajkeTxt = new javax.swing.JTextField();
        prezimeTxt = new javax.swing.JTextField();
        imeTxt = new javax.swing.JTextField();
        jbmgTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        requestBtn = new javax.swing.JButton();
        polComboBox = new javax.swing.JComboBox<>();
        bracnoStanjeComboBox = new javax.swing.JComboBox<>();
        danTxt = new javax.swing.JTextField();
        mesecTxt = new javax.swing.JTextField();
        godinaTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        idStatusTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        statusRequestBtn = new javax.swing.JButton();
        deliverBtn = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        idIspisLb = new javax.swing.JLabel();
        statusIspisLb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("JMBG:");

        jLabel2.setText("ime:");

        jLabel3.setText("prezime:");

        jLabel4.setText("ime majke:");

        jLabel5.setText("ime oca:");

        jLabel6.setText("prezime majke:");

        jLabel7.setText("nacionalnost:");

        jLabel8.setText("profesija:");

        jLabel9.setText("ulica prebivalista:");

        jLabel10.setText("prezime oca:");

        jLabel11.setText("opstina prebivalista:");

        jLabel12.setText("broj prebivalista:");

        jLabel13.setText("pol:");

        jLabel14.setText("bracno stanje:");

        jLabel15.setText("datum rodjenja:");

        requestBtn.setText("kreiraj zahtev");
        requestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestBtnActionPerformed(evt);
            }
        });

        jLabel16.setText("Status:");

        jLabel17.setText("ID:");

        statusRequestBtn.setText("Trazi Status");
        statusRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusRequestBtnActionPerformed(evt);
            }
        });

        deliverBtn.setText("Uruci");
        deliverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliverBtnActionPerformed(evt);
            }
        });

        jLabel18.setText("Za id:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel13)
                            .addComponent(requestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel17))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jbmgTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                        .addComponent(imeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(prezimeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(imeMajkeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(imeOcaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(prezMajkeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(prezOcaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(profTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nacTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ulicaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(brojTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(opstinaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bracnoStanjeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 96, Short.MAX_VALUE)
                                        .addComponent(polComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(danTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mesecTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(godinaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idIspisLb)
                                    .addComponent(idStatusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusIspisLb)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusRequestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbmgTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(imeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(prezimeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(imeMajkeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imeOcaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(prezMajkeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(prezOcaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nacTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(profTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ulicaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(brojTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(opstinaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(polComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(bracnoStanjeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(danTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesecTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(godinaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(requestBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(idStatusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(idIspisLb))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(statusIspisLb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusRequestBtn)
                    .addComponent(deliverBtn))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void requestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestBtnActionPerformed
        k.createRequest();
    }//GEN-LAST:event_requestBtnActionPerformed

    private void deliverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliverBtnActionPerformed
        k.deliver(idStatusTxt.getText());
        statusIspisLb.setText("urucen");
        
    }//GEN-LAST:event_deliverBtnActionPerformed

    private void statusRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusRequestBtnActionPerformed
        
        try {
            String status = k.statusRequest(idStatusTxt.getText());
            idIspisLb.setText(idStatusTxt.getText());
            statusIspisLb.setText(status);
            
        } catch (ProtocolException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_statusRequestBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bracnoStanjeComboBox;
    private javax.swing.JTextField brojTxt;
    private javax.swing.JTextField danTxt;
    private javax.swing.JButton deliverBtn;
    private javax.swing.JTextField godinaTxt;
    private javax.swing.JLabel idIspisLb;
    private javax.swing.JTextField idStatusTxt;
    private javax.swing.JTextField imeMajkeTxt;
    private javax.swing.JTextField imeOcaTxt;
    private javax.swing.JTextField imeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jbmgTxt;
    private javax.swing.JTextField mesecTxt;
    private javax.swing.JTextField nacTxt;
    private javax.swing.JTextField opstinaTxt;
    private javax.swing.JComboBox<String> polComboBox;
    private javax.swing.JTextField prezMajkeTxt;
    private javax.swing.JTextField prezOcaTxt;
    private javax.swing.JTextField prezimeTxt;
    private javax.swing.JTextField profTxt;
    private javax.swing.JButton requestBtn;
    private javax.swing.JLabel statusIspisLb;
    private javax.swing.JButton statusRequestBtn;
    private javax.swing.JTextField ulicaTxt;
    // End of variables declaration//GEN-END:variables
}


