/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Jeu;
import java.io.Serializable;
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
    IntRemoteJeuDAO jeuDao;
   
    public Jeu getJeu() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return jeuDao.findById(request.getParameter("id"));
    }
}
