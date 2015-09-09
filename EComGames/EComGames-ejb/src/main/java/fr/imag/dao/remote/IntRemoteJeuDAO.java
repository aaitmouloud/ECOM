/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.remote;

import fr.imag.entities.Jeu;
import fr.imag.entities.dto.CategorieDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.EditeurDTO;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PlateformeDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteJeuDAO {
     public JeuDTO find(long id);
    public Collection<JeuDTO> findAll();
    public Collection<JeuDTO> findAllFromCle(CleDTO c);
    public Collection<JeuDTO> findAllFromCategorie(CategorieDTO c);
    public Collection<JeuDTO> findAllFromEditeur (EditeurDTO e);
    public Collection<JeuDTO> findAllFromPlaterforme (PlateformeDTO p);
    public boolean create(JeuDTO obj);
    public boolean update(JeuDTO obj);
    public boolean delete(JeuDTO obj);
    
}
