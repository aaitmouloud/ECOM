/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.Editeur;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * Définit un éditeur de jeux-vidéo.
 *
 * @author aaitmouloud
 */
public class EditeurDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nom;
    private String description;
    private byte[] logo;
    private Collection<JeuDTO> jeux;

    public EditeurDTO(Editeur e) {
        this(e.getId(), e.getNom(), e.getDescription(), e.getLogo(),
                JeuDTO.fromBusiness(e.getJeux()));
    }

    public EditeurDTO(Long id, String nom, String description, byte[] logo, Collection<JeuDTO> jeux) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.logo = logo;
        this.jeux = jeux;
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

    public Collection<JeuDTO> getJeux() {
        return jeux;
    }

    public boolean addJeu(JeuDTO jeu) {
        return this.jeux.add(jeu);
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
        final EditeurDTO other = (EditeurDTO) obj;
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
                .append(", ").append(jeux.size()).append(" jeux").append('}')
                .toString();
    }

}
