/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.milaifontanals.model.Client;
import org.milaifontanals.model.Estil;
import org.milaifontanals.model.Producte;
import org.milaifontanals.model.Reproducció;
/**
 *
 * @author gerar
 */
public class CapaPersistencia  {
    private Connection conn;
    
    public CapaPersistencia() throws GestorBDEmpresaException{
        this("empresaJDBC.properties");
    }
    
    public CapaPersistencia (String nomFitxerPropietats) throws GestorBDEmpresaException{
        try{
            Properties props = new Properties();
            props.load(new FileInputStream(nomFitxerPropietats));
            String[] claus = {"url", "user", "password"};
            String[] valors = new String[3];
            for (int i = 0; i < claus.length; i++) {
                valors[i] = props.getProperty(claus[i]);
                if (valors[i] == null || valors[i].isEmpty()) {
                    throw new GestorBDEmpresaException("L'arxiu " + nomFitxerPropietats + " no troba la clau " + claus[i]);
                }
            }
            
            conn = DriverManager.getConnection(valors[0], valors[1], valors[2]);
            conn.setAutoCommit(false);
        } catch (IOException ex) {
            throw new GestorBDEmpresaException("Problemes en recuperar l'arxiu de configuració " + nomFitxerPropietats + "\n" + ex.getMessage());
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("No es pot establir la connexió.\n" + ex.getMessage());
        }
        
    }
    
    /**
     * Tanca la connexió
     */
    public void close() throws GestorBDEmpresaException {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new GestorBDEmpresaException("Error en fer rollback final.\n" + ex.getMessage());
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new GestorBDEmpresaException("Error en tancar la connexió.\n" + ex.getMessage());
            }
        }
    }
    
    
    //Retorna tots els estils creats
    public  List<Estil> getEstils()throws GestorBDEmpresaException{
        List <Estil> llEstils = new ArrayList<Estil>();
        Statement q = null;
        try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT est_id,est_nom from estil");
            while(rs.next()){
                llEstils.add(new Estil(rs.getInt("est_id"),rs.getString("est_nom")));
            }
            rs.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de estils.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de estils.\n" + ex.getMessage());
                }
            }
        }
        
        
        return llEstils;
    }
    
    
    //Omple el combobox de reproduccio_vista amb tots els noms dels clients
    public List<Client> LlistaClients()throws GestorBDEmpresaException{
        List <Client> llclients = new ArrayList<Client>();
        Statement q = null;
        try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT cli_id,cli_nom,cli_cognoms from Clients");
            while(rs.next()){
                llclients.add(new Client(rs.getLong("cli_id"),rs.getString("cli_nom"),rs.getString("cli_cognoms")));
            }
            rs.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de clients.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de estils.\n" + ex.getMessage());
                }
            }
        }
        
        
        return llclients;
    }
    
    
   
  

    
    //Retorna el client que demanem 
    public Client getClient(String id) throws GestorBDEmpresaException{
        Client resultat = null;
        
        
        Statement q = null;
        try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT cli_id,cli_nom,cli_cognoms from Clients where cli_nom='"+id+"'");
            while(rs.next()){
                resultat =(new Client(rs.getLong("cli_id"),rs.getString("cli_nom"),rs.getString("cli_cognoms")));
            }
            rs.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de clients.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de estils.\n" + ex.getMessage());
                }
            }
        }
        
       
        return resultat;
    }

    
    //Afegir noves reproduccions
    
   public void afegirReproducció(Reproducció r) throws GestorBDEmpresaException{
       
       
       Statement q = null;
      
        try{
            q= conn.createStatement();
            
            ResultSet rs2 = q.executeQuery("insert into reproduccio (rep_idclients,rep_mt) values ("+r.getIdClient().getId()+",'"+r.getRep_mt()+"')");

            rs2.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir la nova reproduccio.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit la nova reproduccio.\n" + ex.getMessage());
                }
            }
        }
        
        
        
       
       
   }
   
   
   //Comprovar estat  reproduccions
   
   public  void LlistaReproducció() throws GestorBDEmpresaException{
       List<Reproducció> rep = new ArrayList<Reproducció>();
       Client entrada = null;

       
       
       Statement q = null;
       try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT rep_idclients,rep_mt from reproduccio");
            while(rs.next()){
                
                entrada = new Client(rs.getLong("rep_idclients"));
                rep.add(new Reproducció(rs.getDate("rep_mt"),entrada));
            }
            rs.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de reproduccions.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de reproduccions.\n" + ex.getMessage());
                }
            }
        }
        
        for(int i =0; i<rep.size();i++){
            System.out.println(rep.get(i).getIdClient().getId()+"-------"+rep.get(i).getRep_mt());
        }
       
       
       
   }
   
   
   //omple la taula amb totes les reproduccions que tenim
   public List <Reproducció>  contingutTaula() throws GestorBDEmpresaException, ParseException{
       List<Reproducció> rep = new ArrayList<Reproducció>();
       Client entrada = null;
       
       
       
       Statement q = null;
       try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("select rep_idclients,rep_mt,cli_nom from reproduccio join clients on rep_idclients= clients.cli_id");

            while(rs.next()){
                
                entrada = new Client(rs.getLong("rep_idclients"),rs.getString("cli_nom"));
                rep.add(new Reproducció(rs.getDate("rep_mt"),entrada));
            }
            
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de reproduccions.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de reproduccions.\n" + ex.getMessage());
                }
            }
        }
        
        return rep;
   
   }
   
   
   
   /*public void eliminarReproduccio(Reproducció r) throws GestorBDEmpresaException{
       
       
       Statement q = null;
      
        try{
            q= conn.createStatement();
            
            ResultSet rs2 = q.executeQuery("insert into reproduccio (rep_idclients,rep_mt) values ("+r.getIdClient().getId()+",'"+r.getRep_mt()+"')");

            rs2.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir la nova reproduccio.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit la nova reproduccio.\n" + ex.getMessage());
                }
            }
        }
        
        
        
       
       
   }
   */
   
   //Obtenim la reproduccio o reproduccions del filtre
   public List<Reproducció> getReproducció(Reproducció r) throws GestorBDEmpresaException, ParseException{
        List<Reproducció> llrep = new ArrayList<Reproducció>();
        Client entrada = null;
       
        
        Statement q = null;
        try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT rep_idclients,rep_mt,cli_nom from reproduccio join clients on rep_idclients = clients.cli_id  and rep_mt = '"+r.getRep_mt()+"' and clients.cli_nom = '"+r.getIdClient().getNom()+"'");
            while(rs.next()){
                
                entrada = new Client(rs.getLong("rep_idclients"),rs.getString("cli_nom"));
                llrep.add(new Reproducció(rs.getDate("rep_mt"),entrada));
            }
            rs.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de clients.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de estils.\n" + ex.getMessage());
                }
            }
        }
        
       
        return llrep;
    }
   
   
   
   
   
    
    public static void main(String[] args) throws GestorBDEmpresaException {
        
        
    }
    
    
}


