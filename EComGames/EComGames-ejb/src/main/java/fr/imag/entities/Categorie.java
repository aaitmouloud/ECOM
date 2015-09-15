/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author min
 */
@Entity
@NamedQueries({
    @NamedQuery(name="GetCategorieByJeuId", query="SELECT j.categories FROM Jeu j WHERE j.id = :id"),
    @NamedQuery(name="GetAllCategorie", query="SELECT c FROM Categorie c ORDER BY c.nom")
})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 25, nullable = false, updatable = false)
    private String nom;
    
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Jeu> jeux;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
        this.jeux = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Collection<Jeu> getJeux() {
        return new HashSet<>(jeux);
    }

    public boolean addJeu(Jeu jeu) {
        return this.jeux.add(jeu);
    }
    
    final boolean removeJeu(Jeu jeu) {
   
        if (jeu == null) {
            return false;
        }

        Iterator<Jeu> ite = jeux.iterator();
        Jeu j;
        while (ite.hasNext()) {
            j = ite.next();
            if (j.equals(jeu) || j.getId().equals(jeu.getId())) {
                ite.remove();
                return true;
            }
        }
        return false;
    
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
        final Categorie other = (Categorie) obj;
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
                .append(", nom=").append(nom).append(",")
                .append(jeux==null?"aucun":jeux.size()).append(" jeux}")
                .toString();
    }

    
}
