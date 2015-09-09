/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.local;

import fr.imag.entities.Achat;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.UtilisateurDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalAchatDAO {
    public AchatDTO find(long id);
    public Collection<AchatDTO> findAll();
    public Collection<AchatDTO> findAllFromUser(UtilisateurDTO user);
    public boolean create(AchatDTO obj);
    public boolean update(AchatDTO obj);
    public boolean delete(AchatDTO obj);
    Achat convertDTO(AchatDTO obj);
    AchatDTO convert(Achat obj);
}
