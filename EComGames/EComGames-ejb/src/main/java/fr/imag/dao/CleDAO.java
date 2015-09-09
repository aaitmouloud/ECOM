/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.local.IntLocalAchatDAO;
import fr.imag.dao.local.IntLocalCleDAO;
import fr.imag.dao.remote.IntRemoteCleDAO;
import fr.imag.entities.Cle;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.JeuDTO;
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
public class CleDAO extends IntDAO implements IntLocalCleDAO, IntRemoteCleDAO {
    
    @EJB
    IntLocalJeuDAO jeuDAO;
    
    @EJB
    IntLocalAchatDAO achatDAO;
    
    @Override
    public CleDTO find(long id) {
        try{
            Cle c =em.find(Cle.class, id);
            return convert(c);
        }catch (Exception e){
            return null;
        } 
    }

    @Override
    public Collection<CleDTO> findAll() {
        ArrayList<CleDTO> ccd = new ArrayList<>();
        try{
             TypedQuery<Cle> query = em.createNamedQuery("GetAllCle", Cle.class);
             Collection<Cle> cc = query.getResultList();
             for(Cle c: cc){
                 ccd.add(convert(c));
             }
             return ccd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
     public Collection<CleDTO> findAllFromAchat(AchatDTO a) {
        ArrayList<CleDTO> ccd = new ArrayList<>();
        try{
             TypedQuery<Cle> query = em.createNamedQuery("GetCleByAchatId", Cle.class);
             query.setParameter("id", a.getId());
             Collection<Cle> cc = query.getResultList();
             for(Cle c: cc){
                 ccd.add(convert(c));
             }
             return ccd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
     
    @Override
    public Collection<CleDTO> findAllFromJeu(JeuDTO j) {
        ArrayList<CleDTO> ccd = new ArrayList<>();
        try{
             TypedQuery<Cle> query = em.createNamedQuery("GetCleByJeuId", Cle.class);
             query.setParameter("id", j.getId());
             Collection<Cle> cc = query.getResultList();
             for(Cle c: cc){
                 ccd.add(convert(c));
             }
             return ccd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public boolean create(CleDTO obj) {
         try{
           Cle c = convertDTO(obj);
            em.persist(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(CleDTO obj) {
         try{
           Cle c = convertDTO(obj);
            em.refresh(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean delete(CleDTO obj) {
         try{
           Cle c = convertDTO(obj);
            em.remove(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
     public Cle convertDTO(CleDTO obj) {
      Cle c;
        if (obj != null){
            c = em.find(Cle.class, obj.getCle());
            if (c == null){
                c = new Cle(jeuDAO.convertDTO(obj.getJeu()),achatDAO.convertDTO(obj.getAchat()));
            }
            
            return c;
        }
        return null;
    }


    @Override
    public CleDTO convert(Cle obj) {
         CleDTO cd;
        if(obj != null){
            cd = new CleDTO(obj);
            return cd;
        }
        return null;
    }
    
}
