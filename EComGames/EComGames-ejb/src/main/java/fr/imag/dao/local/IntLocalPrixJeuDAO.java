/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.local;

import fr.imag.dao.IntDAO;
import fr.imag.entities.Jeu;
import fr.imag.entities.PrixJeu;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalPrixJeuDAO extends IntDAO<PrixJeu> {
}
