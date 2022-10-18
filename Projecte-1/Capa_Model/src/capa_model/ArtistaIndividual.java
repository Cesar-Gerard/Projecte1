/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author gerar
 */
public class ArtistaIndividual extends Artista {
    private Date dataNaixement;
    private String nom;
    private String nacionalitat;

    public ArtistaIndividual(Date dataNaixement, String nom, String nacionalitat) {
        super();
        this.dataNaixement = dataNaixement;
        this.nom = nom;
        this.nacionalitat = nacionalitat;
    }
   
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
