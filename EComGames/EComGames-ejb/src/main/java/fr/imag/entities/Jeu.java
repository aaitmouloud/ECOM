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
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 * Définit un jeu et toutes les informations qui lui sont reliées.
 *
 * @author aaitmouloud
 */
@Entity
public class Jeu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 40, updatable = false)
    final private String id;

    @Column(length = 100, nullable = false, updatable = false)
    private String nom;

    @Column(nullable = true, length = 255, updatable = true)
    private String description;

    @Column(nullable = false, updatable = false)
    private int annee;

    @Column(nullable = false, updatable = true)
    private int ageMin;

    @Column(nullable = true, updatable = true)
    @Lob
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(joinColumns = {@JoinColumn(nullable = false)}, 
            inverseJoinColumns = {@JoinColumn(nullable = false)})
    private Collection<Plateforme> plateformes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(joinColumns = {@JoinColumn(nullable = false)}, 
            inverseJoinColumns = {@JoinColumn(nullable = false)})
    private Collection<Categorie> categories;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Editeur editeur;

    @OneToMany(mappedBy = "jeu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Cle> cles;

    @OneToMany(mappedBy = "jeu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<PrixJeu> prix;

    /**
     *
     */
    public Jeu() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     *
     * @param nom
     * @param description
     * @param annee
     * @param ageMin
     * @param image
     * @param plateformes
     * @param categories
     * @param editeur
     * @param cles
     * @param prix
     */
    public Jeu(String nom, String description, int annee, int ageMin, byte[] image, Collection<Plateforme> plateformes, Collection<Categorie> categories, Editeur editeur, Collection<Cle> cles, Collection<PrixJeu> prix) {
        this();
        this.nom = nom;
        this.description = description;
        this.annee = annee;
        this.ageMin = ageMin;
        this.image = image;
        this.plateformes = plateformes;
        this.categories = categories;
        this.editeur = editeur;
        this.cles = cles;
        this.prix = prix;
    }

    /**
     *
     * @param nom
     * @param description
     * @param annee
     * @param ageMin
     */
    public Jeu(String nom, String description, int annee, int ageMin) {
        this(nom, description, annee, ageMin, null, new HashSet<Plateforme>(),
                new HashSet<Categorie>(), null, new HashSet<Cle>(),
                new HashSet<PrixJeu>());

    }

    /**
     *
     * @return
     */
    public String getId() {
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
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public int getAnnee() {
        return annee;
    }

    /**
     *
     * @param annee
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     *
     * @return
     */
    public int getAgeMin() {
        return ageMin;
    }

    /**
     *
     * @param ageMin
     */
    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
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
    public Collection<Plateforme> getPlateformes() {
        return plateformes;
    }

    /**
     *
     * @param plateformes
     */
    public void setPlateformes(Collection<Plateforme> plateformes) {
        this.plateformes = plateformes;
    }

    /**
     *
     * @param plateforme
     * @return
     */
    public boolean addPlateforme(Plateforme plateforme) {
        return this.plateformes.add(plateforme);
    }

    /**
     *
     * @return
     */
    public Collection<Categorie> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     */
    public void setCategories(Collection<Categorie> categories) {
        this.categories = categories;
    }

    /**
     *
     * @param categorie
     * @return
     */
    public boolean addCategorie(Categorie categorie) {
        return this.categories.add(categorie);
    }

    /**
     *
     * @return
     */
    public Editeur getEditeur() {
        return editeur;
    }

    /**
     *
     * @param editeur
     */
    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    /**
     *
     * @return
     */
    public Collection<Cle> getCles() {
        return cles;
    }

    /**
     *
     * @param cles
     */
    public void setCles(Collection<Cle> cles) {
        this.cles = cles;
    }

    /**
     *
     * @param cle
     * @return
     */
    public boolean addCle(Cle cle) {
        return this.cles.add(cle);
    }

    /**
     *
     * @return
     */
    public Collection<PrixJeu> getPrix() {
        return prix;
    }

    /**
     *
     * @param prix
     */
    public void setPrix(Collection<PrixJeu> prix) {
        this.prix = prix;
    }

    /**
     *
     * @param prixJeu
     * @return
     */
    public boolean addPrix(PrixJeu prixJeu) {
        return this.prix.add(prixJeu);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + this.annee;
        hash = 59 * hash + this.ageMin;
        hash = 59 * hash + Arrays.hashCode(this.image);
        hash = 59 * hash + Objects.hashCode(this.plateformes);
        hash = 59 * hash + Objects.hashCode(this.categories);
        hash = 59 * hash + Objects.hashCode(this.editeur);
        hash = 59 * hash + Objects.hashCode(this.cles);
        hash = 59 * hash + Objects.hashCode(this.prix);
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
        final Jeu other = (Jeu) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.annee != other.annee) {
            return false;
        }
        if (this.ageMin != other.ageMin) {
            return false;
        }
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.plateformes, other.plateformes)) {
            return false;
        }
        if (!Objects.equals(this.categories, other.categories)) {
            return false;
        }
        if (!Objects.equals(this.editeur, other.editeur)) {
            return false;
        }
        if (!Objects.equals(this.cles, other.cles)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
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
        return new StringBuilder("Jeu{").append("id=").append(id)
                .append(", nom=").append(nom).append(", description=")
                .append(description).append(", annee=").append(annee)
                .append(", ageMin=").append(ageMin).append(", plateformes=")
                .append(plateformes).append(", categories=").append(categories)
                .append(", editeur=").append(editeur).append('}')
                .toString();
    }

}
