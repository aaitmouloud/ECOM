/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.local;

import fr.imag.entities.Utilisateur;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.UtilisateurDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalUtilisateurDAO {
    public UtilisateurDTO find(long id);
    public Collection<UtilisateurDTO> findAll();
    public Collection<UtilisateurDTO> findAllFromAchat(AchatDTO a);
    public boolean create(UtilisateurDTO obj);
    public boolean update(UtilisateurDTO obj);
    public boolean delete(UtilisateurDTO obj);
    Utilisateur convertDTO(UtilisateurDTO obj);
    UtilisateurDTO convert(Utilisateur obj);
}
