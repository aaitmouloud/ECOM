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
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

/**
 * Définit une plate-forme de jeu.
 *
 * @author aaitmouloud
 */
@Entity
public class Plateforme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 25, nullable = false, updatable = false)
    private String nom;

    @Column(nullable = true, updatable = true)
    @Lob
    private byte[] image;

    @ManyToMany(mappedBy = "plateformes", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Jeu> jeux;

    /**
     *
     */
    public Plateforme() {
        super();
    }

    /**
     *
     * @param nom
     * @param image
     * @param jeux
     */
    public Plateforme(String nom, byte[] image, Collection<Jeu> jeux) {
        this.nom = nom;
        this.image = image;

        this.jeux = new HashSet<>();
        if (jeux != null) {
            for (Jeu jeu : jeux) {
                jeu.addPlateforme((this));
            }
        }
    }

    /**
     *
     * @param nom
     * @param image
     */
    public Plateforme(String nom, byte[] image) {
        this(nom, image, null);
    }

    /**
     *
     * @param nom
     */
    public Plateforme(String nom) {
        this(nom, null);
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return
     */
    public byte[] getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     *
     * @return
     */
    public Collection<Jeu> getJeux() {
        return new HashSet<>(jeux);
    }

    /**
     *
     * @param jeu
     * @return
     */
    final boolean addJeu(Jeu jeu) {
        return this.jeux.add(jeu);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Arrays.hashCode(this.image);
        hash = 97 * hash + Objects.hashCode(this.jeux);
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
        final Plateforme other = (Plateforme) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.jeux, other.jeux)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Plateforme{").append("id=")
                .append(id).append(", nom=").append(nom).append(", image=")
                .append(image).append(", ").append(jeux == null ? "aucun" : jeux.size())
                .append(" jeux}").toString();
    }

}
