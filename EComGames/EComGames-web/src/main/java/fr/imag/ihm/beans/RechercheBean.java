/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author min
 */
@ManagedBean(name = "rechercheBean")
@ViewScoped
public class RechercheBean implements Serializable {

    @EJB
    IntRemoteJeuDAO jeuDao;
    
    
    private String searchTerm;
    private Collection<String> nomsJeux;

    @PostConstruct
    public void setNomsJeux() {
       
        if (nomsJeux == null) {
            
            
            Collection<Jeu> jeux = jeuDao.findAll();

            if (jeux == null || jeux.isEmpty()) {
                nomsJeux = Collections.emptyList();
            }

            nomsJeux = new ArrayList<>();
            for (Jeu jeu : jeux) {
                nomsJeux.add(jeu.getNom());
            }
            
        }
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
  
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public List<String> completion(String query) {
        HashSet<String> toReturn = new HashSet<>();
        List<String> queryParts = Arrays.asList(query.split((" ")));

        for (String jeu : nomsJeux) {
            for (String term : queryParts) {
                if (jeu.toLowerCase().contains(term.toLowerCase())) {
                    toReturn.add(jeu);
                }
            }
        }

        return new ArrayList<>(toReturn);
    }
    
    

}
