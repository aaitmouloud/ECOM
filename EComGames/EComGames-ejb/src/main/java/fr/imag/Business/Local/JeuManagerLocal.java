/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.Business.Local;

import fr.imag.Business.JeuManager;
import fr.imag.entities.Jeu;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author seb
 */
@Local
public interface JeuManagerLocal {
    Collection<Jeu> orderBy(Collection<Jeu> cjd, JeuManager.Element e, JeuManager.Sens s);
    public float getAverageNote(Jeu j);
    public int getNbSell(Jeu j);
    public Double getPrix(Jeu j);
}
