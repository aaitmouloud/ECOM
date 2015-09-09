/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.remote;

import fr.imag.entities.Editeur;
import fr.imag.entities.dto.EditeurDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteEditeurDAO {
    public EditeurDTO find(long id);
    public Collection<EditeurDTO> findAll();
    public Collection<EditeurDTO> findAllFromJeu(JeuDTO j);
    public boolean create(EditeurDTO obj);
    public boolean update(EditeurDTO obj);
    public boolean delete(EditeurDTO obj);
    
}
