/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import java.util.Collection;

/**
 *
 * @param <T> Entity class of the DAO.
 * @author aaitmouloud
 */
public interface IntDAO<T> {
    public Collection<T> findAll();
    public <T> boolean create(T entity);
    public <T> void refresh(T entity);
}
