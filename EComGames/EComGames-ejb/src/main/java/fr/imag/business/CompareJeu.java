/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.business;

import fr.imag.business.remote.JeuManagerRemote;
import fr.imag.entities.Jeu;
import java.util.Comparator;
/**
 *
 * @author seb
 */


public class CompareJeu implements Comparator<Jeu> {
    JeuManager jm;
    
    private JeuManager.Element e;
    private JeuManager.Sens s;
    
    public CompareJeu(JeuManager jm){
        this.setManager(jm);
    }
    
    public void setElement(JeuManager.Element e){
        this.e = e;
    }
    
    public void setSens(JeuManager.Sens s){
        this.s = s;
    }
    
    public void setManager(JeuManager jm){
        this.jm = jm;
    }
    
    
    
    
    @Override
    public int compare(Jeu t1, Jeu t2) {
        int nb;
        switch(e){
            case Note :
                nb = ((Float) jm.getAverageNote(t1)).compareTo((Float) jm.getAverageNote(t2)); break;
            case BestSell: 
                nb = ((Integer) jm.getNbSell(t1)).compareTo((Integer) jm.getNbSell(t2)); break;
            case Editeur:
                nb = t1.getEditeur().getNom().compareTo(t2.getEditeur().getNom()); break;
            case Annee:
                nb = ((Integer)t1.getAnnee()).compareTo((Integer)t2.getAnnee());break;
            case Prix:
                nb = jm.getPrix(t1).compareTo(jm.getPrix(t2)); break;
            case Defaut: 
            default: nb =  t1.getNom().compareTo(t2.getNom());
        }
        switch(s){
            case Decroissant: nb = -nb;break;
        }
        return nb;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
