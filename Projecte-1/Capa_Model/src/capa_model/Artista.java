/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerar
 */
abstract class Artista {
    private long id;
    private String nom;

    public Artista(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Artista() {
    }

    
    
    
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
    
    
    public List<Canso> getCansoInterpretades(){
        
        List <Canso> resultat = new ArrayList<Canso>();
        
        return resultat;
    }
    
}
