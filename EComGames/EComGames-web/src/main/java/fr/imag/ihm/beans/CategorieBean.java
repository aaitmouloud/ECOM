/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteCategorieDAO;
import fr.imag.entities.Categorie;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author cedric
 */
@ManagedBean(name = "categorieBean")
@ApplicationScoped
public class CategorieBean implements Serializable {
    
    @EJB
    IntRemoteCategorieDAO categorieDao;

    public Collection<Categorie> getAllCategorie() {
        return categorieDao.findAll();
    }   
}
