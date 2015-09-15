/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author seb
 */
@ManagedBean(name = "panier")
@SessionScoped
public class PanierBean implements Serializable {

    @EJB
    private IntRemoteCleDAO cleDao;
    
    @EJB 
     private IntRemoteJeuDAO jeuDAO;
    private Map<String, ItemBean> gameC;
    
    @PostConstruct
    public void init() {
        gameC = new HashMap<>();
        Jeu j = jeuDAO.findAll().iterator().next();
        if (j != null){
             gameC.put(j.getId(), new ItemBean(j));
        }
    }
    
    public boolean isEmptyOrNot(){
        return gameC.isEmpty();
    }
    
    public void addGame(Jeu j,int nb) {
        addGame(j);
        ItemBean ib = gameC.get(j.getId());
        ib.setNombre(nb);
        updatePanier(ib);
    }

    public void addGame(Jeu j) {
        FacesMessage message;
        try {

            if (gameC.containsKey(j.getId())) {
                ItemBean i = gameC.get(j.getId());
                
                int nbCleDispo = cleDao.findAvailableCle(i.getId()).size();
                if (i.getNombre()+1 > nbCleDispo) {
                    i.setNombre(nbCleDispo);message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible d'ajouter un exemlpaire:", "le nombre de cle en stock de " + j.getNom() + " est insuffisant.");
                }else{
                    i.setNombre(i.getNombre() + 1);
                    gameC.remove(j.getId());
                    gameC.put(j.getId(), i);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panier Mis à jour:", "Un nouvel exemplaire de " + j.getNom() + " a été ajouté au panier.");

                }
               
            } else {
                gameC.put(j.getId(), new ItemBean(j));
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Ajouté:", "Le jeu " + j.getNom() + " a été ajouté au panier.");
            }
        } catch (Exception e) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'ajout", "Le jeu " + j.getNom() + " n'a pas pu être ajouté au panier.");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void removeGame(Jeu j) {
        FacesMessage message;
        if (gameC.containsKey(j.getId())) {
            gameC.remove(j.getId());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Supprimé:", "Le jeu " + j.getNom() + " a été supprimé du panier.");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jeux Introuvable:", "Le jeu " + j.getNom() + " n'a pas été trouvé dans le panier.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void updatePanier(ItemBean i) {
        FacesMessage message;
        if (gameC.containsKey(i.getId())) {
            int nbCleDispo = cleDao.findAvailableCle(i.getId()).size();
            if (i.getNombre() > nbCleDispo) {
                i.setNombre(nbCleDispo);
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la mise à jour:", "Le nombre de jeu disponible est insufisant");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panier Mis à jour:", "");
            }
            gameC.remove(i.getId());
            gameC.put(i.getId(), i);
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jeux Introuvable:", "Le jeu " + i.getNom() + " n'a pas été trouvé dans le panier.");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Collection<ItemBean> getGame() {
        return new ArrayList<>(gameC.values());
    }
    
    public double getPrix(){
        double prix = 0;
        Iterator<ItemBean> i = gameC.values().iterator();
        while (i.hasNext()){
            ItemBean ib = i.next();
            prix += ib.getPrix();
        }
        return prix;
    }

}
