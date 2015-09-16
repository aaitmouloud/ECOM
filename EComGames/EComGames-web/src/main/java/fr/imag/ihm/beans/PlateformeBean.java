/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.entities.Plateforme;

/**
 *
 * @author seb
 */
public class PlateformeBean {
    private Plateforme p;
    private Boolean checked;
    

    public PlateformeBean (Plateforme p){
       this.p = p;
       checked = false;
    }
    
    public boolean getValue(){
        return this.checked;
    }
    
    public void setValue(boolean value){
        this.checked = value;
    }
    
    public String getName(){
        return this.p.getNom();
    }
    
    public Long getId(){
        return this.p.getId();
    }
}
