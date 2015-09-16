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
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

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
    
    public boolean isChecked(){
        return this.checked;
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
