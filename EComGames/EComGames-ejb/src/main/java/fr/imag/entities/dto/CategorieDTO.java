/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.Categorie;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author aaitmouloud
 */
public class CategorieDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nom;
    private Collection<JeuDTO> jeux;

    public CategorieDTO(Categorie c) {
        this(c.getId(), c.getNom());
    }

    public CategorieDTO(Long id, String nom) {
        this.nom = nom;
        this.jeux = new HashSet<>();
    }
    
     public static Collection<CategorieDTO> fromBusiness(Collection<Categorie> businessObjects) {
        Collection<CategorieDTO> tmp = new HashSet<>();
        for (Categorie businessObject : businessObjects) {
            tmp.add(new CategorieDTO(businessObject));
        }
        
        return tmp;
    }
     
     public Long getId(){
         return this.id;
     }
     
    public String getNom() {
        return nom;
    }

    public Collection<JeuDTO> getJeux() {
        return jeux;
    }

    public boolean addJeux(JeuDTO jeu) {
        return this.jeux.add(jeu);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.jeux);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategorieDTO other = (CategorieDTO) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.jeux, other.jeux)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return new StringBuilder("Categorie{").append("id=").append(id)
                .append(", nom=").append(nom).append(", jeux=").append(jeux)
                .append('}')
                .toString();
    }
    
    
    
    
}
