/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.dao.remote;

import fr.imag.entities.Cle;
import fr.imag.entities.dto.AchatDTO;
import fr.imag.entities.dto.CleDTO;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface IntRemoteCleDAO {
    public CleDTO find(long id);
    public Collection<CleDTO> findAll();
    public Collection<CleDTO> findAllFromAchat(AchatDTO a);
    public Collection<CleDTO> findAllFromJeu(JeuDTO j);
    public boolean create(CleDTO obj);
    public boolean update(CleDTO obj);
    public boolean delete(CleDTO obj);
    
}
