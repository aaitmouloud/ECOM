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
    
    public boolean isChecked(){
        return this.checked;
    }
    
    public void checkPlateforme(){
        this.checked = !this.checked;
    }
    
    public String getName(){
        return this.p.getNom();
    }
    
    public Long getId(){
        return this.p.getId();
    }
}
