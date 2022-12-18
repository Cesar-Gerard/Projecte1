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
import java.util.logging.Level;
import java.util.logging.Logger;
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
   
   
   
   
   //Omple la taula de filtre de la vista Productes
   public List<Producte> Omplir_Taula_Productes() throws GestorBDEmpresaException{
       List<Producte> prod = new ArrayList<Producte>();
       
       Canso song = null;
       Album alb = null;
       Llista list = null;
       boolean estat;
      Tipus_Producte tipus= null;
       Estil est = null;
       
       
       
       PreparedStatement q = null;
       try{
            q= conn.prepareStatement("select cat_id, cat_titol,cat_actiu,cat_estil,cat_tipus from cataleg where cat_actiu=? order by cat_titol asc");
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
                 tipus = Tipus_Producte.C;
                 song = new Canso(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
                 prod.add(song);
                 break;
                 
                 case ("A"):
                     tipus = Tipus_Producte.A;
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
                prod.add(alb);
                 break;
                 
                 case ("L"):
                     tipus = Tipus_Producte.L;
                 list = new Llista(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
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
   
   
   //Taula de la pantalla de la creació de llistes
   public List<Producte> Omplir_Taula_Productes_Llista() throws GestorBDEmpresaException{
       List<Producte> prod = new ArrayList<Producte>();
       
       Canso song = null;
       Album alb = null;
       Llista list = null;
       boolean estat;
      Tipus_Producte tipus= null;
       Estil est = null;
       
       
       
       PreparedStatement q = null;
       try{
            q= conn.prepareStatement("select cat_id, cat_titol,cat_actiu,cat_estil,cat_tipus from cataleg where cat_actiu=? and REGEXP_LIKE(cat_tipus, ?)order by cat_titol asc");
            q.setString(1, "Actiu");
            String t = "C"+"|"+"A";
               q.setString(2, t );
            
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
                 tipus = Tipus_Producte.C;
                 song = new Canso(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
                 prod.add(song);
                 break;
                 
                 case ("A"):
                     tipus = Tipus_Producte.A;
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
                prod.add(alb);
                 break;
                 
                 case ("L"):
                     tipus = Tipus_Producte.L;
                 list = new Llista(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
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
   
   
   
   
   //Ens dona el resultat de filtre de productes
   public List<Producte> getProductes(String titol,String state, String estil, List<Tipus_Producte>tp) throws GestorBDEmpresaException{
       List<Producte> filtre = new ArrayList<Producte>();
       Canso song = null;
       Album alb = null;
       Llista list = null;
       boolean estat;
       Tipus_Producte tp2= null;
      
       Estil est = null;
       
       
       
       PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("select cat_id, cat_titol,cat_actiu,cat_estil,cat_tipus from cataleg where cat_titol like ? and REGEXP_LIKE(cat_actiu, ?) and cat_estil like ? and REGEXP_LIKE(cat_tipus, ?) order by cat_titol asc");
           
           q.setString(1, "%" + titol + "%");
              
           if(state == null){
            String tipus = "Actiu"+"|"+"Inactiu";
               q.setString(2, tipus );
           }else{
               q.setString(2, state );
           }
           
           q.setString(3,"%" + estil + "%");
           
           
           
           
           
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
                 tp2=Tipus_Producte.C;
                 song = new Canso(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                 filtre.add(song);
                 break;
                 
                 case ("A"):
                     tp2=Tipus_Producte.A;
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                filtre.add(alb);
                 break;
                 
                 case ("L"):
                     tp2=Tipus_Producte.L;
                 list = new Llista(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
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
   
      //Ens dona el contingut per a la taula de autoria
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
   
   
   //Omplim el contingut de la taula interpret
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
            throw new GestorBDEmpresaException("Error en intentar omplir la Taula Interpret.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que de omplir la Taula Interpret\n" + ex.getMessage());
                }
            }
        }
        
       
        return resultat;
   }
   
   //Filtre interpret
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

//Filtre Autoria 
   public List<Artista> FiltreAutoria(String nom) throws GestorBDEmpresaException{
       List<Artista> resultat = new ArrayList<Artista>();
       
       TipusArtista ta=null;
       
         PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("select art_id,art_nom,art_tipus from artista where art_nom like ? and art_tipus like 'Individual'");
           q.setString(1, "%" + nom + "%");
          
           
           

            ResultSet rs = q.executeQuery();
            
            while(rs.next()){

                    ta=TipusArtista.Individual;
                    ArtistaIndividual ind = new ArtistaIndividual(rs.getLong("art_id"),rs.getString("art_nom"),ta);
                    resultat.add(ind);

               
                        
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
   
   //Afegir nu producte a la taula Cataleg
   
   public void afegirProducte(String titol,boolean estat,Estil estil,Tipus_Producte tipus) throws GestorBDEmpresaException{
       
       
       PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("insert into cataleg(cat_titol,cat_actiu,cat_estil,cat_tipus) values (?,?,?,?)");
           q.setString(1,titol);
           if(estat == true){
               q.setString(2,"Actiu");
           }else{
               q.setString(2,"Inactiu");
           }
           
           q.setString(3,estil.getNom());
           q.setString(4,tipus.toString());
           
            ResultSet rs = q.executeQuery();
            q.executeQuery("commit");
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir el nou producte.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit el nou producte.\n" + ex.getMessage());
                }
            }
        }
  
   }
   
   //Ens retorna el ID que se li ha assignat de forma automatica al producte que acabem de crear
 public long getIDProducteAfegit() throws GestorBDEmpresaException{
       
       
       Statement q = null;
       long id=0;
      
        try{
            q= conn.createStatement();
            ResultSet rs2 = q.executeQuery("select max(cat_id) from cataleg");
            while(rs2.next()){
                id = rs2.getLong("max(cat_id)");
            }
            rs2.close();
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar aconseguir el id del producte creat que li ha sigut asignat.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha de aconseguir el id del producte creat que li ha sigut asignat.\n" + ex.getMessage());
                }
            }
        }
        
        
        
       return id;
   }
 
 //Inserció de una nova canço a la taula canço
    public void afegirCanso(Canso afegir) throws GestorBDEmpresaException {
        PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("insert into canço (can_id,can_any_creacio,can_interpret,can_durada) values (?,?,?,?)");
           q.setLong(1,afegir.getId());
           q.setInt(2, afegir.getAnyCreacio());
           q.setString(3,afegir.getArt().getNom());
           q.setDouble(4,afegir.getDurada());
           
            ResultSet rs = q.executeQuery();
            q.executeQuery("commit");
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir el producte en la taula canço.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit el nou producte en la taula canço.\n" + ex.getMessage());
                }
            }
        }
     }

    //Creem un nou album en la taula album
    public void afegirAlbum(Album afegir) throws GestorBDEmpresaException {
        PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("insert into album (alb_id,alb_interpret,alb_anycreacio,alb_durada) values (?,?,?,?)");
           q.setLong(1,afegir.getId());
           q.setString(2, afegir.getArt().getNom());
           q.setInt(3,afegir.getAnyCreacio());
           q.setDouble(4,afegir.getDurada());
           
            ResultSet rs = q.executeQuery();
            q.executeQuery("commit");
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir el producte en la taula canço.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit el nou producte en la taula canço.\n" + ex.getMessage());
                }
            }
        }
    
    }

    
    //Crear una nova llista en la taula Llista
    public void afegirLlista(long id) throws GestorBDEmpresaException {
        PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("insert into llista (lli_id,lli_durada) values (?,?)");
           q.setLong(1,id);
           q.setLong(2, 0);
           
           
            ResultSet rs = q.executeQuery();
            q.executeQuery("commit");
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir el producte en la taula Llista.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit la nova llista en la taula Llista.\n" + ex.getMessage());
                }
            }
        }
     }

     
    
    
    
    //Afegim una nova entrada en la taula de autoria
    public void afegirProducteAutoria(Long producte,Long artista) throws GestorBDEmpresaException {
        PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("insert into autoria (aut_idprod,aut_artid) values (?,?)");
           q.setLong(1,producte);
           q.setLong(2,artista);
           
            ResultSet rs = q.executeQuery();
            q.executeQuery("commit");
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar inserir el producte en la taula autoria.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha inserit el nou producte en la taula autoria.\n" + ex.getMessage());
                }
            }
        }
     }
    
    
    
    //Ens retorna un llistat de totes les cançons
    public List<Canso> getCanso() throws GestorBDEmpresaException{
       List<Canso> resultat = new ArrayList<Canso>();
       Canso nova = null;
       Statement q = null;
       Estil estil = null;
      
       
     
     
        try{
            q= conn.createStatement();
                                  
            ResultSet rs = q.executeQuery("select can_id,can_any_creacio,can_interpret,can_durada,cat_titol,cat_estil from canço join cataleg on can_id=cat_id");
                while(rs.next()){
                   estil = new Estil(rs.getString("cat_estil"));
                    
                    
                  nova = new Canso(rs.getInt("can_any_creacio"),rs.getLong("can_durada"),rs.getInt("can_id"),rs.getString("cat_titol"),estil);
                       
                   resultat.add(nova);
                       
                         
                }
                
            rs.close();    
 
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de cançons.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de pcançons.\n" + ex.getMessage());
                }
            }
        }
        
       
        return resultat;
   }
   
    
    
    //Filtre contingut album
    public List<Canso> getCançonsAlbum(Estil estil, String titol, String Artista) throws GestorBDEmpresaException{
        List<Canso> resultat = new ArrayList<Canso>();
       Canso nova = null;
       Estil es = null;
       
       
       PreparedStatement q = null;
       try{
           
           
           q= conn.prepareStatement("select can_id,can_any_creacio,can_interpret,can_durada,cat_titol,cat_estil from canço join cataleg on can_id=cat_id where cat_titol like ? and cat_estil like ? and can_interpret like ?");
           
           q.setString(1, "%" + titol + "%");
           
           q.setString(2, "%" + estil.getNom() + "%");
           q.setString(3, "%" +""+ "%");
          
              
         
           
             
            ResultSet rs = q.executeQuery();
            
            while(rs.next()){
                es=new Estil(rs.getString("cat_estil"));
               nova = new Canso(rs.getInt("can_any_creacio"),rs.getLong("can_durada"),rs.getInt("can_id"),rs.getString("cat_titol"),es);
                       
                   resultat.add(nova);
              
                
            }
           
            
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de cansons .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de cansons.\n" + ex.getMessage());
                }
            }
        }
       
       
       
       return resultat;
   }
    
    //Retorna una llista de caçons i albums que es poden afegir en un producte de tipus llista
    public List<Producte> getProductesLlista(Estil estil, String titol) throws GestorBDEmpresaException{
        List<Producte> resultat = new ArrayList<Producte>();
       Canso song = null;
       Album alb = null;
       Tipus_Producte tipus = null;
       Estil est = null;
       boolean estat = false;
       
       
       PreparedStatement q = null;
       try{
           
           
            q= conn.prepareStatement("select cat_id, cat_titol,cat_actiu,cat_estil,cat_tipus from cataleg where cat_titol like ? and REGEXP_LIKE(cat_actiu, ?) and cat_estil like ? and REGEXP_LIKE(cat_tipus, ?) order by cat_titol asc");
           q.setString(1, "%" + titol + "%");
           
           q.setString(2, "Actiu");
           q.setString(3, "%" + estil.getNom() + "%");
           String tp = "C"+"|"+"A";
               q.setString(4, tp );
            
          
          
              
         
           
             
            ResultSet rs = q.executeQuery();
            
            while(rs.next()){
                if(rs.getString("cat_actiu").equals("Actiu")){
                    estat=true;
                }else{
                    estat = false;
                }
                
                  est=new Estil(rs.getString("cat_estil"));
                
                
                switch(rs.getString("cat_tipus")){
                 case ("C"):
                 tipus = Tipus_Producte.C;
                 song = new Canso(rs.getLong("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
                 resultat.add(song);
                 break;
                 
                 case ("A"):
                     tipus = Tipus_Producte.A;
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tipus);
                resultat.add(alb);
                 break;
                 
               
                 
             }

              
                
            }
           
            
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de cansons .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de cansons.\n" + ex.getMessage());
                }
            }
        }
       
       
       
       return resultat;
   }
    
    
    
    //Afageix una canso al album seleccionat
