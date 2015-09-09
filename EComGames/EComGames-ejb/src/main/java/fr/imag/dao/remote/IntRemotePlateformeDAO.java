/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PlateformeDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemotePlateformeDAO {
      public PlateformeDTO find(long id);
    public Collection<PlateformeDTO> findAll();
    public Collection<PlateformeDTO> findAllFromJeu(JeuDTO j);
    public boolean create(PlateformeDTO obj);
    public boolean update(PlateformeDTO obj);
    public boolean delete(PlateformeDTO obj);
   
}
