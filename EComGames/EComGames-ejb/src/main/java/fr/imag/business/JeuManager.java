/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.business;

import fr.imag.business.local.JeuManagerLocal;
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
    
    @EJB
    IntRemoteAchatDAO achatDAO;
    
    @EJB
    IntRemoteCleDAO cleDAO;
    
    private CompareJeu cj;
    private Element e;
    private Sens s;
    
    public JeuManager(){
        cj = new CompareJeu(this);
    }
    
    
    
    @Override
    public Collection<Jeu> orderBy(Collection<Jeu> cjd){
        if(cjd == null){
            return Collections.EMPTY_LIST;
        }
        ArrayList<Jeu> lj = new ArrayList<>(cjd);
     
        cj.setElement(e);
        cj.setSens(s);
        Collections.sort(lj, cj);
        if (lj.isEmpty()){
            return cjd;
        }
        return lj;
    }

    @Override
    public float getAverageNote(Jeu j) {
        float result = 0;
        int nb = 0;
        if (j == null){
            return 0;
        }
        Collection<Cle> cc = cleDAO.findClebyJeu(j.getId());
        if (cc == null){
            return -1;
        }
        Iterator<Cle> ic = cc.iterator();
       while (ic.hasNext()){
            Cle c = ic.next();
            Achat a = achatDAO.findAchatByCle(c.getCle());
            if (a != null){
                result += a.getNote();
                nb++;
            }
        }
        if (nb == 0){
            return -1;
       }else{
            return result/nb;
        }
    }

    @Override
    public Collection<Achat> getNbSell(Jeu j) {
        if( j == null){
            return Collections.EMPTY_LIST;
        }
        ArrayList<Achat> aa = new ArrayList<>();
        Collection<Cle> cc = cleDAO.findClebyJeu(j.getId());
        
        if (cc == null){
            return Collections.EMPTY_LIST;
        }
        Iterator<Cle> ic = cc.iterator();
        while (ic.hasNext()){
            Cle c = ic.next();
            Achat a = achatDAO.findAchatByCle(c.getCle());
            if (a != null){
                aa.add(a);
            }
        }
        return aa;
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

    @Override
    public void setElement(Element e) {
        this.e = e;
    }

    @Override
    public void setSens(Sens s) {
        this.s = s;
    }


    
    
    
    
}
