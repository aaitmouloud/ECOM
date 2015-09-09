/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.remote;

import fr.imag.entities.PrixJeu;
import fr.imag.entities.dto.JeuDTO;
import fr.imag.entities.dto.PrixJeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemotePrixJeuDAO {
    public PrixJeuDTO find(long id);
    public Collection<PrixJeuDTO> findAll();
    public Collection<PrixJeuDTO> findAllFromJeu(JeuDTO j);
    public boolean create(PrixJeuDTO obj);
    public boolean update(PrixJeuDTO obj);
    public boolean delete(PrixJeuDTO obj);
    
}
