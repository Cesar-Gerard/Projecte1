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
public class Llista extends Producte{
    private long durada;

    public Llista(long id, String titol, boolean actiu, Estil estil, String tp) {
        super(id, titol, actiu, estil, tp);
    }

    public Llista(long durada, long id, String titol, boolean actiu, Estil estil, String tp) {
        super(id, titol, actiu, estil, tp);
        this.durada = durada;
    }

    

    

    
    
    
    
    
    
    
    public long getDurada() {
        return durada;
    }

    public void setDurada(long durada) {
        this.durada = durada;
    }
    
    
    
    
}
