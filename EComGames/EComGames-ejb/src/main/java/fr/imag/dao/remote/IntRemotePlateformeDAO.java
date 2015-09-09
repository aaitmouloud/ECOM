/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.remote;

import fr.imag.entities.Plateforme;
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