/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.business.remote.JeuManagerRemote;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Achat;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cedric
 */
@ManagedBean(name = "gameDetailBean")
@ViewScoped
public class GameDetailBean implements Serializable {

    final private static NumberFormat DOUBLE_FORMAT = new DecimalFormat("#0.00€");
    
    private Jeu j;
    @EJB
    private JeuManagerRemote jeuMan;
    
    @EJB
    IntRemoteJeuDAO jeuDao;
   
    @PostConstruct
    public void Init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        j = jeuDao.findById(request.getParameter("id"));
    }
    
    public Jeu getJeu(){
        return j;
    }
    
    public Collection<Achat> getAchat(){

        if (j == null){
            return Collections.EMPTY_LIST;
        }
        
        Collection<Achat> ca = jeuMan.getNbSell(j);
        if (ca == null){
            return Collections.EMPTY_LIST;
        }
        return ca;
    }
    
    public String getPrixUnit() {
        return DOUBLE_FORMAT.format(getPrixUnitDouble(j));
    }

    public double getPrixUnitDouble(Jeu j) {
        if (j.getCurrentPrix() != null) {
            return j.getCurrentPrix().getPrix();
        }
        return -1;
    }
    
    
}
