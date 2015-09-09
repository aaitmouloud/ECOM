/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.local;

import fr.imag.entities.PrixJeu;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PrixJeuDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalPrixJeuDAO {
    public PrixJeuDTO find(long id);
    public Collection<PrixJeuDTO> findAll();
    public Collection<PrixJeuDTO> findAllFromJeu(JeuDTO j);
    public boolean create(PrixJeuDTO obj);
    public boolean update(PrixJeuDTO obj);
    public boolean delete(PrixJeuDTO obj);
    PrixJeu convertDTO(PrixJeuDTO obj);
    PrixJeuDTO convert(PrixJeu obj);
}
