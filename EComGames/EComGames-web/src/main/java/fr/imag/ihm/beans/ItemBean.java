/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.entities.Jeu;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seb
 */
@ManagedBean(name = "itemBean")
@SessionScoped
public class ItemBean implements Serializable{
    final private Jeu j;
    private int nb;
    
    public ItemBean(Jeu j){
        this.j = j;
        this.nb = 1;
    }
    
    public String getId(){
        return j.getId();
    }

    
   
    public String getNom(){
        return j.getNom();
    }
    
    public String getEditeur(){
        if (j.getEditeur() != null){
             return j.getEditeur().getNom();
        }else{
            return "Editeur inconnu";
        }
       
        
    }
    
    public boolean isPrixNull(){
        return getPrixUnit() == (double)0;
    }
    
    public double getPrixUnit(){
        if (j.getCurrentPrix() != null){
            return j.getCurrentPrix().getPrix();
        }
        return -1;
    }
    
    public int getNombre(){
        return nb;
    }
    
    public void setNombre(int nb) {
        if (nb >= 1 ){
            this.nb = nb;
        }
    }
    
    public double getPrix(){
        return (double)nb * getPrixUnit();
    }
    
    public void handleEvent() {
    }
    
}
