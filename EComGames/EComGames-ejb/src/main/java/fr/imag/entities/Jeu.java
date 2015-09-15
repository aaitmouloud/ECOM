/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Définit un jeu et toutes les informations qui lui sont reliées.
 *
 * @author aaitmouloud
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "GetJeuByCleId", query = "SELECT c.cle FROM Cle c WHERE c.cle = :id"),
    @NamedQuery(name = "GetJeuByEditeurId", query = "SELECT e.jeux FROM Editeur e WHERE e.id = :id"),
    @NamedQuery(name = "GetJeuByCategorieId", query = "SELECT c.jeux FROM Categorie c WHERE c.id = :id"),
    @NamedQuery(name = "GetJeuByPlateformeId", query = "SELECT p.jeux FROM Plateforme p WHERE p.id = :id"),
    @NamedQuery(name = "GetAllJeu", query = "SELECT j FROM Jeu j"),
})
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
    @JoinTable(joinColumns = {
        @JoinColumn(nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(nullable = false)})
    private Collection<Plateforme> plateformes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(joinColumns = {
        @JoinColumn(nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(nullable = false)})
    private Collection<Categorie> categories;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Editeur editeur;

    @OneToMany(mappedBy = "jeu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Cle> cles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<PrixJeu> prix;

    @Column(nullable = false)
    private String url;

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
    public Jeu(String nom, String description, int annee, int ageMin, byte[] image, Collection<Plateforme> plateformes, Collection<Categorie> categories, Editeur editeur, Collection<Cle> cles, Collection<PrixJeu> prix, String url) {
        this();
        this.nom = nom;
        this.description = description;
        this.annee = annee;
        this.ageMin = ageMin;
        this.image = image;
        this.plateformes = new HashSet<>();
        if (plateformes != null) {
            for (Plateforme p : plateformes) {
                this.addPlateforme(p);
            }
        }
        this.categories = new HashSet<>();
        if (categories != null) {
            for (Categorie c : categories) {
                this.addCategorie(c);
            }
        }
        this.cles = new HashSet<>();
        if (cles != null) {
            for (Cle c : cles) {
                this.addCle(c);
            }
        }
        this.prix = new HashSet<>();
        if (prix != null) {
            for (PrixJeu p : prix) {
                this.addPrix(p);
            }
        }
        this.setEditeur(editeur);
        this.url = url;
    }

    /**
     *
     * @param nom
     * @param description
     * @param annee
     * @param ageMin
     * @param url
     */
    public Jeu(String nom, String description, int annee, int ageMin, String url) {
        this(nom, description, annee, ageMin, null, new HashSet<Plateforme>(),
                null, null, null, null, url);

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
        return new ArrayList<>(plateformes);
    }

    public boolean removePlateforme(Plateforme p) {
        if (p == null) {
            return false;
        }

        Iterator<Plateforme> ite = plateformes.iterator();
        Plateforme pl;
        while (ite.hasNext()) {
            pl = ite.next();
            if (pl.equals(p) || pl.getId().equals(p.getId())) {
                ite.remove();
                p.removeJeu(this);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param plateforme
     * @return
     */
    final public boolean addPlateforme(Plateforme plateforme) {
        if (plateforme == null) {
            return false;
        }

        return plateforme.addJeu(this) && this.plateformes.add(plateforme);
    }

    /**
     *
     * @return
     */
    public Collection<Categorie> getCategories() {
        return new ArrayList<>(categories);
    }

    /**
     *
     * @param categorie
     * @return
     */
    final public boolean addCategorie(Categorie categorie) {
        if (categorie == null) {
            return false;
        }

        return categorie.addJeu(this) && this.categories.add(categorie);
    }

    public boolean removeCategorie(Categorie c) {
        if (c == null) {
            return false;
        }

        Iterator<Categorie> ite = categories.iterator();
        Categorie cat;
        while (ite.hasNext()) {
            cat = ite.next();
            if (cat.equals(c) || cat.getId().equals(c.getId())) {
                ite.remove();
                c.removeJeu(this);
                return true;
            }
        }
        return false;
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
     * @return
     */
    final public boolean setEditeur(Editeur editeur) {
        if (this.editeur != null) {
            this.editeur.removeJeu(this.getId());
        }

        this.editeur = editeur;

        return this.editeur == null || this.editeur.addJeu(this);
    }

    /**
     *
     * @return
     */
    public Collection<Cle> getCles() {
        return new HashSet<>(cles);
    }

    /**
     *
     * @param cle
     * @return
     */
    final public boolean addCle(Cle cle) {
        if (cle == null || cle.getJeu() != null) {
            return false;
        }

        if (this.cles.add(cle)) {
            cle.setJeu(this);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public Collection<PrixJeu> getPrix() {
        return new HashSet<>(prix);
    }

    /**
     *
     * @param pri
     * @return
     */
    final public boolean addPrix(PrixJeu pri) {
        return this.prix.add(pri);
    }

    final public boolean addCurrentPrix(double prix) {
        Calendar now = Calendar.getInstance();
        PrixJeu last = null;
        for (PrixJeu pj : this.prix) {

            if (pj.getDateDebut().before(now)
                    && (last == null || pj.getDateFin().after(last.getDateFin()))) {
                last = pj;
            }
        }
        if (last != null) {
            last.setDateFin(now);
        }
        return this.addPrix(new PrixJeu(this, now, null, prix));

    }

    public PrixJeu getCurrentPrix() {
        if (this.prix == null || this.prix.isEmpty())
            return null;
        
        Calendar now = Calendar.getInstance();
        
        for (PrixJeu pj : this.prix) {

            if (pj.getDateDebut().before(now)
                    && (pj.getDateFin() == null || pj.getDateFin().after(now))) {
                return pj;
            }
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + this.annee;
        hash = 59 * hash + Objects.hashCode(this.url);
        hash = 59 * hash + this.ageMin;
        hash = 59 * hash + Arrays.hashCode(this.image);
        hash = 31 * hash + Objects.hashCode(this.cles);
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
        if (!Objects.equals(this.url, other.url)) {
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
                .append(", nom=").append(nom).append(", url=").append(url)
                .append(", description=").append(description).append(", annee=")
                .append(annee).append(", ageMin=").append(ageMin)
                .append(",").append(plateformes == null ? "aucune" : plateformes.size())
                .append("plateformes ,").append(categories == null ? "aucune" : categories.size())
                .append("categories , editeur=")
                .append(editeur == null ? "aucun" : editeur.getNom()).append('}')
                .toString();
    }

}
