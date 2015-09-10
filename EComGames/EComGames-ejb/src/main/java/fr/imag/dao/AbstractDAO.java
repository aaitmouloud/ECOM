/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author seb
 */
abstract class AbstractDAO {
    @PersistenceContext(unitName = "EComGamesPU")
    protected EntityManager em;
    
    public <U> boolean create(U entity) {
        em.persist(entity);
        return em.contains(entity);
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
