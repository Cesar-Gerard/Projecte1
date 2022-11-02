/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.model;

/**
 *
 * @author gerar
 */
public abstract class Producte {
    private long id;
    private String titol;
    private boolean actiu;

    public Producte() {
    }
    
    


    public Producte(long id, String titol, boolean actiu) {
        this.id = id;
        this.titol = titol;
        this.actiu = actiu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }

    @Override
    public String toString() {
        return "Producte{" + "id=" + id + ", titol=" + titol + ", actiu=" + actiu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    public int getDuracio(){
        int durada=0;
        
        return durada;
    }

    
}
