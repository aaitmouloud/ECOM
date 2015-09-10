/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.Business;

import fr.imag.Business.Local.JeuManagerLocal;
import fr.imag.Business.remote.JeuManagerRemote;
import fr.imag.dao.remote.IntRemoteAchatDAO;
import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.dao.remote.IntRemotePrixJeuDAO;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PrixJeuDTO;
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
    IntRemoteCleDAO cleDAO;
    
    @EJB
    IntRemoteAchatDAO achatDAO;
    
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
    public Collection<JeuDTO> orderBy(Collection<JeuDTO> cjd, Element e, Sens s){
        ArrayList<JeuDTO> lj = new ArrayList<>(cjd);
        cj.setElement(e);
        cj.setSens(s);
        Collections.sort(lj, cj);
        return lj;
    }

    @Override
    public float getAverageNote(JeuDTO j) {
        float result = 0;
        int nb = 0;
        Collection<CleDTO> cc = cleDAO.findAllFromJeu(j);
        Iterator<CleDTO> ic = cc.iterator();
        while (ic.hasNext()){
            CleDTO c = ic.next();
            AchatDTO a = achatDAO.findAllFromCle(c);
            if (a != null){
                result += a.getNote();
                nb++;
            }
        }
        return result/nb;
    }

    @Override
    public int getNbSell(JeuDTO j) {
        int nb = 0;
        Collection<CleDTO> cc = cleDAO.findAllFromJeu(j);
        Iterator<CleDTO> ic = cc.iterator();
        while (ic.hasNext()){
            CleDTO c = ic.next();
            AchatDTO a = achatDAO.findAllFromCle(c);
            if (a != null){
                nb++;
            }
        }
        return nb;
    }
    
    @Override
    public Double getPrix(JeuDTO j) {
        PrixJeuDTO p = prixJeuDAO.findPriceFromJeu(j);
        return p.getPrix();
    }
    
    
    
    
}
