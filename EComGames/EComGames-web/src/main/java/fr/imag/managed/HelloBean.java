/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.managed;

import fr.imag.ejb.Initializer;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author aaitmouloud
 */
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;
        
        @EJB
	private Initializer init;
	private String name;
        
        public Collection<Jeu> getJeux() {
		return init.getJeu();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
