/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalAchatDAO;
import fr.imag.dao.remote.IntRemoteAchatDAO;
import fr.imag.entities.Achat;
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
public class AchatDAO extends AbstractDAO implements IntLocalAchatDAO, IntRemoteAchatDAO{
   

    @Override
    public Collection<Achat> findAll() {
        ArrayList<Achat> cad = new ArrayList<>();
        try{
             TypedQuery<Achat> query = em.createNamedQuery("GetAllAchat", Achat.class);
             return query.getResultList();
        }catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
        
}
