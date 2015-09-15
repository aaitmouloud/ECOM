/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteEditeurDAO;
import fr.imag.entities.Editeur;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author cedric
 */
@ManagedBean(name = "editeurBean")
@ApplicationScoped
public class editeurBean implements Serializable {
    
    @EJB
    IntRemoteEditeurDAO editeurDao;

    public Collection<Editeur> getAllEditeur() {
        return editeurDao.findAll();
    }   
}
