/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalPrixJeuDAO;
import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.remote.IntRemotePrixJeuDAO;
import fr.imag.entities.Jeu;
import fr.imag.entities.PrixJeu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
@Stateless
public class PrixJeuDAO extends AbstractDAO implements IntLocalPrixJeuDAO, IntRemotePrixJeuDAO {

    @EJB
    IntLocalJeuDAO jeuDAO;

    @Override
    public Collection<PrixJeu> findAll() {
        ArrayList<PrixJeu> cpd = new ArrayList<>();
        try {
            TypedQuery<PrixJeu> query = em.createNamedQuery("GetAllPrixJeu", PrixJeu.class);
            return query.getResultList();

        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

}
