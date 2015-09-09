/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
