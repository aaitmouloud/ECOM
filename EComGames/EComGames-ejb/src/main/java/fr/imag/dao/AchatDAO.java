/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalAchatDAO;
import fr.imag.dao.remote.IntRemoteAchatDAO;
import fr.imag.entities.Achat;
import fr.imag.entities.Cle;
import fr.imag.entities.Jeu;
import fr.imag.entities.Utilisateur;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
@Stateless
public class AchatDAO extends AbstractDAO implements IntLocalAchatDAO, IntRemoteAchatDAO {

    @Override
    public Collection<Achat> findAll() {
        ArrayList<Achat> cad = new ArrayList<>();
        try {
            TypedQuery<Achat> query = em.createNamedQuery("GetAllAchat", Achat.class);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public boolean addAchat(Long userId, String jeuId, int count) {
        if (count < 1) {
            return false;
        }

        Jeu j = em.find(Jeu.class, jeuId);
        if (j == null) {
            return false;
        }
        Utilisateur u = em.find(Utilisateur.class, userId);
        if (u == null) {
            return false;
        }

        if (u.getAge() < j.getAgeMin()) {
            return false;
        }

        Set<Cle> cles = new HashSet<>();
        Iterator<Cle> clesDuJeuIte = j.getCles().iterator();

        for (int i = 0; i < count && clesDuJeuIte.hasNext(); i++) {
            cles.add(clesDuJeuIte.next());
        }

        Calendar now = Calendar.getInstance();
        for (Cle c : cles) {
            u.addAchat(new Achat(u, now, c));
        }
        return true;
    }

    @Override
    public Achat findAchatByCle(String cle) {
        ArrayList<Achat> cad = new ArrayList<>();
        try {
            TypedQuery<Achat> query = em.createNamedQuery("GetAchatByCleId", Achat.class);
            query.setParameter("id", cle);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
