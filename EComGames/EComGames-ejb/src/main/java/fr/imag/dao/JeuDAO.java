/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Categorie;
import fr.imag.entities.Editeur;
import fr.imag.entities.Jeu;
import fr.imag.entities.Plateforme;
import fr.imag.entities.PrixJeu;
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
public class JeuDAO extends AbstractDAO implements IntLocalJeuDAO, IntRemoteJeuDAO {

    @Override
    public Collection<Jeu> findAll() {
        try {
            TypedQuery<Jeu> query = em.createNamedQuery("GetAllJeu", Jeu.class);
            return query.getResultList();
            
        } catch (Exception e) {
            throw (new RuntimeException(e));
        }
    }

    @Override
    public Collection<Jeu> Search(Collection<Categorie> c, Collection<Editeur> e, Collection<Plateforme> p, PrixJeu min, PrixJeu max) {
        ArrayList<Jeu> cjd = new ArrayList<>();
        try {
            TypedQuery<Jeu> query = em.createNamedQuery("SearchJeu", Jeu.class);
            query.setParameter("prixMin", min.getPrix());
            query.setParameter("prixMax", max.getPrix());
            if (c.isEmpty()) {
                query.setParameter("cid", null);
            } else {
                query.setParameter("cid", c);
            }

            if (e.isEmpty()) {
                query.setParameter("ced", null);
            } else {
                query.setParameter("ced", e);
            }

            if (p.isEmpty()) {
                query.setParameter("plid", null);
            } else {
                query.setParameter("plid", p);
            }

            return query.getResultList();

        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }

}
