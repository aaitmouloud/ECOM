/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.local;

import fr.imag.entities.Editeur;
import fr.imag.entities.dto.EditeurDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalEditeurDAO {
    public EditeurDTO find(long id);
    public Collection<EditeurDTO> findAll();
    public Collection<EditeurDTO> findAllFromJeu(JeuDTO j);
    public boolean create(EditeurDTO obj);
    public boolean update(EditeurDTO obj);
    public boolean delete(EditeurDTO obj);
    Editeur convertDTO(EditeurDTO obj);
    EditeurDTO convert(Editeur obj);
}
