/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteCategorieDAO;
import fr.imag.entities.Categorie;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author cedric
 */

public class CategorieBean {
    private Categorie c;
    private Boolean checked;
    

    public CategorieBean (Categorie c){
       this.c = c;
       checked = false;
    }
    
    public boolean getValue(){
        return this.checked;
    }
    
    public void setValue(boolean value){
        this.checked = value;
    }
    
    public void checkCategorie(){
        this.checked = !this.checked;
    }
    
    public String getName(){
        return this.c.getNom();
    }
    
    public Long getId(){
        return this.c.getId();
    }
}
