/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.Business;

import fr.imag.Business.Local.JeuManagerLocal;
import fr.imag.entities.dto.JeuDTO;
import java.util.Collection;
import javax.ejb.Stateless;

/**
 *
 * @author seb
 */
@Stateless
public class JeuManager implements JeuManagerLocal {
   
    
    Collection<JeuDTO> oderBy(Collection<JeuDTO> cjd, Element e, Sens s){
        
        cjd.toArray();
        return null;
    }
}
