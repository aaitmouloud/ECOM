/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.UtilisateurDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteAchatDAO {
    public AchatDTO find(long id);
    public Collection<AchatDTO> findAll();
    public Collection<AchatDTO> findAllFromUser(UtilisateurDTO user);
    public AchatDTO findAllFromCle(CleDTO cle);
    public boolean create(AchatDTO obj);
    public boolean update(AchatDTO obj);
    public boolean delete(AchatDTO obj);
}
