/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteAchatDAO;
import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author seb
 */
@ManagedBean(name = "panier")
@SessionScoped
public class PanierBean implements Serializable {

    final private static NumberFormat DOUBLE_FORMAT = new DecimalFormat("#0.00€");

    @EJB
    private IntRemoteCleDAO cleDao;
    @EJB
    private IntRemoteAchatDAO achatDao;

    @EJB
    private IntRemoteJeuDAO jeuDAO;
    private Map<String, PanierItem> gameC;
    
    @PostConstruct
    public void init() {
        gameC = new HashMap<>();
    }

    public boolean isEmptyOrNot() {
        return gameC.isEmpty();
    }

    public void addGame(Jeu j, int nb) {
        addGame(j);
        PanierItem ib = gameC.get(j.getId());
        ib.setNombre(nb);
        updatePanier(ib);
    }

    public void addGame(Jeu j) {
        try {
            PanierItem i;
            if (gameC.containsKey(j.getId())) {
                i = gameC.get(j.getId());
            } else {
                i = new PanierItem(j);
            }

            int nbCleDispo = cleDao.findAvailableCle(i.getId()).size();
            if (i.getNombre() + 1 > nbCleDispo) {
                i.setNombre(nbCleDispo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible d'ajouter un exemlpaire:", "le nombre de cle en stock de " + j.getNom() + " est insuffisant."));
            } else {
                i.setNombre(i.getNombre() + 1);
                gameC.remove(j.getId());
                gameC.put(j.getId(), i);
                //message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panier Mis à jour:", "Un nouvel exemplaire de " + j.getNom() + " a été ajouté au panier.");

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'ajout", "Le jeu " + j.getNom() + " n'a pas pu être ajouté au panier."));
        }

    }

    public void removeGame(PanierItem item) {
        if (gameC.containsKey(item.getId())) {
            gameC.remove(item.getId());
            //message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Supprimé:", "Le jeu " + item.getNom() + " a été supprimé du panier.");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jeux Introuvable:", "Le jeu " + item.getNom() + " n'a pas été trouvé dans le panier."));
        }

    }

    public void updatePanier(PanierItem i) {
        if (gameC.containsKey(i.getId())) {
            int nbCleDispo = cleDao.findAvailableCle(i.getId()).size();
            if (i.getNombre() > nbCleDispo) {
                i.setNombre(nbCleDispo);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la mise à jour:", "Le nombre de jeu disponible est insufisant"));
            } else {
                //message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panier Mis à jour:", "");
            }
            gameC.remove(i.getId());
            gameC.put(i.getId(), i);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Jeux Introuvable:", "Le jeu " + i.getNom() + " n'a pas été trouvé dans le panier."));
        }

    }

    public Collection<PanierItem> getGame() {
        return new ArrayList<>(gameC.values());
    }

    public String getPrix() {
        return DOUBLE_FORMAT.format(getPrixDouble());
    }

    public double getPrixDouble() {
        double prix = 0;
        Iterator<PanierItem> i = gameC.values().iterator();
        while (i.hasNext()) {
            PanierItem ib = i.next();
            prix += ib.getPrixDouble();
        }
        return prix;
    }

    public void validerAchats(Long userId) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext fContext = FacesContext.getCurrentInstance();
        boolean loggedIn;
        FacesMessage message = null;
        Logger.getLogger(PanierBean.class).debug("Valider achats pour " + userId);
        if (userId == null) {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connectez-vous!", "Vous devez vous connecter pour valider vos achats.");
        } else {
            Iterator<String> itemIte = gameC.keySet().iterator();
            PanierItem item;
            loggedIn = true;
            List<String> notValidatedItems = new ArrayList<>();
            List<String> validatedItems = new ArrayList<>();

            while (itemIte.hasNext()) {
                item = gameC.get(itemIte.next());
                if (achatDao.addAchat(userId, item.getId(), item.getNombre())) {
                    validatedItems.add(item.getNom());
                    itemIte.remove();
                } else {
                    notValidatedItems.add(item.getNom());
                }
                if (!notValidatedItems.isEmpty()) {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Achat non complet.", "Les jeux suivants ont été achetés: <br/>" + validatedItems.toString() + "<br/><br/>Les jeux suivants n'ont pas pu être achetés. <br/>" + notValidatedItems.toString());
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Achat terminé.", "Les jeux suivants ont été achetés: <br/>" + validatedItems.toString());
                }
            }

        }
        fContext.addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }

}
