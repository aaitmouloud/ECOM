/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalPlateformeDAO;
import fr.imag.dao.remote.IntRemotePlateformeDAO;
import fr.imag.entities.Plateforme;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PlateformeDTO;
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
public class PlateformeDAO extends IntDAO implements IntLocalPlateformeDAO, IntRemotePlateformeDAO {
    
    

    @Override
    public PlateformeDTO find(long id) {
        try{
              Plateforme p =em.find(Plateforme.class, id);
            return convert(p);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public Collection<PlateformeDTO> findAll() {
         ArrayList<PlateformeDTO> cpd = new ArrayList<>();
        try{
             TypedQuery<Plateforme> query = em.createNamedQuery("GetAllPlateforme", Plateforme.class);
             Collection<Plateforme> cp = query.getResultList();
             for(Plateforme p: cp){
                 cpd.add(convert(p));
             }
             return cpd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
    @Override
       public Collection<PlateformeDTO> findAllFromJeu(JeuDTO j) {
         ArrayList<PlateformeDTO> cpd = new ArrayList<>();
        try{
             TypedQuery<Plateforme> query = em.createNamedQuery("GetPlateformeByJeuId", Plateforme.class);
             query.setParameter("id", j.getId());
             Collection<Plateforme> cp = query.getResultList();
             for(Plateforme p: cp){
                 cpd.add(convert(p));
             }
             return cpd;
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }


    @Override
    public boolean create(PlateformeDTO obj) {
        try{
            Plateforme p = convertDTO(obj);
            em.persist(p);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean update(PlateformeDTO obj) {
        try{
            Plateforme p = convertDTO(obj);
            em.refresh(p);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public boolean delete(PlateformeDTO obj) {
        try{
            Plateforme p = convertDTO(obj);
            em.remove(p);
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public Plateforme convertDTO(PlateformeDTO obj) {
          Plateforme p = null;
        if (obj != null){
            if (obj.getId() != null){
             p = em.find(Plateforme.class, obj.getId());
            }
            if (p == null){
                p = new Plateforme(obj.getNom(), obj.getImage());
            }
            
            return p;
        }
        return null;
    }


    
    @Override
    public PlateformeDTO convert(Plateforme obj) {
        PlateformeDTO pd;
        if(obj != null){
            pd = new PlateformeDTO(obj);
            return pd;
        }
        return null;
    }
    
}
