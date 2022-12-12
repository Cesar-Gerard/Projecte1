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
    private Estil estil;
    private Tipus_Producte tp;


    
    //Constructors necessaris per les diverses configuracions de classes que necessiten la classe Producte
   
    

    public Producte(long id, String titol) {
        this.id = id;
        this.titol = titol;
    }

    public Producte(String titol) {
        this.titol = titol;
    }

    public Producte(long id) {
            this.id = id;
    }

    public Producte(long id, String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        this.id = id;
        this.titol = titol;
        this.actiu = actiu;
        this.estil = estil;
        this.tp = tp;
    }

    public Producte(String titol, boolean actiu, Estil estil, Tipus_Producte tp) {
        this.titol = titol;
        this.actiu = actiu;
        this.estil = estil;
        this.tp = tp;
    }

    public Producte(long id, String titol, Estil estil) {
        this.id = id;
        this.titol = titol;
        this.estil = estil;
    }
    
    
    
    
    
     public String getIdString(){
        String resultat = Long.toString(id);
        
        return resultat;
    }

    public Estil getEstil() {
        return estil;
    }

    public void setEstil(Estil estil) {
        this.estil = estil;
    }

    public Tipus_Producte getTp() {
        return tp;
    }

    public void setTp(Tipus_Producte tp) {
        this.tp = tp;
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

    public String isActiu() {
        if(actiu == true){
            return "Actiu";
        }else{
            return "Inactiu";
        }
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
    

    
}
