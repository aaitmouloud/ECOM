/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.Business.remote;

import fr.imag.Business.JeuManager;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface JeuManagerRemote {
    Collection<JeuDTO> orderBy(Collection<JeuDTO> cjd, JeuManager.Element e, JeuManager.Sens s);
    public float getAverageNote(JeuDTO j);
    public int getNbSell(JeuDTO j);
    public Double getPrix(JeuDTO j);
}
