/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.EditeurDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteEditeurDAO {
    public EditeurDTO find(long id);
    public Collection<EditeurDTO> findAll();
    public Collection<EditeurDTO> findAllFromJeu(JeuDTO j);
    public boolean create(EditeurDTO obj);
    public boolean update(EditeurDTO obj);
    public boolean delete(EditeurDTO obj);
    
}
