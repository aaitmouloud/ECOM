/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
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
public class Panier implements Serializable{
    @EJB
    private IntRemoteCleDAO CleDao;
    private Map<Jeu,Item> GameC;
    
    public void addGame(Jeu j){
        FacesMessage message;
        try{
      
        if (GameC.containsKey(j)){
            Item i = GameC.get(j);
            i.setNombre(i.getNombre()+1);
            GameC.remove(j);
            GameC.put(j, i);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panier Mis à jour:","Un nouvel exemplaire de "+j.getNom()+" a été ajouté au panier.");
        }else{
            GameC.put(j,new Item(j));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Ajouté:","Le jeu "+j.getNom()+" a été ajouté au panier.");
        }
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur lors de l'ajout","Le jeu "+j.getNom()+" n'a pas pu être ajouté au panier.");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void remove(Jeu j){
        FacesMessage message;
        if (GameC.containsKey(j)){
            GameC.remove(j);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Supprimé:","Le jeu "+j.getNom()+" a été supprimé du panier.");
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Introuvable:","Le jeu "+j.getNom()+" n'a pas été trouvé dans le panier.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void Update(Item i){
        FacesMessage message;
        if (GameC.containsKey(i.getJeu())){
            int nbCleDispo = CleDao.findAvailableCle(i.getJeu()).size();
            if (i.getNombre() > nbCleDispo){
                 i.setNombre(nbCleDispo);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur lors de la mise à jour:","Le nombre de jeu disponible est insufisant");
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panier Mis à jour:","");
            }
            GameC.remove(i.getJeu());
            GameC.put(i.getJeu(), i);
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Jeux Introuvable:","Le jeu "+i.getNom()+" n'a pas été trouvé dans le panier.");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public Collection<Item> getGame(){
        return GameC.values();
    }
    
    
    
}
