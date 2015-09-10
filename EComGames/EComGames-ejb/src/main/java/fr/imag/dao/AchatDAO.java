/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalUtilisateurDAO;
import fr.imag.dao.local.IntLocalAchatDAO;
import fr.imag.dao.local.IntLocalCleDAO;
import fr.imag.dao.remote.IntRemoteAchatDAO;
import fr.imag.entities.Achat;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.UtilisateurDTO;
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
public class AchatDAO extends IntDAO implements IntLocalAchatDAO, IntRemoteAchatDAO{
    @EJB
    IntLocalUtilisateurDAO utilisateurDAO;
    
    @EJB
    IntLocalCleDAO cleDAO;
    
    @Override
    public AchatDTO find(long id) {
        try{
            Achat a =em.find(Achat.class, id);
            return convert(a);
        }catch (Exception e){
            return null;
        }
                       
    }


    @Override
    public Collection<AchatDTO> findAll() {
        ArrayList<AchatDTO> cad = new ArrayList<>();
        try{
             TypedQuery<Achat> query = em.createNamedQuery("GetAllAchat", Achat.class);
             Collection<Achat> ca = query.getResultList();
             for(Achat a: ca){
                 cad.add(convert(a));
             }
             return cad;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
    public Collection<AchatDTO> findAllFromUser(UtilisateurDTO user) {
         ArrayList<AchatDTO> cad = new ArrayList<>();
        try{
             TypedQuery<Achat> query = em.createNamedQuery("GetAchatByUserId", Achat.class);
             query.setParameter("id", user.getId());
             Collection<Achat> ca = query.getResultList();
             for(Achat a: ca){
                 cad.add(convert(a));
             }
             return cad;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
    public AchatDTO findAllFromCle(CleDTO cle) {
        try{
             TypedQuery<Achat> query = em.createNamedQuery("GetAchatByCleId", Achat.class);
             query.setParameter("id", cle.getCle());
             Achat a = (Achat)query.getSingleResult();
            return convert(a);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public boolean create(AchatDTO obj) {
        try{
            Achat a = convertDTO(obj);
            em.persist(a);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean update(AchatDTO obj) {
        try{
            Achat a = convertDTO(obj);
            em.refresh(a);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean delete(AchatDTO obj) {
          try{
            Achat a = convertDTO(obj);
            em.remove(a);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Achat convertDTO(AchatDTO obj) {
        Achat a = null;
        if (obj != null){
            if (obj.getId() != null){
                a = em.find(Achat.class, obj.getId());
            }
            if (a == null){
                a = new Achat(utilisateurDAO.convertDTO(obj.getUtilisateur()), cleDAO.convertDTO(obj.getCle()), obj.getDate(), obj.getNote(), obj.getCommentaire());
            }
            
            return a;
        }
        return null;
    }


    @Override
    public AchatDTO convert(Achat obj) {
        AchatDTO a;
        if(obj != null){
            a = new AchatDTO(obj);
            return a;
        }
        return null;
    }

    

   
    
}
