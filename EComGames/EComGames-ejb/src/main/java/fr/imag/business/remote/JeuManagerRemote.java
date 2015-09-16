/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.business.remote;

import fr.imag.entities.Achat;
import fr.imag.entities.Jeu;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface JeuManagerRemote {
     public static enum Element{
        Note,
        Defaut,
        BestSell,
        Editeur,
        Annee,
        Prix,
    };
    
    public static enum Sens{
        Croissant,
        Decroissant,
    }; 
    Collection<Jeu> orderBy(Collection<Jeu> cjd);
    public float getAverageNote(Jeu j);
    public Collection<Achat> getNbSell(Jeu j);
    public Double getPrix(Jeu j);
    public void setElement(Element e);
    public void setSens(Sens s);
}
