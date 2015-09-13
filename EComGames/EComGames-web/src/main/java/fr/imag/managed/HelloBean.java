/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag.managed;

import fr.imag.ejb.Initializer;
import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

    public List<String> getAuto() {
        return Arrays.asList("recherche qqqch", "super mario bros", "the legend of zelda");

    }

    public List<String> getAuto(String searchTerms) {
        if (searchTerms == null || searchTerms.isEmpty()) {
            return Collections.emptyList();
        }
        String[] terms = searchTerms.split(" ");

        List<String> completeTermsList = Arrays.asList("recherche qqqch", "super mario bros", "the legend of zelda");

        HashSet<String> toReturn = new HashSet<>();
        for (int i = 0; i < terms.length; i++) {
            for (String all : completeTermsList) {
                if (all.contains(terms[i])) {
                    toReturn.add(all);
                }
            }
        }

        return new ArrayList<>(toReturn);

    }

}
