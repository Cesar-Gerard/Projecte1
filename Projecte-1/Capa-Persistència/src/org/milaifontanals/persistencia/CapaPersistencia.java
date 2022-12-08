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
import javax.swing.JOptionPane;
import org.milaifontanals.model.Album;
import org.milaifontanals.model.Artista;
import org.milaifontanals.model.ArtistaGrupal;
import org.milaifontanals.model.ArtistaIndividual;
import org.milaifontanals.model.Canso;
import org.milaifontanals.model.Client;
import org.milaifontanals.model.Estil;
import org.milaifontanals.model.Llista;
import org.milaifontanals.model.Pais;
import org.milaifontanals.model.Prod_Rep;
import org.milaifontanals.model.Producte;
import org.milaifontanals.model.Reproducció;
import org.milaifontanals.model.TipusArtista;
import org.milaifontanals.model.Tipus_Producte;
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
      long id_producte=0;
        try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("select cat_id from cataleg where cat_titol = '"+r.getIdProducte().getTitol()+"'");
            while(rs.next()){
                id_producte=rs.getLong("cat_id");
            }
            //No fa falta fer una consulta per trobar el id dels clients ja que en el comboBox ja esta guardada aquesta informacio
            ResultSet rs2 = q.executeQuery("insert into reproduccio (rep_idclients,rep_mt,rep_idcataleg) values ("+r.getIdClient().getId()+",'"+r.getRep_mt()+"',"+id_producte+")");
            rs2=q.executeQuery("commit");
            rs2.close();
            rs.close();
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
   
   
  
   
   //omple la taula amb totes les reproduccions que tenim
   public List <Reproducció>  contingutTaula() throws GestorBDEmpresaException, ParseException{
       List<Reproducció> rep = new ArrayList<Reproducció>();
       Client entrada = null;
       Prod_Rep prod = null;
       int id_rep = 0;
       
       
       
       Statement q = null;
       try{
            q= conn.createStatement();
            ResultSet rs = q.executeQuery("select rep_idclients,rep_mt,rep_idcataleg,cli_nom,cat_titol,cat_tipus,rep_id from clients join reproduccio on rep_idclients= clients.cli_id join cataleg on rep_idcataleg=cat_id order by rep_mt DESC,rep_idclients ASC");

            while(rs.next()){
                
                id_rep=rs.getInt("rep_id");
               prod= new Prod_Rep(rs.getLong("rep_idcataleg"),rs.getString("cat_titol"));
                entrada = new Client(rs.getLong("rep_idclients"),rs.getString("cli_nom"));
                
                rep.add(new Reproducció(id_rep,rs.getDate("rep_mt"),entrada,prod));
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
   
   
   
   
   
   public List<Producte> Omplir_Taula_Productes() throws GestorBDEmpresaException{
       List<Producte> prod = new ArrayList<Producte>();
       
       Canso song = null;
       Album alb = null;
       Llista list = null;
       boolean estat;
      
       Estil est = null;
       
       
       
       PreparedStatement q = null;
       try{
            q= conn.prepareStatement("select cat_id, cat_titol,cat_actiu,cat_estil,cat_tipus from cataleg where cat_actiu=?");
            q.setString(1, "Actiu");
            
            ResultSet rs = q.executeQuery();
            
            while(rs.next()){
                
                if(rs.getString("cat_actiu").equals("Actiu")){
                    estat=true;
                }else{
                    estat=false;
                }
                
                est = new Estil(rs.getString("cat_estil"));
                
             switch(rs.getString("cat_tipus")){
                 case ("C"):
                 
                 song = new Canso(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,rs.getString("cat_tipus"));
                 prod.add(song);
                 break;
                 
                 case ("A"):
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,rs.getString("cat_tipus"));
                prod.add(alb);
                 break;
                 
                 case ("L"):
                 list = new Llista(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,rs.getString("cat_tipus"));
                prod.add(list);
                 break;
                 
             }
              
                
            }
            
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de productes.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de reproduccions.\n" + ex.getMessage());
                }
            }
        }
       
       return prod;
       
       
   }
   
   public List<Producte> getProductes(String titol,String state, String estil, List<Tipus_Producte>tp) throws GestorBDEmpresaException{
       List<Producte> filtre = new ArrayList<Producte>();
       Canso song = null;
       Album alb = null;
       Llista list = null;
       boolean estat;
      
       Estil est = null;
       
       
       
       PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("select cat_id, cat_titol,cat_actiu,cat_estil,cat_tipus from cataleg where cat_titol like ? and REGEXP_LIKE(cat_actiu, ?) and cat_estil like ? and REGEXP_LIKE(cat_tipus, ?)");
           
           q.setString(1, "%" + titol + "%");
              
           if(state == null){
            String tipus = "Actiu"+"|"+"Inactiu";
               q.setString(2, tipus );
           }else{
               q.setString(2, state );
           }
           
           q.setString(3,estil);
           
           
           
           
           
           String tipus = tp.get(0).toString()+"|"+tp.get(1).toString()+"|"+tp.get(2).toString();
           q.setString(4, tipus);
         
           
             
            ResultSet rs = q.executeQuery();
            
            while(rs.next()){
                
                if(rs.getString("cat_actiu").equals("Actiu")){
                    estat=true;
                }else{
                    estat=false;
                }
                est = new Estil(rs.getString("cat_estil"));
              
             switch(rs.getString("cat_tipus")){
                 case ("C"):
                 
                 song = new Canso(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,rs.getString("cat_tipus"));
                 filtre.add(song);
                 break;
                 
                 case ("A"):
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,rs.getString("cat_tipus"));
                filtre.add(alb);
                 break;
                 
                 case ("L"):
                 list = new Llista(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,rs.getString("cat_tipus"));
                filtre.add(list);
                 break;
                 
             }
              
                
            }
           
            
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de productes.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de reproduccions.\n" + ex.getMessage());
                }
            }
        }
       
       
       
       return filtre;
   }
   
   //Elimina la reproduccio que li passem
   public void eliminarReproduccio(Reproducció r) throws GestorBDEmpresaException{
       
       
       Statement q = null;
       long id=0;
      
        try{
            q= conn.createStatement();
            ResultSet rs2 = q.executeQuery("select cli_id from clients where cli_nom = '"+r.getIdClient().getNom()+"'");
            while(rs2.next()){
                id = rs2.getLong("cli_id");
            }
            
            rs2=q.executeQuery("delete from reproduccio where rep_idclients ="+id+" and rep_mt='"+r.getRep_mt()+"'");
            rs2=q.executeQuery("commit");
            rs2.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar eliminar la reproduccio.\n" + ex.getMessage());
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
   
      
   public List<Artista> TaulaAutor() throws GestorBDEmpresaException{
       List<Artista> resultat = new ArrayList<Artista>();
       
       Statement q = null;
       TipusArtista tp=null;
       
       Pais p =null;
     
        try{
            q= conn.createStatement();
                                  
            ResultSet rs = q.executeQuery("select art_id,art_nom,art_tipus from artista where art_tipus like 'Individual'");
                while(rs.next()){
                    
                   
                       
                        tp=TipusArtista.Individual;
                        
                        ArtistaIndividual ind = new ArtistaIndividual(rs.getLong("art_id"),rs.getString("art_nom"),tp);
                        resultat.add(ind);
                       
                    
                }
                
            rs.close();    
 
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de artistes per la Taula Interpret.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de artistes per la Taula Interpret.\n" + ex.getMessage());
                }
            }
        }
        
       
        return resultat;
   }
   
   public List<Artista> TaulaInterpret() throws GestorBDEmpresaException{
       List<Artista> resultat = new ArrayList<Artista>();
       
       Statement q = null;
       TipusArtista tp=null;
       
       Pais p =null;
     
        try{
            q= conn.createStatement();
                                  
            ResultSet rs = q.executeQuery("select art_id,art_nom,art_tipus from artista");
                while(rs.next()){
                    
                   switch(rs.getString("art_tipus")){
                       case("Individual"):
                            tp=TipusArtista.Individual;
                        
                        ArtistaIndividual ind = new ArtistaIndividual(rs.getLong("art_id"),rs.getString("art_nom"),tp);
                        resultat.add(ind);
                           
                        break;
                        
                        
                       case ("Grupal"):
                            tp=TipusArtista.Grupal;
                        
                        ArtistaGrupal gr = new ArtistaGrupal(rs.getLong("art_id"),rs.getString("art_nom"),tp);
                        resultat.add(gr);
                           
                        break;
                       
                   }
                       
                       
                       
                    
                }
                
            rs.close();    
 
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de paisos.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de paisos.\n" + ex.getMessage());
                }
            }
        }
        
       
        return resultat;
   }
   
   
   public List<Artista> FiltreInterpret(String nom,TipusArtista tp) throws GestorBDEmpresaException{
       List<Artista> resultat = new ArrayList<Artista>();
       
       TipusArtista ta=null;
       
         PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("select art_id,art_nom,art_tipus from artista where art_nom like ? and art_tipus like ?");
           q.setString(1, "%" + nom + "%");
           if(tp == null){
               q.setString(2,"%" + "" + "%");
           }else{
               q.setString(2,"%" + tp.toString() + "%");
           }
           

            ResultSet rs = q.executeQuery();
            
            while(rs.next()){
                switch(rs.getString("art_tipus")){
                    case("Individual"):
                        ta=TipusArtista.Individual;
                    ArtistaIndividual ind = new ArtistaIndividual(rs.getLong("art_id"),rs.getString("art_nom"),ta);
                    resultat.add(ind);
                   break;
                        
                        
                     case("Grupal"):
                           ta=TipusArtista.Grupal;
                    ArtistaGrupal gr = new ArtistaGrupal(rs.getLong("art_id"),rs.getString("art_nom"),ta);
                          resultat.add(gr);
                     break;
                }
               
              
                
            }
           
            
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de interprets.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de interprets.\n" + ex.getMessage());
                }
            }
        }
        
       
        return resultat;
   }


   
       
   
   
   
   
   
   
   //Obtenim la reproduccio o reproduccions del filtre
   public List<Reproducció> getReproducció(Reproducció r) throws GestorBDEmpresaException, ParseException{
        List<Reproducció> llrep = new ArrayList<Reproducció>();
        Client entrada = null;
        Prod_Rep prod=null;
       long id_producte=0;
        
        Statement q = null;
        try{
            q= conn.createStatement();
                                  
            ResultSet rs = q.executeQuery("select cli_nom,rep_idclients,rep_mt,cat_titol,rep_idcataleg from clients join reproduccio on cli_nom = '"+r.getIdClient().getNom()+"' and rep_idclients = cli_id join cataleg on cat_titol='"+r.getIdProducte().getTitol()+"' and rep_mt='"+r.getRep_mt()+"'");
                while(rs.next()){
                    prod = new Prod_Rep(rs.getLong("rep_idcataleg"),rs.getString("cat_titol"));
                    entrada = new Client(rs.getLong("rep_idclients"),rs.getString("cli_nom"));
                    llrep.add(new Reproducció(rs.getDate("rep_mt"),entrada,prod));
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
   
   //Editem la reproduccio que li passem per parametre
   public void editarReproduccio(Reproducció nova, Reproducció vella) throws GestorBDEmpresaException{
       
       Statement q = null;
       long id_client=0;
       long id_producte=0;
       int id_rep=0;
      
        try{
            q= conn.createStatement();
            ResultSet rs2 = q.executeQuery("select rep_id,cli_id,cat_id from clients join reproduccio on  cli_id=rep_idclients and cli_nom='"+vella.getIdClient().getNom()+"' join cataleg on cat_titol='"+vella.getIdProducte().getTitol()+"' and rep_mt ='"+vella.getRep_mt()+"'");
            
            while(rs2.next()){
                
                id_rep=rs2.getInt("rep_id");
     
            }
            rs2.close();
            
            ResultSet rs3 = q.executeQuery("select cli_id,cat_id from clients join reproduccio on cli_nom='"+nova.getIdClient().getNom()+"' join cataleg on cat_titol='"+nova.getIdProducte().getTitol()+"'");
            
            
            while(rs3.next()){
                id_producte=rs3.getLong("cat_id");
                id_client=rs3.getLong("cli_id");
             }
                rs3.close();
                
           
                
            ResultSet rs=q.executeQuery("update reproduccio set rep_mt='"+nova.getRep_mt()+"', rep_idclients ='"+id_client+"',rep_idcataleg='"+id_producte+"' where rep_id ='"+id_rep+"'");
            rs=q.executeQuery("commit");
            rs.close();
                
         
            
            
            
            
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar eliminar la reproduccio.\n" + ex.getMessage());
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
   
   
    
    public static void main(String[] args) throws GestorBDEmpresaException {
        
        
    }
    
    
}


