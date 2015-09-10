/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalPlateformeDAO;
import fr.imag.dao.remote.IntRemotePlateformeDAO;
import fr.imag.entities.Plateforme;
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
public class PlateformeDAO extends AbstractDAO implements IntLocalPlateformeDAO, IntRemotePlateformeDAO {
    
    

   
    @Override
    public Collection<Plateforme> findAll() {
         ArrayList<Plateforme> cpd = new ArrayList<>();
        try{
             TypedQuery<Plateforme> query = em.createNamedQuery("GetAllPlateforme", Plateforme.class);
             return query.getResultList();
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
    
        
}
