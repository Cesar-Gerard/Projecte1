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
    private long durada;
    private Artista art;

 
     
    

    public Canso(long id, String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        super(id, titol, actiu, estil, tp);
    }

    

    //Constructor per crear una nova canço

    public Canso(int anyCreacio, long durada, Artista art, long id) {
        super(id);
        this.anyCreacio = anyCreacio;
        this.durada = durada;
        this.art = art;
    }

    

    
    
    
    
    

    
    
    
    public int getAnyCreacio() {
        return anyCreacio;
    }

    public void setAnyCreacio(int anyCreacio) {
        this.anyCreacio = anyCreacio;
    }

    public long getDurada() {
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
