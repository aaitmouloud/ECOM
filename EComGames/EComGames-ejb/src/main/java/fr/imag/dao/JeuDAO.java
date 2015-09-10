/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalPrixJeuDAO;
import fr.imag.dao.local.IntLocalPlateformeDAO;
import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.local.IntLocalCategorieDAO;
import fr.imag.dao.local.IntLocalCleDAO;
import fr.imag.dao.local.IntLocalEditeurDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Categorie;
import fr.imag.entities.Editeur;
import fr.imag.entities.Jeu;
import fr.imag.entities.Plateforme;
import fr.imag.entities.dto.CategorieDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.EditeurDTO;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PlateformeDTO;
import fr.imag.entities.dto.PrixJeuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
@Stateless
public class JeuDAO extends IntDAO implements IntLocalJeuDAO, IntRemoteJeuDAO{
    
    @EJB
    IntLocalCategorieDAO categorieDAO;
    
    @EJB
    IntLocalCleDAO cleDAO;
    
    @EJB
    IntLocalEditeurDAO editeurDAO;
    
    @EJB
    IntLocalPrixJeuDAO prixJeuDAO;
    
    @EJB
    IntLocalPlateformeDAO plateformeDAO;

 
    @Override
    public JeuDTO find(long id) {
          try{
            Jeu j =em.find(Jeu.class, id);
            return convert(j);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public Collection<JeuDTO> findAll() {
        ArrayList<JeuDTO> cjd = new ArrayList<>();
        try{
             TypedQuery<Jeu> query = em.createNamedQuery("GetAllJeu", Jeu.class);
             Collection<Jeu> cj = query.getResultList();
             for(Jeu j: cj){
                 cjd.add(convert(j));
             }
             return cjd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
    public Collection<JeuDTO> findAllFromCle(CleDTO c) {
        ArrayList<JeuDTO> cjd = new ArrayList<>();
        try{
             TypedQuery<Jeu> query = em.createNamedQuery("GetJeuByCleId", Jeu.class);
             query.setParameter("id", c.getCle());
             Collection<Jeu> cj = query.getResultList();
             for(Jeu j: cj){
                 cjd.add(convert(j));
             }
             return cjd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
     public Collection<JeuDTO> findAllFromCategorie(CategorieDTO c) {
        ArrayList<JeuDTO> cjd = new ArrayList<>();
        try{
             TypedQuery<Jeu> query = em.createNamedQuery("GetJeuByCategorieId", Jeu.class);
             query.setParameter("id", c.getId());
             Collection<Jeu> cj = query.getResultList();
             for(Jeu j: cj){
                 cjd.add(convert(j));
             }
             return cjd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
      
    @Override
     public Collection<JeuDTO> findAllFromEditeur (EditeurDTO e) {
        ArrayList<JeuDTO> cjd = new ArrayList<>();
        try{
             TypedQuery<Jeu> query = em.createNamedQuery("GetJeuByEditeurId", Jeu.class);
             query.setParameter("id", e.getId());
             Collection<Jeu> cj = query.getResultList();
             for(Jeu j: cj){
                 cjd.add(convert(j));
             }
             return cjd;
        }catch (Exception ex){
            return Collections.EMPTY_LIST;
        }
    }
     
    @Override
     public Collection<JeuDTO> findAllFromPlaterforme (PlateformeDTO p) {
        ArrayList<JeuDTO> cjd = new ArrayList<>();
        try{
             TypedQuery<Jeu> query = em.createNamedQuery("GetJeuByPlateformeId", Jeu.class);
             query.setParameter("id", p.getId());
             Collection<Jeu> cj = query.getResultList();
             for(Jeu j: cj){
                 cjd.add(convert(j));
             }
             return cjd;
        }catch (Exception ex){
            return Collections.EMPTY_LIST;
        }
    }
     
    @Override
    public Collection<JeuDTO> Search (Collection<CategorieDTO> cc, Collection<EditeurDTO> ce, Collection<PlateformeDTO> cp, PrixJeuDTO min, PrixJeuDTO max){
        ArrayList<Categorie> c = new ArrayList<>();
        ArrayList<Editeur> e = new ArrayList<>();
        ArrayList<Plateforme> p = new ArrayList<>();
        Iterator<CategorieDTO> ic = cc.iterator();
        Iterator<EditeurDTO> ie = ce.iterator();
        Iterator<PlateformeDTO> ip = cp.iterator();
        while (ic.hasNext()){
            CategorieDTO cd = ic.next();
            c.add(categorieDAO.convertDTO(cd));
        }
        
        while (ie.hasNext()){
            EditeurDTO ed = ie.next();
            e.add(editeurDAO.convertDTO(ed));
        }
        
        while (ip.hasNext()){
            PlateformeDTO pd = ip.next();
            p.add(plateformeDAO.convertDTO(pd));
        }
        
        ArrayList<JeuDTO> cjd = new ArrayList<>();
        try{
            TypedQuery<Jeu> query = em.createNamedQuery("SearchJeu", Jeu.class);
             query.setParameter("prixMin",min.getPrix() );
             query.setParameter("prixMax", max.getPrix());
             if (c.isEmpty()){
                 query.setParameter("cid", null);
             }else{
                 query.setParameter("cid", c);
             }
             
             if (e.isEmpty()){
                 query.setParameter("ced", null);
             }else{
                 query.setParameter("ced", e);
             }
             
             if(p.isEmpty()){
                 query.setParameter("plid", null);
             }else{
                 query.setParameter("plid", p);
             }
             
             Collection<Jeu> cj = query.getResultList();     
             for(Jeu j: cj){
                 cjd.add(convert(j));
             }
             return cjd;       
        }catch (Exception ex){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
    public boolean create(JeuDTO obj) {
        try{
            Jeu j = convertDTO(obj);
            em.persist(j);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean update(JeuDTO obj) {
          try{
            Jeu j = convertDTO(obj);
            em.refresh(j);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean delete(JeuDTO obj) {
         try{
            Jeu j = convertDTO(obj);
            em.remove(j);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public Jeu convertDTO(JeuDTO obj) {
        Jeu j = null;
        if (obj != null){
            if (obj.getId() != null){
                j = em.find(Jeu.class, obj.getId());
            }
            if (j == null){
                j = new Jeu(obj.getNom(), obj.getDescription(), obj.getAnnee(), obj.getAgeMin(),obj.getUrl());
                for (CategorieDTO c: obj.getCategories()){
                    j.addCategorie(categorieDAO.convertDTO(c));
                }
                for (PrixJeuDTO p: obj.getPrix()){
                    j.addPrix(prixJeuDAO.convertDTO(p));
                }
                
                for (PlateformeDTO p: obj.getPlateformes()){
                    j.addPlateforme(plateformeDAO.convertDTO(p));
                }
                
                for (CleDTO c: obj.getCles()){
                    j.addCle(cleDAO.convertDTO(c));
                }
                j.setEditeur(editeurDAO.convertDTO(obj.getEditeur()));
            }
            
            return j;
        }
        return null;
    }

    
    @Override
    public JeuDTO convert(Jeu obj) {
         JeuDTO jd;
        if(obj != null){
            jd = new JeuDTO(obj);
            return jd;
        }
        return null;
    }
    
}
