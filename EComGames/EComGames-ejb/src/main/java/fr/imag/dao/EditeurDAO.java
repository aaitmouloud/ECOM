/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.local.IntLocalEditeurDAO;
import fr.imag.dao.remote.IntRemoteEditeurDAO;
import fr.imag.entities.Editeur;
import fr.imag.entities.dto.EditeurDTO;
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
public class EditeurDAO extends IntDAO implements IntLocalEditeurDAO, IntRemoteEditeurDAO{
   
    @EJB
    IntLocalJeuDAO jeuDAO;

    @Override
    public EditeurDTO find(long id) {
          try{
            Editeur e =em.find(Editeur.class, id);
            return convert(e);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<EditeurDTO> findAll() {
        ArrayList<EditeurDTO> ced = new ArrayList<>();
        try{
             TypedQuery<Editeur> query = em.createNamedQuery("GetAllEditeur", Editeur.class);
             Collection<Editeur> ce = query.getResultList();
             for(Editeur e: ce){
                 ced.add(convert(e));
             }
             return ced;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    
    @Override
     public Collection<EditeurDTO> findAllFromJeu(JeuDTO j) {
        ArrayList<EditeurDTO> ced = new ArrayList<>();
        try{
             TypedQuery<Editeur> query = em.createNamedQuery("GetEditeurByJeuId", Editeur.class);
             query.setParameter("id", j.getId());
             Collection<Editeur> ce = query.getResultList();
             for(Editeur e: ce){
                 ced.add(convert(e));
             }
             return ced;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public boolean create(EditeurDTO obj) {
            try{
           Editeur e = convertDTO(obj);
            em.persist(e);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(EditeurDTO obj) {
            try{
           Editeur e = convertDTO(obj);
            em.refresh(e);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(EditeurDTO obj) {
             try{
           Editeur e = convertDTO(obj);
            em.remove(e);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Editeur convertDTO(EditeurDTO obj) {
        Editeur e;
        if (obj != null){
            e = em.find(Editeur.class, obj.getId());
            if (e == null){
                e = new Editeur(obj.getNom(), obj.getDescription(), obj.getLogo());
                for (JeuDTO j: obj.getJeux()){
                    jeuDAO.convertDTO(j).setEditeur(e);
                }
            }
            
            return e;
        }
        return null;
    }

    @Override
    public EditeurDTO convert(Editeur obj) {
         EditeurDTO ed;
        if(obj != null){
            ed = new EditeurDTO(obj);
            return ed;
        }
        return null;
    }
    
}
