/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.entities.Editeur;

/**
 *
 * @author cedric
 */
public class EditeurBean {
    
    private Editeur e;
    private Boolean checked;
    

    public EditeurBean (Editeur e){
       this.e = e;
       checked = false;
    }
    
    public boolean getValue(){
        return this.checked;
    }
    
    public void setValue(boolean value){
        this.checked = value;
    }
    
    public String getName(){
        return this.e.getNom();
    }
    
    public Long getId(){
        return this.e.getId();
    } 
}
