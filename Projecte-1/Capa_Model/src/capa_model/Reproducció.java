/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_model;

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

    
    
    
    
    
    
    public Date getRep_mt() {
        return rep_mt;
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
