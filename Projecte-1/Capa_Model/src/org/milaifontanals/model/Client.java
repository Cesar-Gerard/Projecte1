/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.model;

import java.util.Date;

/**
 *
 * @author gerar
 */
public class Client {
    private long id;
    private String email;
    private String nom;
    private String cognoms;
    private Date dataNaix;
    private String cp;
    private String dom1;
    private String poblacio;

    public Client(long id, String email, String nom, String cognoms, Date dataNaix, String cp, String dom1, String poblacio) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaix = dataNaix;
        this.cp = cp;
        this.dom1 = dom1;
        this.poblacio = poblacio;
    }

    public Client() {
       
    }

     public Client(long id, String nom, String cognoms) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
      
    }


    public Client(long id) {
        this.id = id;
    }

    public Client(String nom) {//Proba seleccio client taula reproduccio
        this.nom = nom;
    }
    
    

    public Client(long id, String nom) {//Constructor Proba Afegir reproduccio
        this.id = id;
        this.nom = nom;
    }

    
    
     
     
     
     

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(Date dataNaix) {
        this.dataNaix = dataNaix;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDom1() {
        return dom1;
    }

    public void setDom1(String dom1) {
        this.dom1 = dom1;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }
    
}
