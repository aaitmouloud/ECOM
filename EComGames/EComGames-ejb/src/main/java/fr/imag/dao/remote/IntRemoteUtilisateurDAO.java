/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao.remote;

import fr.imag.dao.IntDAO;
import fr.imag.entities.Utilisateur;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteUtilisateurDAO extends IntDAO<Utilisateur>{
    Utilisateur findFromLoginEtMdp(String login, String hashmdp);
    boolean createUser(Utilisateur u);
}
