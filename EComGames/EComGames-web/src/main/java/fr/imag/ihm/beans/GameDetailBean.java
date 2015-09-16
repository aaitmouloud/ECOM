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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cedric
 */
@ManagedBean(name = "gameDetailBean")
@ApplicationScoped
public class GameDetailBean implements Serializable {

    @EJB
    private JeuManagerRemote jeuMan;
    
    @EJB
    IntRemoteJeuDAO jeuDao;
   
    public Jeu getJeu() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return jeuDao.findById(request.getParameter("id"));
    }
    
    public Collection<Achat> getAchat(Jeu jeu){

        if (jeu == null){
            return Collections.EMPTY_LIST;
        }
        
        Collection<Achat> ca = jeuMan.getNbSell(jeu);
        if (ca == null){
            return Collections.EMPTY_LIST;
        }
        return ca;
    }
    
    
}
