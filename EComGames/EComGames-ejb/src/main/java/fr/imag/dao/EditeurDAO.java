/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalEditeurDAO;
import fr.imag.dao.remote.IntRemoteEditeurDAO;
import fr.imag.entities.Editeur;
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
public class EditeurDAO extends AbstractDAO implements IntLocalEditeurDAO, IntRemoteEditeurDAO {

    @Override
    public Collection<Editeur> findAll() {
        ArrayList<Editeur> ced = new ArrayList<>();
        try {
            TypedQuery<Editeur> query = em.createNamedQuery("GetAllEditeur", Editeur.class);
            return query.getResultList();

        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

}
