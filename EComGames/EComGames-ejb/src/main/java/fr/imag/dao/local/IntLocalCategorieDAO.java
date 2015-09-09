/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.local;

import fr.imag.entities.Categorie;
import fr.imag.entities.dto.CategorieDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface IntLocalCategorieDAO {
    public CategorieDTO find(long id);
    public Collection<CategorieDTO> findAll();
    public Collection<CategorieDTO> findAllFromJeu(JeuDTO jeu);
    public boolean create(CategorieDTO obj);
    public boolean update(CategorieDTO obj);
    public boolean delete(CategorieDTO obj);
    Categorie convertDTO(CategorieDTO obj);
    CategorieDTO convert(Categorie obj);
}
