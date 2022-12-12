/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.model;

/**
 *
 * @author isard
 */
public class Album extends Producte{
    private int anyCreacio;
    private long durada;
    private Artista art;

    public Album(long id, String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        super(id, titol, actiu, estil, tp);
    }

    public Album(int anyCreacio, long durada, long id, String titol, boolean actiu, Estil estil,Tipus_Producte tp) {
        super(id, titol, actiu, estil, tp);
        this.anyCreacio = anyCreacio;
        this.durada = durada;
    }

    public Album(int anyCreacio, long durada, Artista art, long id) {
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