public void afegirCansoAlbum(Long id, Long alb_cont) throws GestorBDEmpresaException{
       
    long pos=0;
       
       
       PreparedStatement q = null;
       PreparedStatement x = null;
       ResultSet rs = null;
       try{
           q=conn.prepareStatement("delete from album_cont where abc_idalbum=?");
           q.setLong(1, id);
           rs = q.executeQuery();
            
           q= conn.prepareStatement("select max(abc_pos)from album_cont where abc_idalbum like ?");
           
           q.setLong(1, id);

            rs = q.executeQuery();
            
            while(rs.next()){
              pos = rs.getInt("max(abc_pos)");

            }
            pos=pos+1;
            x=conn.prepareStatement("insert into album_cont(abc_idalbum,abc_idcanço,abc_pos) values(?,?,?)");
            x.setLong(1, id);
            x.setLong(2,alb_cont);
            x.setLong(3,pos);
            
            ResultSet rs2 = x.executeQuery();
            rs2=x.executeQuery("commit");
            rs2.close();
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de cansons .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de cansons.\n" + ex.getMessage());
                }
            }
        }

   }






//Afegeix contingut a la taula llista_cont
public void afegirContingutLlista(Long id, Long llc_cont) throws GestorBDEmpresaException{
       
    long pos=0;
       
       
       PreparedStatement q = null;
       PreparedStatement x = null;
       
       ResultSet rs = null;
       try{
           
           q=conn.prepareStatement("delete from llista_cont where llc_idllista=?");
           q.setLong(1, id);
           rs = q.executeQuery();
           
           
           q= conn.prepareStatement("select max(llc_pos)from llista_cont where llc_idllista like ?");
           
           q.setLong(1, id);

             rs = q.executeQuery();
            
            while(rs.next()){
              pos = rs.getInt("max(llc_pos)");

            }
            pos=pos+1;
            x=conn.prepareStatement("insert into llista_cont(llc_idllista,llc_idcataleg,llc_pos) values(?,?,?)");
            x.setLong(1, id);
            x.setLong(2,llc_cont);
            x.setLong(3,pos);
            
            ResultSet rs2 = x.executeQuery();
            rs2=x.executeQuery("commit");
            rs2.close();
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar afegir contingut a la llista .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha afegit contignut a la llista.\n" + ex.getMessage());
                }
            }
        }

   }



