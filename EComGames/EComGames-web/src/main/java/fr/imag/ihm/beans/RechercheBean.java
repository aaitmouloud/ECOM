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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author min
 */
@ManagedBean(name = "rechercheBean")
@ViewScoped
public class RechercheBean implements Serializable{

    @EJB
    IntRemoteJeuDAO jeuDao;

    private String searchTerm;
    private Collection<String> nomsJeux;

    private void setNomsJeux() {
        if (nomsJeux == null) {
            Collection<Jeu> jeux = jeuDao.findAll();

            if (jeux == null || jeux.isEmpty()) {
                nomsJeux = Collections.emptyList();
            }

            Collection<String> noms = new ArrayList<>();
            for (Jeu jeu : jeux) {
                noms.add(jeu.getNom());
            }

            nomsJeux = noms;
        }
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public List<String> getCompletion() {
        setNomsJeux();
        return new ArrayList<>(nomsJeux);
    }

}
