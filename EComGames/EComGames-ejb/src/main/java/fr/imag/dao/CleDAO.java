/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalCleDAO;
import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.entities.Cle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author seb
 */
@Stateless
public class CleDAO extends AbstractDAO implements IntLocalCleDAO, IntRemoteCleDAO {

    @Override
    public Collection<Cle> findAll() {
        ArrayList<Cle> ccd = new ArrayList<>();
        try {
            TypedQuery<Cle> query = em.createNamedQuery("GetAllCle", Cle.class);
            return query.getResultList();

        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Collection<Cle> findAvailableCle(String jeuId) {

        TypedQuery<Cle> query = null;
        ArrayList<Cle> ccd = new ArrayList<>();
        try {
            query = em.createNamedQuery("GetAvailableCleByJeuId", Cle.class);
            query.setParameter("id", jeuId);

            return query.getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Cle> findClebyJeu(String jeuId) {
        TypedQuery<Cle> query = null;
        ArrayList<Cle> ccd = new ArrayList<>();
        try {
            query = em.createNamedQuery("GetCleByJeuId", Cle.class);
            query.setParameter("id", jeuId);
            //throw new RuntimeException(query.toString());
            return query.getResultList();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