//Actualitzem el producte

public void updateProducte(String nom,Long id,Double durada,String tipus,String estat,Estil estil,int any) throws GestorBDEmpresaException{
       
    long pos=0;
       
       ResultSet rs =null;
       PreparedStatement q = null;
       
       
       try{
           
           switch(tipus){
               case("C"):
                   
                       System.out.println("Hola");
                   
                     q= conn.prepareStatement("update canço set can_durada=?,can_any_creacio=? where can_id=?");
                   q.setDouble(1, durada);
                   q.setLong(2, any);
                   q.setLong(3,id);
                   rs = q.executeQuery();   
                       
                       
                       
                   q= conn.prepareStatement("update cataleg set cat_titol=?,cat_actiu=?,cat_estil=? where cat_id=?");
                   q.setString(1, nom);
                   q.setString(2, estat);
                   q.setString(3, estil.getNom());
                   q.setLong(4, id);
                   rs = q.executeQuery();
                    
                   
                   
                  
                   
                   
                   
               break;
               
               case("A"):
                   
                  
                   
                   
                   q= conn.prepareStatement("update cataleg set cat_titol=?,cat_actiu=?,cat_estil=? where cat_id=?");
                   q.setString(1, nom);
                   q.setString(2, estat);
                   q.setString(3, estil.getNom());
                   q.setLong(4, id);
                   rs = q.executeQuery();
                   
                   q= conn.prepareStatement("update album set alb_durada=?,alb_anycreacio=? where alb_id=?");
                   q.setDouble(1, durada);
                   q.setLong(2, any);
                   q.setLong(3,id);
                   rs = q.executeQuery();
                   
               break;
               
               case("L"):
                   
                   q= conn.prepareStatement("update cataleg set cat_titol=?,cat_actiu=?,cat_estil=? where cat_id=?");
                   q.setString(1, nom);
                   q.setString(2, estat);
                   q.setString(3, estil.getNom());
                   q.setLong(4, id);
                   rs = q.executeQuery();
                   
                   q= conn.prepareStatement("update llista set lli_durada=? where lli_id=?");
                   q.setDouble(1, durada);
                   q.setLong(2, id);
                   rs = q.executeQuery();
                   
               break;
               
               
               
           }
           

            
           rs= q.executeQuery("commit");
           
            rs.close();
          
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar actualitzar el producte .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha actualitzat el producte.\n" + ex.getMessage());
                }
            }
        }

   }



















    //Actualitza la durada del album que li passem
   public void updateDuradaAlbum(Double durada,Long id) throws GestorBDEmpresaException{
       
    long pos=0;
       
       
       PreparedStatement q = null;
       
       try{
           
           
           q= conn.prepareStatement("update album set alb_durada=? where alb_id like ?");
           
           q.setDouble(1, durada);
           q.setLong(2,id);

            ResultSet rs = q.executeQuery();
            rs=q.executeQuery("commit");
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de cansons .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de cansons.\n" + ex.getMessage());
                }
            }
        }

   }
   
   //Actualitza la durada de la llista que li passem
   public void updateDuradLlista(Double durada,Long id) throws GestorBDEmpresaException{
       
    long pos=0;
       
       
       PreparedStatement q = null;
       
       try{
           
           
           q= conn.prepareStatement("update llista set lli_durada=? where lli_id like ?");
           
           q.setDouble(1, durada);
           q.setLong(2,id);

            ResultSet rs = q.executeQuery();
            rs=q.executeQuery("commit");
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar actualitzar la duració de la llista .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha actualitzat la duració de la llista.\n" + ex.getMessage());
                }
            }
        }

   }
   
   //Retorna la durada del producte
   public double getDurada(Long llc_cont) throws GestorBDEmpresaException{
       
    double durada=0;
       
       
       PreparedStatement q = null;
       PreparedStatement x = null;
       
       try{
           
           
           q= conn.prepareStatement("select cat_tipus from cataleg where cat_id = ?");
           
           q.setDouble(1, llc_cont);
         

            ResultSet rs = q.executeQuery();
            
             while(rs.next()){

                switch(rs.getString("cat_tipus")){
                 case ("C"):
                  x=conn.prepareStatement("select can_durada from canço where can_id = ?");
               
                   x.setLong(1,llc_cont);
                  
            
                ResultSet rs2 = x.executeQuery();
                while(rs2.next()){
                    durada=durada+(rs2.getDouble("can_durada"));
                }
                
                rs2.close();

                 break;
                 
                 case ("A"):
                      x=conn.prepareStatement("select alb_durada from album where alb_id = ?");
               
                   x.setLong(1,llc_cont);
                  
            
                ResultSet rs3 = x.executeQuery();
                 while(rs3.next()){
                   durada=durada+(rs3.getDouble("alb_durada"));
                }
                
                rs3.close();
                 break;
                 
                 
                  case ("L"):
                      x=conn.prepareStatement("select lli_durada from llista where lli_id = ?");
               
                   x.setLong(1,llc_cont);
                  
            
                ResultSet rs4 = x.executeQuery();
                 while(rs4.next()){
                   durada=durada+(rs4.getDouble("lli_durada"));
                }
                
                rs4.close();
                 break;
                 
                }
                 
             }
            
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar duracio .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la duracio.\n" + ex.getMessage());
                }
            }
        }
       
       return durada;

   }
   
   
   
   
   
   //Retorna l'any de crearció del producte
   public int getAny(Long llc_cont) throws GestorBDEmpresaException{
       
    int any=0;
       
       
       PreparedStatement q = null;
       PreparedStatement x = null;
       
       try{
           
           
           q= conn.prepareStatement("select cat_tipus from cataleg where cat_id = ?");
           
           q.setDouble(1, llc_cont);
         

            ResultSet rs = q.executeQuery();
            
             while(rs.next()){

                switch(rs.getString("cat_tipus")){
                 case ("C"):
                  x=conn.prepareStatement("select can_any_creacio from canço where can_id = ?");
               
                   x.setLong(1,llc_cont);
                  
            
                ResultSet rs2 = x.executeQuery();
                while(rs2.next()){
                   any=(rs2.getInt("can_any_creacio"));
                }
                
                rs2.close();

                 break;
                 
                 case ("A"):
                      x=conn.prepareStatement("select alb_anycreacio from album where alb_id = ?");
               
                   x.setLong(1,llc_cont);
                  
            
                ResultSet rs3 = x.executeQuery();
                 while(rs3.next()){
                    any=(rs3.getInt("alb_anycreacio"));
                }
                
                rs3.close();
                 break;
            
                 
                }
                 
             }
            
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar el any de creació .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat el any de creació.\n" + ex.getMessage());
                }
            }
        }
       
       return any;

   }

   
   
   
  
   //Actualitza Valor durada llista
   public void updateDuradaLlista(Double durada,Long id) throws GestorBDEmpresaException{
       
    long pos=0;
       
       
       PreparedStatement q = null;
       
       try{
           
           
           q= conn.prepareStatement("update llista set lli_durada=? where lli_id like ?");
           
           q.setDouble(1, durada);
           q.setLong(2,id);

            ResultSet rs = q.executeQuery();
            
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar actualitzar la duracio total de la llista .\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha actualitzar la duracio total de la llista.\n" + ex.getMessage());
                }
            }
        }

   }
   
   
   public void eliminarProducte(Long id, String tipus) throws GestorBDEmpresaException{
       
   
        
        ResultSet rs= null;
        ResultSet rs2= null;
        ResultSet rs3= null;
        Tipus_Producte tp = null;
        
       PreparedStatement q = null;
       
       try{
           
           if(tipus.equals("L")){
               
           
               
           q= conn.prepareStatement("delete from llista_cont where llc_idllista=?");
           q.setLong(1, id);
           rs = q.executeQuery();
           
           
            q= conn.prepareStatement("delete from cataleg where cat_id = ?");
            q.setLong(1, id);
            rs = q.executeQuery();
            rs=q.executeQuery("commit");
            
            
            
            rs.close();
            
           }else if(tipus.equals("A")){
               
              q= conn.prepareStatement("delete from llista_cont where llc_idcataleg=?");
              q.setLong(1, id);
              rs = q.executeQuery();
               
              
              q= conn.prepareStatement("delete from album_cont where abc_idalbum = ?");
              q.setLong(1, id);
              rs = q.executeQuery();
              
              q= conn.prepareStatement("delete from cataleg where cat_id = ?");
              q.setLong(1, id);
              rs = q.executeQuery();
              
              
              rs=q.executeQuery("commit");
            
            rs.close();

              
              
           }else if(tipus.equals("C")){
               
               
              q= conn.prepareStatement("delete from llista_cont where llc_idcataleg=?");
              q.setLong(1, id);
              rs = q.executeQuery();
               
              
              q= conn.prepareStatement("delete from album_cont where abc_idcanço = ?");
              q.setLong(1, id);
              rs = q.executeQuery();
              
              q= conn.prepareStatement("delete from cataleg where cat_id = ?");
              q.setLong(1, id);
              rs = q.executeQuery();
              
              
            rs=q.executeQuery("commit");
            
            rs.close();
            
           }

            
            

        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar eliminar el producte seleccionat.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha eliminat el producte seleccionat.\n" + ex.getMessage());
                }
            }
        }

   }
   
   
   
   //Retorna el contingut de un album per editar
   public List<Canso> ContingutAlbum(Producte prod) throws GestorBDEmpresaException{
       
    
    List<Canso> filtre = new ArrayList<Canso>();
    Canso song = null;
    Album alb = null;
    Llista list = null;
    Tipus_Producte tp2=null;
    Estil est = null;
    boolean estat;
       
       
       PreparedStatement q = null;
       
       try{
           
           
           
          
           q= conn.prepareStatement("select  cat_id, cat_titol, cat_actiu,cat_estil, cat_tipus,can_durada,can_any_creacio from cataleg join canço on can_id = cat_id join album_cont on can_id=abc_idcanço and abc_idalbum =?");

           q.setLong(1,prod.getId());
           
          
  
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
                     tp2=Tipus_Producte.C;
                 song = new Canso(rs.getInt("can_any_creacio"),rs.getLong("can_durada"),rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                filtre.add(song);
                 break;
                 
             } 
                
            }
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar el contingut de la llista.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat el contingut de la llista.\n" + ex.getMessage());
                }
            }
        }
        return filtre;
   }
   
   
   //Retorna el de una llista per editar
   public List<Producte> ContingutLlista(Producte prod) throws GestorBDEmpresaException{
       
    
    List<Producte> cont = new ArrayList<Producte>();
    Canso song = null;
    Album alb = null;
    Llista list = null;
    Tipus_Producte tp2=null;
    Estil est = null;
    boolean estat;
       
       
       PreparedStatement q = null;
       
       try{
           
           
           
          
           q= conn.prepareStatement("select cat_id,cat_titol,cat_actiu,cat_estil,cat_tipus,can_durada,alb_durada,can_any_creacio,alb_anycreacio from cataleg left join canço on can_id = cat_id left join album on alb_id = cat_id join llista_cont on llc_idcataleg = cat_id and llc_idllista=?");

           q.setLong(1,prod.getId());
           
          
  
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
                     tp2=Tipus_Producte.C;
                 song = new Canso(rs.getInt("can_any_creacio"),rs.getLong("can_durada"),rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                cont.add(song);
                 break;
                 
                 
                 case ("A"):
                     tp2=Tipus_Producte.A;
                 alb = new Album(rs.getInt("alb_anycreacio"),rs.getLong("alb_durada"),rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                cont.add(alb);
                 break;
                 
                 
                 
             }

              
                
            }
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar el contingut de la llista.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat el contingut de la llista.\n" + ex.getMessage());
                }
            }
        }
        return cont;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   //Retorna el contingut de un àlbum / llista per la seva inspecció no edició
    public List<Producte> Contingut(Producte prod) throws GestorBDEmpresaException{
       
    
    List<Producte> filtre = new ArrayList<Producte>();
    Canso song = null;
    Album alb = null;
    Llista list = null;
    Tipus_Producte tp2=null;
    Estil est = null;
    boolean estat;
       
       
       PreparedStatement q = null;
       
       try{
           
           
           
           if(prod.getTp().toString().equals("A")){
           q= conn.prepareStatement("select cat_id, cat_titol, cat_actiu,cat_estil, cat_tipus from cataleg join album_cont on cat_id=abc_idcanço and abc_idalbum =?");

           q.setLong(1,prod.getId());
           
           }else if(prod.getTp().toString().equals("L")){
           q= conn.prepareStatement("select cat_id,cat_titol, cat_actiu,cat_estil, cat_tipus from cataleg join llista_cont on cat_id=llc_idcataleg and llc_idllista =?");

           q.setLong(1,prod.getId());
           
           }
  
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
                     tp2=Tipus_Producte.C;
                 song = new Canso(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                filtre.add(song);
                 break;
                 
                 
                 case ("A"):
                     tp2=Tipus_Producte.A;
                 alb = new Album(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                filtre.add(alb);
                 break;
                 
                 case ("L"):
                     tp2=Tipus_Producte.L;
                 list = new Llista(rs.getInt("cat_id"),rs.getString("cat_titol"),estat,est,tp2);
                filtre.add(list);
                 break;
                 
             }
              
                
            }
           
            rs.close();
            
        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en intentar recuperar el contingut de la llista.\n" + ex.getMessage());
        }finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat el contingut de la llista.\n" + ex.getMessage());
                }
            }
        }
        return filtre;
   }
   
   
 
   
   
   
   
   
   
   
   
   
   
   
   
   
    public static void main(String[] args) throws GestorBDEmpresaException {
        
        
    }

    
    void fercommit() throws GestorBDEmpresaException{
        try {
            conn.commit();
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error al fer comit.\n" + ex.getMessage());
                
        }
    }
    
    void ferrollback() throws GestorBDEmpresaException{
        try {
            conn.commit();
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error al fer comit.\n" + ex.getMessage());
                
        }
    }
    
    
   
}


