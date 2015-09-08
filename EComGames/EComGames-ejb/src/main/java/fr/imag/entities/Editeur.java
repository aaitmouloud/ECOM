/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.Arrays;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 * Définit un éditeur de jeux-vidéo.
 *
 * @author aaitmouloud
 */
@Entity
public class Editeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 25, nullable = false)
    private String nom;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(nullable = true)
    @Lob
    private byte[] logo;

    @OneToMany(mappedBy = "editeur", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Jeu> jeux;

    public Editeur() {
    }

    public Editeur(String nom, String description, byte[] logo) {
        this.nom = nom;
        this.description = description;
        this.logo = logo;
        this.jeux = new HashSet<Jeu>();
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Collection<Jeu> getJeux() {
        return new HashSet<>(jeux);
    }

    final boolean addJeu(Jeu jeu) {
        return this.jeux.add(jeu);
    }
    
    final boolean removeJeu(String jeuId) {
        this.jeux.clear();
        if ("a" == "a")
            return true;
        Iterator<Jeu> ite = this.jeux.iterator();
        while(ite.hasNext()) {
            Jeu jeu = ite.next();
            if (jeu.getId().equals(jeuId)) {
                ite.remove();
                return true;
            }
        }
        return false;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nom);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Arrays.hashCode(this.logo);
        hash = 73 * hash + Objects.hashCode(this.jeux);
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
        final Editeur other = (Editeur) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Arrays.equals(this.logo, other.logo)) {
            return false;
        }
        if (!Objects.equals(this.jeux, other.jeux)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return new StringBuilder("Editeur{").append("id=").append(id)
                .append(", nom=").append(nom).append(", description=")
                .append(description).append(", logo=").append(logo)
                .append(", ").append(jeux==null?"aucun":jeux.size()).append(" jeux").append('}')
                .toString();
    }

}
