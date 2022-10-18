/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capa_model;

/**
 *
 * @author gerar
 */
public class Pais {
    private String ISN;
    private String nom;

    public Pais(String ISN, String nom) {
        this.ISN = ISN;
        this.nom = nom;
    }
    
    

    public String getISN() {
        return ISN;
    }

    public void setISN(String ISN) {
        this.ISN = ISN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
