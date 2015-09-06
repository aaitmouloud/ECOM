/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Définit une plate-forme de jeu.
 *
 * @author aaitmouloud
 */
public class PlateformeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nom;
    private byte[] image;
    private Collection<JeuDTO> jeux;

    /**
     *
     * @param p
     */
    public PlateformeDTO(Plateforme p) {
        this(p.getId(), p.getNom(), p.getImage(), JeuDTO.fromBusiness(p.getJeux()));
    }

    /**
     *
     * @param id
     * @param nom
     * @param image
     * @param jeux
     */
    public PlateformeDTO(Long id, String nom, byte[] image, Collection<JeuDTO> jeux) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.jeux = jeux;
    }

    /**
     *
     * @param id
     * @param nom
     * @param image
     */
    public PlateformeDTO(Long id, String nom, byte[] image) {
        this(id, nom, image, new HashSet<JeuDTO>());
    }

    /**
     *
     * @param id
     * @param nom
     */
    public PlateformeDTO(Long id, String nom) {
        this(id, nom, null);
    }
    
    public static Collection<PlateformeDTO> fromBusiness(Collection<Plateforme> businessObjects) {
        Collection<PlateformeDTO> tmp = new HashSet<>();
        for (Plateforme businessObject : businessObjects) {
            tmp.add(new PlateformeDTO(businessObject));
        }
        
        return tmp;
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
    public Collection<JeuDTO> getJeux() {
        return jeux;
    }

    /**
     *
     * @param jeux
     */
    public void setJeux(Collection<JeuDTO> jeux) {
        this.jeux = jeux;
    }

    /**
     *
     * @param jeu
     * @return
     */
    public boolean addJeu(JeuDTO jeu) {
        return this.jeux.add(jeu);
    }

    /**
     *
     * @param jeux
     * @return
     */
    public boolean addAllJeux(Collection<JeuDTO> jeux) {
        return this.jeux.addAll(jeux);
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
        final PlateformeDTO other = (PlateformeDTO) obj;
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
                .append(image).append(", jeux=").append(jeux).append('}')
                .toString();
    }

}
