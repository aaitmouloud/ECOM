/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.entities.Jeu;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author seb
 */
@ManagedBean(name = "item")
@RequestScoped
public class Item implements Serializable{
    private Jeu j;
    private int nb;
    
    public Item(Jeu j){
        this.j = j;
        this.nb = 1;
    }
    
    public Jeu getJeu(){
        return j;
    }
    
   
    public String getNom(){
        return j.getNom();
    }
    
    public String getEditeur(){
        return j.getEditeur().getNom();
        
    }
    
    public double getPrixUnit(){
        return j.getCurrentPrix().getPrix();
        
    }
    
    public int getNombre(){
        return nb;
        
    }
    
    public void setNombre(int nb){
        this.nb = nb;
    }
    
    public double getPrix(){
        return (double)nb * getPrixUnit();
    }
    
}
