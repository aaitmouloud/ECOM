/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalUtilisateurDAO;
import fr.imag.dao.remote.IntRemoteUtilisateurDAO;
import fr.imag.entities.Utilisateur;
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
public class UtilisateurDAO extends AbstractDAO implements IntLocalUtilisateurDAO, IntRemoteUtilisateurDAO {

    
    @Override
    public Collection<Utilisateur> findAll() {
        ArrayList<Utilisateur> cud = new ArrayList<>();
        try {
            TypedQuery<Utilisateur> query = em.createNamedQuery("GetAllUser", Utilisateur.class);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }
}
