/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.UtilisateurDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteUtilisateurDAO {

    public UtilisateurDTO find(long id);

    public Collection<UtilisateurDTO> findAll();

    public Collection<UtilisateurDTO> findAllFromAchat(AchatDTO a);

    public boolean create(UtilisateurDTO obj);

    public boolean update(UtilisateurDTO obj);

    public boolean delete(UtilisateurDTO obj);

    public UtilisateurDTO findFromLoginEtMdp(String login, String hashMdp);

}
