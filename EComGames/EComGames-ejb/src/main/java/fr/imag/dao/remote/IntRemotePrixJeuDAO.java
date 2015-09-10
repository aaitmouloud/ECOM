/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PrixJeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemotePrixJeuDAO {
    public PrixJeuDTO find(long id);
    public Collection<PrixJeuDTO> findAll();
    public Collection<PrixJeuDTO> findAllFromJeu(JeuDTO j);
    public PrixJeuDTO findPriceFromJeu(JeuDTO j);
    public PrixJeuDTO getMaxPrix();
    public boolean create(PrixJeuDTO obj);
    public boolean update(PrixJeuDTO obj);
    public boolean delete(PrixJeuDTO obj);
    
}
