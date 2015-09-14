/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author aaitmouloud
 */
@ManagedBean(name = "gameBean")
@ApplicationScoped
public class GameBean implements Serializable {
    
    @EJB
    IntRemoteJeuDAO jeuDao;

    public Collection<Jeu> getAllJeux() {
        return jeuDao.findAll();
    }   
}
