/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalCategorieDAO;
import fr.imag.dao.remote.IntRemoteCategorieDAO;
import fr.imag.entities.Categorie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
@Stateless
public class CategorieDAO extends AbstractDAO implements IntLocalCategorieDAO, IntRemoteCategorieDAO {

    @Override
    public Collection<Categorie> findAll() {
        ArrayList<Categorie> ccd = new ArrayList<>();
        try{
             TypedQuery<Categorie> query = em.createNamedQuery("GetAllCategorie", Categorie.class);
             return query.getResultList();

        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }    
}
