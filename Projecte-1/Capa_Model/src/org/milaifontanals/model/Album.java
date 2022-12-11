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

    public Album(long id, String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        super(id, titol, actiu, estil, tp);
    }

    public Album(int anyCreacio, long durada, long id, String titol, boolean actiu, Estil estil,Tipus_Producte tp) {
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

    public long getDurada() {
        return durada;
    }

    public void setDurada(long durada) {
        this.durada = durada;
    }
    
    
    
}
