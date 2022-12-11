/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author gerar
 */
public class ArtistaIndividual extends Artista {
    private Date dataNaixement;
    private String nacionalitat;

    public ArtistaIndividual(long id, String nom, TipusArtista tp) {
        super(id, nom, tp);
    }
    
    
    public ArtistaIndividual(Date dataNaixement, String nacionalitat, long id, String nom, TipusArtista tp) {
        super(id, nom, tp);
        this.dataNaixement = dataNaixement;
        this.nacionalitat = nacionalitat;
    }

    public ArtistaIndividual(String nom) {
        super(nom);
    }

    public ArtistaIndividual(long id) {
        super(id);
    }

    
    

    
    
    

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }


    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }


    
    
    
    
}
