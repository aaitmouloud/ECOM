/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Définit un jeu et toutes les informations qui lui sont reliées.
 *
 * @author aaitmouloud
 */
public class JeuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    final private String id;
    private String nom;
    private String description;
    private int annee;
    private int ageMin;
    private String url;
    private byte[] image;
    private Collection<PlateformeDTO> plateformes;
    private Collection<CategorieDTO> categories;
    private EditeurDTO editeur;
    private Collection<CleDTO> cles;
    private Collection<PrixJeuDTO> prix;

    /**
     *
     * @param j
     */
    public JeuDTO(Jeu j) {
        this(j.getId(), j.getNom(), j.getDescription(), j.getAnnee(),
                j.getAgeMin(), j.getImage(),
                PlateformeDTO.fromBusiness(j.getPlateformes()),
                CategorieDTO.fromBusiness(j.getCategories()),
                new EditeurDTO(j.getEditeur()),
                CleDTO.fromBusiness(j.getCles()),
                PrixJeuDTO.fromBusiness(j.getPrix()), j.getUrl());
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
     * @param url
     */
    public JeuDTO(String id, String nom, String description, int annee,
            int ageMin, byte[] image, Collection<PlateformeDTO> plateformes,
            Collection<CategorieDTO> categories, EditeurDTO editeur,
            Collection<CleDTO> cles, Collection<PrixJeuDTO> prix, String url) {
        this.id = id;
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
        this.url = url;
    }

    public static Collection<JeuDTO> fromBusiness(Collection<Jeu> businessObjects) {
        Collection<JeuDTO> tmp = new HashSet<>();
        for (Jeu businessObject : businessObjects) {
            tmp.add(new JeuDTO(businessObject));
        }

        return tmp;
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
    public Collection<PlateformeDTO> getPlateformes() {
        return plateformes;
    }

    /**
     *
     * @param plateformes
     */
    public void setPlateformes(Collection<PlateformeDTO> plateformes) {
        this.plateformes = plateformes;
    }

    /**
     *
     * @param plateforme
     * @return
     */
    public boolean addPlateforme(PlateformeDTO plateforme) {
        return this.plateformes.add(plateforme);
    }

    /**
     *
     * @return
     */
    public Collection<CategorieDTO> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     */
    public void setCategories(Collection<CategorieDTO> categories) {
        this.categories = categories;
    }

    /**
     *
     * @param categorie
     * @return
     */
    public boolean addCategorie(CategorieDTO categorie) {
        return this.categories.add(categorie);
    }

    /**
     *
     * @return
     */
    public EditeurDTO getEditeur() {
        return editeur;
    }

    /**
     *
     * @param editeur
     */
    public void setEditeur(EditeurDTO editeur) {
        this.editeur = editeur;
    }

    /**
     *
     * @return
     */
    public Collection<CleDTO> getCles() {
        return cles;
    }

    /**
     *
     * @param cles
     */
    public void setCles(Collection<CleDTO> cles) {
        this.cles = cles;
    }

    /**
     *
     * @param cle
     * @return
     */
    public boolean addCle(CleDTO cle) {
        return this.cles.add(cle);
    }

    /**
     *
     * @return
     */
    public Collection<PrixJeuDTO> getPrix() {
        return prix;
    }

    /**
     *
     * @param prix
     */
    public void setPrix(Collection<PrixJeuDTO> prix) {
        this.prix = prix;
    }

    /**
     *
     * @param prixJeu
     * @return
     */
    public boolean addPrix(PrixJeuDTO prixJeu) {
        return this.prix.add(prixJeu);
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
        final JeuDTO other = (JeuDTO) obj;
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
                .append(", nom=").append(nom).append(", url=").append(url)
                .append(", description=").append(description).append(", annee=")
                .append(annee).append(", ageMin=").append(ageMin)
                .append(", plateformes=").append(plateformes)
                .append(", categories=").append(categories)
                .append(", editeur=").append(editeur).append('}')
                .toString();
    }

}
