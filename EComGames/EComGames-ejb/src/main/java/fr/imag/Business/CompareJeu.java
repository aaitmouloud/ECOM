/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.Business;

import fr.imag.entities.dto.JeuDTO;
import javax.ejb.Stateless;

/**
 *
 * @author seb
 */
@Stateless
public class CompareJeu implements CompareJeuLocal, Comparable<JeuDTO> {

    private Element e;
    private Sens s;
    public static enum Element{
        Note,
        Defaut,
        BestSell,
        Genre
    };
    
    public static enum Sens{
        Croissant,
        Decroissant
    };
    
    @Override
    public int compareTo(JeuDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
