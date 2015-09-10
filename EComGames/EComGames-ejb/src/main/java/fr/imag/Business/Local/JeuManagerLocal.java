/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.Business.Local;

import fr.imag.Business.JeuManager;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface JeuManagerLocal {
    Collection<JeuDTO> orderBy(Collection<JeuDTO> cjd, JeuManager.Element e, JeuManager.Sens s);
    public float getAverageNote(JeuDTO j);
    public int getNbSell(JeuDTO j);
    public Double getPrix(JeuDTO j);
}
