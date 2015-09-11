/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.business;

import fr.imag.business.Local.JeuManagerLocal;
import fr.imag.business.remote.JeuManagerRemote;
import fr.imag.dao.remote.IntRemoteAchatDAO;
import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.dao.remote.IntRemotePrixJeuDAO;
import fr.imag.entities.Achat;
import fr.imag.entities.Cle;
import fr.imag.entities.Jeu;
import fr.imag.entities.PrixJeu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author seb
 */
@Stateless
public class JeuManager implements JeuManagerLocal, JeuManagerRemote {
    
    @EJB
    IntRemotePrixJeuDAO prixJeuDAO;
    
    private CompareJeu cj;
    
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
    
    public JeuManager(){
        cj = new CompareJeu();
    }
    
    @Override
    public Collection<Jeu> orderBy(Collection<Jeu> cjd, Element e, Sens s){
        ArrayList<Jeu> lj = new ArrayList<>(cjd);
        cj.setElement(e);
        cj.setSens(s);
        Collections.sort(lj, cj);
        return lj;
    }

    @Override
    public float getAverageNote(Jeu j) {
        float result = 0;
        int nb = 0;
        Collection<Cle> cc = j.getCles();
        Iterator<Cle> ic = cc.iterator();
        while (ic.hasNext()){
            Cle c = ic.next();
            Achat a = c.getAchat();
            if (a != null){
                result += a.getNote();
                nb++;
            }
        }
        return result/nb;
    }

    @Override
    public int getNbSell(Jeu j) {
        int nb = 0;
        Collection<Cle> cc = j.getCles();
        Iterator<Cle> ic = cc.iterator();
        while (ic.hasNext()){
            Cle c = ic.next();
            Achat a = c.getAchat();
            if (a != null){
                nb++;
            }
        }
        return nb;
    }
    
    @Override
    public Double getPrix(Jeu j) {
        Collection<PrixJeu> cp = j.getPrix();
        Iterator<PrixJeu> i = cp.iterator();
        while (i.hasNext()){
            PrixJeu p = i.next();
            if (p.getDateFin() == null){
                return  p.getPrix();
            }
        }
        return null;
    }
    
    
    
    
}
