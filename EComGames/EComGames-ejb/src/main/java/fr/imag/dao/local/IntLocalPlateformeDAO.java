/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.local;

import fr.imag.entities.Plateforme;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PlateformeDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalPlateformeDAO {
    public PlateformeDTO find(long id);
    public Collection<PlateformeDTO> findAll();
    public Collection<PlateformeDTO> findAllFromJeu(JeuDTO j);
    public boolean create(PlateformeDTO obj);
    public boolean update(PlateformeDTO obj);
    public boolean delete(PlateformeDTO obj);
    Plateforme convertDTO(PlateformeDTO obj);
    PlateformeDTO convert(Plateforme obj);
}
