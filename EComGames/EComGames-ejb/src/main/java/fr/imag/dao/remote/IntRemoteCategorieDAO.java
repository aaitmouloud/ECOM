/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.CategorieDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteCategorieDAO {
    public CategorieDTO find(long id);
    public Collection<CategorieDTO> findAll();
    public Collection<CategorieDTO> findAllFromJeu(JeuDTO jeu);
    public boolean create(CategorieDTO obj);
    public boolean update(CategorieDTO obj);
    public boolean delete(CategorieDTO obj);
    
}
