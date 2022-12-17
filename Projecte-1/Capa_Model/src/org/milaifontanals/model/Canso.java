/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.model;

/**
 *
 * @author gerar
 */
public class Canso extends Producte{
    private int anyCreacio;
    private double durada;
    private Artista art;

 
     
    

    public Canso(long id, String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        super(id, titol, actiu, estil, tp);
    }

    

    public Canso(int anyCreacio, double durada, Artista art, long id) {
        super(id);
        this.anyCreacio = anyCreacio;
        this.durada = durada;
        this.art = art;
    }

    public Canso(int anyCreacio, double durada, long id, String titol) {
        super(id, titol);
        this.anyCreacio = anyCreacio;
        this.durada = durada;
    }

    public Canso(int anyCreacio, double durada,long id, String titol, Estil estil) {
        super(id, titol, estil);
        this.anyCreacio = anyCreacio;
        this.durada = durada;
       
    }

    public Canso(int anyCreacio, double durada, long id, String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        super(id, titol, actiu, estil, tp);
        this.anyCreacio = anyCreacio;
        this.durada = durada;
    }
    
    


    
    
    
    

    
    
    
    public int getAnyCreacio() {
        return anyCreacio;
    }

    public void setAnyCreacio(int anyCreacio) {
        this.anyCreacio = anyCreacio;
    }

    public double getDurada() {
        return durada;
    }

    public void setDurada(long durada) {
        this.durada = durada;
    }

    public Artista getArt() {
        return art;
    }

    public void setArt(Artista art) {
        this.art = art;
    }
    
    
    
    
}
