/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.model;

import java.util.Date;

/**
 *
 * @author isard
 */
public class ArtistaGrupal extends Artista {
    Date datacreacio;

    public ArtistaGrupal(Date datacreacio, long id, String nom, TipusArtista tp) {
        super(id, nom, tp);
        this.datacreacio = datacreacio;
    }
    
    public ArtistaGrupal(long id, String nom, TipusArtista tp) {
        super(id, nom, tp);
        
    }
    

    public Date getDatacreacio() {
        return datacreacio;
    }

    public void setDatacreacio(Date datacreacio) {
        this.datacreacio = datacreacio;
    }
    
    
    
    
    
    
}
