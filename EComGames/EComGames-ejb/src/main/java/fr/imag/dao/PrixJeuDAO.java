/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalPrixJeuDAO;
import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.remote.IntRemotePrixJeuDAO;
import fr.imag.entities.PrixJeu;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PrixJeuDTO;
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
public class PrixJeuDAO extends IntDAO implements IntLocalPrixJeuDAO, IntRemotePrixJeuDAO {
  
   @EJB
   IntLocalJeuDAO jeuDAO;
   

    
   @Override
    public PrixJeuDTO find(long id) {
        try{
            PrixJeu p =em.find(PrixJeu.class, id);
            return convert(p);
        }catch (Exception e){
            return null;
        }
    }


   @Override
    public Collection<PrixJeuDTO> findAll() {
       ArrayList<PrixJeuDTO> cpd = new ArrayList<>();
        try{
             TypedQuery<PrixJeu> query = em.createNamedQuery("GetAllPrixJeu", PrixJeu.class);
             Collection<PrixJeu> cp = query.getResultList();
             for(PrixJeu p: cp){
                 cpd.add(convert(p));
             }
             return cpd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

   @Override
    public Collection<PrixJeuDTO> findAllFromJeu(JeuDTO j) {
       ArrayList<PrixJeuDTO> cpd = new ArrayList<>();
        try{
             TypedQuery<PrixJeu> query = em.createNamedQuery("GetPrixJeuByJeuId", PrixJeu.class);
             query.setParameter("id", j.getId());
             Collection<PrixJeu> cp = query.getResultList();
             for(PrixJeu p: cp){
                 cpd.add(convert(p));
             }
             return cpd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
    public PrixJeuDTO findPriceFromJeu(JeuDTO j) {
        try{
             TypedQuery<PrixJeu> query = em.createNamedQuery("GetCurrentPrixJeuByJeuId", PrixJeu.class);
             query.setParameter("id", j.getId());
             PrixJeu p = query.getSingleResult();
             return convert(p);
        }catch (Exception e){
            return null;
        }
    }
    
    @Override
    public PrixJeuDTO getMaxPrix() {
        try{
             TypedQuery<PrixJeu> query = em.createNamedQuery("GetMaxPrix", PrixJeu.class);
             PrixJeu p = (PrixJeu)query.getSingleResult();
            return convert(p);
        }catch (Exception e){
            return null;
        }
    }
 
   @Override
    public boolean create(PrixJeuDTO obj) {
        try{
            PrixJeu p = convertDTO(obj);
            em.persist(p);
        }catch(Exception e){
            return false;
        }
        return true;
    }

  
   @Override
    public boolean update(PrixJeuDTO obj) {
        try{
            PrixJeu p = convertDTO(obj);
            em.refresh(p);
        }catch(Exception e){
            return false;
        }
        return true;
    }


   @Override
    public boolean delete(PrixJeuDTO obj) {
        try{
            PrixJeu p = convertDTO(obj);
            em.remove(p);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    
   @Override
    public PrixJeu convertDTO(PrixJeuDTO obj) {
          PrixJeu p = null;
        if (obj != null){
            if (obj.getId() != null){
             p = em.find(PrixJeu.class, obj.getId());
            }
            if (p == null){
                p = new PrixJeu(jeuDAO.convertDTO(obj.getJeu()), obj.getDateDebut(), obj.getDateFin(), obj.getPrix());
                 }
            
            return p;
        }
        return null;
    }

 
   @Override
    public PrixJeuDTO convert(PrixJeu obj) {
        PrixJeuDTO pd;
        if(obj != null){
            pd = new PrixJeuDTO(obj);
            return pd;
        }
        return null;
    }
    
}
