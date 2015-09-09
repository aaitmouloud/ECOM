/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalCategorieDAO;
import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.remote.IntRemoteCategorieDAO;
import fr.imag.entities.Categorie;
import fr.imag.entities.dto.CategorieDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.EJB;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
public class CategorieDAO extends IntDAO implements IntLocalCategorieDAO, IntRemoteCategorieDAO {
    @EJB
    IntLocalJeuDAO jeuDAO;

    @Override
    public CategorieDTO find(long id) {
        try{
            Categorie c =em.find(Categorie.class, id);
            return convert(c);
        }catch (Exception e){
            return null;
        }    
    }

    @Override
    public Collection<CategorieDTO> findAll() {
        ArrayList<CategorieDTO> ccd = new ArrayList<>();
        try{
             TypedQuery<Categorie> query = em.createNamedQuery("GetAllCategorie", Categorie.class);
             Collection<Categorie> cc = query.getResultList();
             for(Categorie c: cc){
                 ccd.add(convert(c));
             }
             return ccd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Collection<CategorieDTO> findAllFromJeu(JeuDTO jeu) {
        ArrayList<CategorieDTO> ccd = new ArrayList<>();
        try{
             TypedQuery<Categorie> query = em.createNamedQuery("GetCategorieByJeuId", Categorie.class);
             query.setParameter("id", jeu.getId());
             Collection<Categorie> cc = query.getResultList();
             for(Categorie c: cc){
                 ccd.add(convert(c));
             }
             return ccd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
    public boolean create(CategorieDTO obj) {
        try{
            Categorie c = convertDTO(obj);
            em.persist(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(CategorieDTO obj) {
        try{
            Categorie c = convertDTO(obj);
            em.refresh(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(CategorieDTO obj) {
        try{
            Categorie c = convertDTO(obj);
            em.remove(c);
        }catch(Exception e){
            return false;
        }
        return true;
    }

 
    
    @Override
    public Categorie convertDTO(CategorieDTO obj) {
         Categorie c;
        if (obj != null){
            c = em.find(Categorie.class, obj.getId());
            if (c == null){
                c = new Categorie(obj.getNom());
                for (JeuDTO j: obj.getJeux()){
                    c.addJeux(jeuDAO.convertDTO(j));
                }
            }
            
            return c;
        }
        return null;
    }

 
    @Override
    public CategorieDTO convert(Categorie obj) {
        CategorieDTO cd;
        if(obj != null){
            cd = new CategorieDTO(obj);
            return cd;
        }
        return null;
    }

   
    
}
