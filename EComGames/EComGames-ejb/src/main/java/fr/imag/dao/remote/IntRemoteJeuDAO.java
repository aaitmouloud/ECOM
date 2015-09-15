/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.dao.IntDAO;
import fr.imag.entities.Jeu;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteJeuDAO extends IntDAO<Jeu> {

    public Collection<Jeu> Search(Collection<String> cc,
            Collection<String> ce, Collection<String> cp,
            double min, double max);
    
    public Jeu findById(String id);
}
