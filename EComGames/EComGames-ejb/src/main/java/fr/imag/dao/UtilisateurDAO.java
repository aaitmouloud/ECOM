/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalUtilisateurDAO;
import fr.imag.dao.local.IntLocalAchatDAO;
import fr.imag.dao.remote.IntRemoteUtilisateurDAO;
import fr.imag.entities.Utilisateur;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.UtilisateurDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
@Stateless
public class UtilisateurDAO extends IntDAO implements IntLocalUtilisateurDAO, IntRemoteUtilisateurDAO {

    @EJB
    IntLocalAchatDAO achatDAO;

    @Override
    public UtilisateurDTO find(long id) {
        try {
            Utilisateur u = em.find(Utilisateur.class, id);
            return convert(u);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<UtilisateurDTO> findAll() {
        ArrayList<UtilisateurDTO> cud = new ArrayList<>();
        try {
            TypedQuery<Utilisateur> query = em.createNamedQuery("GetAllUser", Utilisateur.class);
            Collection<Utilisateur> cu = query.getResultList();
            for (Utilisateur u : cu) {
                cud.add(convert(u));
            }
            return cud;
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Collection<UtilisateurDTO> findAllFromAchat(AchatDTO a) {
        ArrayList<UtilisateurDTO> cud = new ArrayList<>();
        try {
            TypedQuery<Utilisateur> query = em.createNamedQuery("GetUserByAchatId", Utilisateur.class);
            query.setParameter("id", a.getId());
            Collection<Utilisateur> cu = query.getResultList();
            for (Utilisateur u : cu) {
                cud.add(convert(u));
            }
            return cud;
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public boolean create(UtilisateurDTO obj) {
        try {
            Utilisateur u = convertDTO(obj);
            em.persist(u);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(UtilisateurDTO obj) {
        try {
            Utilisateur u = convertDTO(obj);
            em.refresh(u);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(UtilisateurDTO obj) {
        try {
            Utilisateur u = convertDTO(obj);
            em.remove(u);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Utilisateur convertDTO(UtilisateurDTO obj) {
        Utilisateur u;
        if (obj != null) {
            u = em.find(Utilisateur.class, obj.getId());
            if (u == null) {
                u = new Utilisateur(obj.getNom(), obj.getHashMdp(), obj.getDateNaissance(), obj.getEmail());
                for (AchatDTO a : obj.getAchats()) {
                    u.addAchat(achatDAO.convertDTO(a));
                }
            }

            return u;
        }
        return null;
    }

    @Override
    public UtilisateurDTO convert(Utilisateur obj) {
        UtilisateurDTO ud;
        if (obj != null) {
            ud = new UtilisateurDTO(obj);
            return ud;
        }
        return null;
    }

    @Override
    public UtilisateurDTO findFromLoginEtMdp(String login, String hashMdp) {
        try {
            TypedQuery<Utilisateur> query = em.createNamedQuery("GetUserByLoginAndMdp", Utilisateur.class);
            query.setParameter("login", login);
            query.setParameter("hashMdp", hashMdp);

            Utilisateur res = query.getSingleResult();
            return convert(res);
        } catch (Exception e) {
            return null;
        }
    }

}
