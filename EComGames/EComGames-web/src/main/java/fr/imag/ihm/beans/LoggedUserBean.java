/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteUtilisateurDAO;
import fr.imag.entities.Achat;
import fr.imag.entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author aaitmouloud
 */
@ManagedBean(name = "loggedUserBean")
@SessionScoped
public class LoggedUserBean implements Serializable {

    private Utilisateur utilisateur = null;
    private Collection<Achat> a;

    @EJB
    private IntRemoteUtilisateurDAO userDao;

    public void login(String login, String hashMdp) {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;

        Utilisateur tmp;
        if (login != null && hashMdp != null
                && (tmp = userDao.findFromLoginEtMdp(login, hashMdp)) != null) {
            utilisateur = tmp;
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue", "Bienvenue "+utilisateur.getNom());
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur d'authentification", "Identifiants invalides.");

        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);

    }

    public String getUsername() {
        if (utilisateur == null) {
            return null;
        }
        return utilisateur.getNom();
    }

    public Long getId() {
        if (utilisateur == null) {
            return null;
        }
        return utilisateur.getId();
    }

    public String getEmail() {
        if (utilisateur == null) {
            return null;
        }
        return utilisateur.getEmail();
    }

    public Calendar getDateN() {
        if (utilisateur == null) {
            return null;
        }
        return utilisateur.getDateNaissance();
    }

    public Collection<Achat> getAchats() {
        if (utilisateur == null) {
            return null;
        }
        refreshUser();
        a = utilisateur.getAchats();
        return a;
    }

    public boolean isHasAchats() {
        if (utilisateur == null) {
            return false;
        }
        
        getAchats();
        if (a != null && !a.isEmpty()){
            return true;
        }
        return false;
    }
    
    public void UpdateUser(){
        userDao.refresh(utilisateur);
    }
    
    public void refreshUser(){
        this.utilisateur = userDao.updateUser(utilisateur);
    }
  
    

    public boolean isUnlogged() {

        return utilisateur == null;
    }

    public void logout() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (context != null) {
           
            context.invalidateSession();
            utilisateur = null;
            context.redirect(((HttpServletRequest) context.getRequest()).getRequestURI());

        }
    }

}
