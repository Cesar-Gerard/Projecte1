/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gerar
 */
public class Reproducció{

    private Date rep_mt; 
    private Client idClient;
    private Producte idProducte; 

    public Reproducció(Date rep_mt, Client idClient, Producte idProducte) {
        this.rep_mt = rep_mt;
        this.idClient = idClient;
        this.idProducte = idProducte;
    }

    public Reproducció(Client idClient, Producte idProducte) {
        this.idClient = idClient;
        this.idProducte = idProducte;
    }

    public Reproducció(Date rep_mt, Client idClient) {//Proba
        this.rep_mt = rep_mt;
        this.idClient = idClient;
    }

    
    
    
    
    
    
    
    
    
    public String getRep_mt() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String resultat=null;
        
        resultat=df.format(rep_mt);
        
        return resultat;
    }

    public void setRep_mt(Date rep_mt) {
        this.rep_mt = rep_mt;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Producte getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(Producte idProducte) {
        this.idProducte = idProducte;
    }
    
    
    
    
    
            
    
    
    
}
