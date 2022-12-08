/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerar
 */
public abstract class Artista {
    private long id;
    private String nom;
    private TipusArtista tp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TipusArtista getTp() {
        return tp;
    }

    public void setTp(TipusArtista tp) {
        this.tp = tp;
    }

    public Artista(long id, String nom, TipusArtista tp) {
        this.id = id;
        this.nom = nom;
        this.tp = tp;
    }

   

    
     public String getIdString(){
        String resultat = Long.toString(id);
        
        return resultat;
    }
    
    
    
    
    public List<Canso> getCansoInterpretades(){
        
        List <Canso> resultat = new ArrayList<Canso>();
        
        return resultat;
    }
    
}
