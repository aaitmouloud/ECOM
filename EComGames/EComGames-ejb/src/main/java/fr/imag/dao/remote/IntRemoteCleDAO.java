/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteCleDAO {
    public CleDTO find(long id);
    public Collection<CleDTO> findAll();
    public Collection<CleDTO> findAllFromAchat(AchatDTO a);
    public Collection<CleDTO> findAllFromJeu(JeuDTO j);
    public boolean create(CleDTO obj);
    public boolean update(CleDTO obj);
    public boolean delete(CleDTO obj);
    
}
