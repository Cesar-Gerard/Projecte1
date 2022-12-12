/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.vista;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.model.Album;
import org.milaifontanals.model.Artista;
import org.milaifontanals.model.Canso;
import org.milaifontanals.model.Estil;
import org.milaifontanals.persistencia.CapaPersistencia;
import org.milaifontanals.persistencia.GestorBDEmpresaException;

/**
 *
 * @author isard
 */
public class Contingut_Album extends javax.swing.JDialog {

    private CapaPersistencia cBD = null;
    List<Estil> est = new ArrayList<Estil>();
    List<Artista> art = new ArrayList<Artista>();
    List<Canso> alb_cont = new ArrayList<Canso>();
    List<Canso> alb_taula = new ArrayList<Canso>();
    
    int j;
    int hores;
    int minuts;
    int segons;
    
    
    
    /**
     * Creates new form Contingut_Album
     */
    public Contingut_Album(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Estil_combo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        Titol_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Artista_Combo = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        Cerca_BT = new javax.swing.JButton();
        Netejar_BT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Afegir_BT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Eliminar_BT = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        Guardar_BT = new javax.swing.JButton();
        Cancelar_BT = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Inserir cançons al àlbum"));

        jLabel1.setText("Estil: ");

        jLabel2.setText("Títol: ");

        jLabel3.setText("Artista: ");

        Cerca_BT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/Imatges/icons8-search-32.png"))); // NOI18N
        Cerca_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cerca_BTActionPerformed(evt);
            }
        });

        Netejar_BT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/Imatges/icons8-erase-32.png"))); // NOI18N
        Netejar_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Netejar_BTActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        Afegir_BT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/Imatges/icons8-add-file-16.png"))); // NOI18N
        Afegir_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Afegir_BTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(43, 43, 43)
                        .addComponent(Titol_field, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Artista_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(45, 45, 45)
                            .addComponent(Estil_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Afegir_BT, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Netejar_BT)
                            .addComponent(Cerca_BT))
                        .addGap(159, 159, 159))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(Estil_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Cerca_BT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Titol_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(Artista_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Netejar_BT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Afegir_BT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Contingut actual del àlbum"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        Eliminar_BT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/Imatges/icons8-trash-16.png"))); // NOI18N
        Eliminar_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_BTActionPerformed(evt);
            }
        });

        jLabel4.setText("Eliminar contingut seleccionat:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Eliminar_BT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addContainerGap(20, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(Eliminar_BT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        Guardar_BT.setText("Guardar");
        Guardar_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_BTActionPerformed(evt);
            }
        });

        Cancelar_BT.setText("Cancelar");
        Cancelar_BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_BTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancelar_BT)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Guardar_BT)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Guardar_BT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cancelar_BT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Netejar_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Netejar_BTActionPerformed
        DadesTaula();
        Titol_field.setText("");
        Estil_combo.setSelectedIndex(0);
        Artista_Combo.setSelectedIndex(0);
    }//GEN-LAST:event_Netejar_BTActionPerformed

    private void Cancelar_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_BTActionPerformed
        this.dispose();
    }//GEN-LAST:event_Cancelar_BTActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Carreguem el combo de estils i artisyes
        

        try {
            est=cBD.getEstils();   
            Estil_combo.addItem("Tots");
            for(int i =0; i<est.size();i++){
                Estil_combo.addItem(est.get(i).getNom());
            }
            
            
            art=cBD.TaulaInterpret();
            
            Artista_Combo.addItem("Tots");
            for(int j=0; j<art.size();j++){
                Artista_Combo.addItem(art.get(j).getNom());
            }
            
            
            DadesTaula();
            ContingutTaulaCont(alb_cont);
        } catch (GestorBDEmpresaException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Ups, ha hagut un error!", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_formWindowOpened

    private void Afegir_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Afegir_BTActionPerformed

          j = jTable1.getSelectedRow();
          
        
        
        if(j==-1){
            JOptionPane.showMessageDialog(this, "Hem de seleccionar un Autor per la canço que volm crear");
        }else{

            Canso agafar= alb_taula.get(j);
            
           boolean trobat=false;
            for(int i =0; i<alb_cont.size();i++){
                if(alb_cont.get(i).getTitol().equals(agafar.getTitol())){
                    trobat=true;
                }
            }
            
                if(trobat==true){
                     JOptionPane.showMessageDialog(this, "Aquesta canço ja esta incluida en el àlbum");
                }else if(trobat==false){
                     alb_cont.add(agafar);
                       ContingutTaulaCont(alb_cont);
                }
           
           
          
            
        }
    }//GEN-LAST:event_Afegir_BTActionPerformed

    private void Eliminar_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_BTActionPerformed
        
        int x= jTable2.getSelectedRow();
        if(x==-1){
            JOptionPane.showMessageDialog(this, "Hem de seleccionar una canso que volem eliminar");
        }else{
            alb_cont.remove(x);
        ContingutTaulaCont(alb_cont);
        }
       
        
        
    }//GEN-LAST:event_Eliminar_BTActionPerformed

    private void Cerca_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cerca_BTActionPerformed
        
        List<Canso> filtre = new ArrayList<Canso>();
        String artista=Artista_Combo.getSelectedItem().toString();
        
        Estil estil = new Estil (Estil_combo.getSelectedItem().toString());
        
        if(estil.getNom().contains("Tots")){
            estil.setNom("");
        }
        
        if(Artista_Combo.getSelectedItem().toString().contains("Tots")){
            artista="";
        }
        
        try {
            filtre = cBD.getCançonsAlbum(estil, Titol_field.getText(),artista);
            ContingutTaulaCansons(filtre);
        } catch (GestorBDEmpresaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Ups, ha hagut un error!", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_Cerca_BTActionPerformed

    private void Guardar_BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_BTActionPerformed
       //Anem a fer la inserció de la llista alb_cont juntament amb el id del album que hem passat desde la anterior pantalla per poder tenir a mà la seva informació en aquesta pantalla
    double durada =0;
    long id = 0;
    
       try {
       id = cBD.getIDProducteAfegit();
       for(int i=0; i<alb_cont.size();i++){
             
            cBD.afegirCansoAlbum(id,alb_cont.get(i).getId());
               
              durada=durada +alb_cont.get(i).getDurada();
          
       }
       
       cBD.updateDuradaAlbum(durada,id);
       CalcularTemps(durada);
       
         JOptionPane.showMessageDialog(this, "Cançons afegides al contingut del àlbum sense cap problema");
         JOptionPane.showMessageDialog(this, "La durada del àlbum s'ha actualitzat amb la suma de la durada del seu contingut:"+hores+":"+minuts+":"+segons);
       
      } catch (GestorBDEmpresaException ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage(),"Ups, ha hagut un error!", JOptionPane.ERROR_MESSAGE);
        }
       
    }//GEN-LAST:event_Guardar_BTActionPerformed

    
    
    
    void setConnexio(CapaPersistencia conn) {
     cBD=conn;
    }

    

    public void DadesTaula() {
         try{
            
            alb_taula= cBD.getCanso();
            
            ContingutTaulaCansons(alb_taula);
            
        }catch (GestorBDEmpresaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Ups, ha hagut un error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ContingutTaulaCansons(List<Canso> cont) {
        jTable1.removeAll();
         String columnNames []={"ID","NOM","DURADA","ESTIL","ANY CREACIO"};
            String data[][]= new String [cont.size()][5];
         
         
        for(int i =0; i<cont.size();i++){
                
            double durada = cont.get(i).getDurada();
            CalcularTemps(durada);
            String any = String.valueOf(cont.get(i).getAnyCreacio());
            
            
                data[i][0]=cont.get(i).getIdString();
                data[i][1]=cont.get(i).getTitol();
                data[i][2]=(hores+":"+minuts+":"+segons);
                data[i][3]=cont.get(i).getEstil().getNom();
                data[i][4]=any;
                
            
             
            }
            
            jTable1.setDefaultEditor(Object.class, null);
            jTable1.setModel(new DefaultTableModel(data,columnNames));
            
            
    }
    
    
     private void ContingutTaulaCont(List<Canso> cont) {
        jTable2.removeAll();
         String columnNames []={"ID","NOM","DURADA","ESTIL","ANY CREACIO"};
            String data[][]= new String [cont.size()][5];
         
         
        for(int i =0; i<cont.size();i++){
                
               double durada = cont.get(i).getDurada();
            CalcularTemps(durada);
            String any = String.valueOf(cont.get(i).getAnyCreacio());
            
            
                data[i][0]=cont.get(i).getIdString();
                data[i][1]=cont.get(i).getTitol();
                data[i][2]=(hores+":"+minuts+":"+segons);
                data[i][3]=cont.get(i).getEstil().getNom();
                data[i][4]=any;
            
             
            }
            
            jTable2.setDefaultEditor(Object.class, null);
            jTable2.setModel(new DefaultTableModel(data,columnNames));
            
            
    }
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
            java.util.logging.Logger.getLogger(Contingut_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contingut_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contingut_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contingut_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Contingut_Album dialog = new Contingut_Album(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Afegir_BT;
    private javax.swing.JComboBox<String> Artista_Combo;
    private javax.swing.JButton Cancelar_BT;
    private javax.swing.JButton Cerca_BT;
    private javax.swing.JButton Eliminar_BT;
    private javax.swing.JComboBox<String> Estil_combo;
    private javax.swing.JButton Guardar_BT;
    private javax.swing.JButton Netejar_BT;
    private javax.swing.JTextField Titol_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    private void CalcularTemps(double durada) {
            double calcul;
            calcul= durada/60;
            hores=(int)calcul;
            calcul=calcul-hores;
            calcul=calcul*60;
            minuts=(int)calcul;
            calcul=calcul-minuts;
            calcul=calcul*60;
            segons=(int)calcul;
            
    }

    
    private long ConvertirTemps(int hores, int minuts, int segons) {
    long resultat=0;
    long hores_m;
    long segons_m;
    
    hores_m= hores*60;
    segons_m=segons /60;
    resultat=minuts+hores_m+segons_m;
    
    return resultat;
    }
}
