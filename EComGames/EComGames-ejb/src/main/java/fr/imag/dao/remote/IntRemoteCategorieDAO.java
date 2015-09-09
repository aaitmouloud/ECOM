/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.remote;

import fr.imag.entities.Categorie;
import fr.imag.entities.dto.CategorieDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteCategorieDAO {
    public CategorieDTO find(long id);
    public Collection<CategorieDTO> findAll();
    public Collection<CategorieDTO> findAllFromJeu(JeuDTO jeu);
    public boolean create(CategorieDTO obj);
    public boolean update(CategorieDTO obj);
    public boolean delete(CategorieDTO obj);
    
}
