/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.PrixJeu;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Définit le prix d'un jeu à un moment paritculier
 *
 * @author aaitmouloud
 */
public class PrixJeuDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private JeuDTO jeu;
    private Calendar dateDebut;
    private Calendar dateFin;
    private Double prix;

    /**
     *
     * @param prixJeu
     */
    public PrixJeuDTO(PrixJeu prixJeu) {
        this(new JeuDTO(prixJeu.getJeu()), prixJeu.getDateDebut(),
                prixJeu.getDateFin(), prixJeu.getPrix());
    }

    /**
     *
     * @param jeu
     * @param dateDebut
     * @param dateFin
     * @param prix
     */
    public PrixJeuDTO(JeuDTO jeu, Calendar dateDebut, Calendar dateFin, Double prix) {
        this.jeu = jeu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
    }

    public static Collection<PrixJeuDTO> fromBusiness(Collection<PrixJeu> businessObjects) {
        Collection<PrixJeuDTO> tmp = new HashSet<>();
        for (PrixJeu businessObject : businessObjects) {
            tmp.add(new PrixJeuDTO(businessObject));
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
    public JeuDTO getJeu() {
        return jeu;
    }

    /**
     *
     * @return
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     *
     * @return
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     *
     * @return
     */
    public Double getPrix() {
        return prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.jeu);
        hash = 11 * hash + Objects.hashCode(this.dateDebut);
        hash = 11 * hash + Objects.hashCode(this.dateFin);
        hash = 11 * hash + Objects.hashCode(this.prix);
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
        final PrixJeuDTO other = (PrixJeuDTO) obj;
        if (!Objects.equals(this.jeu, other.jeu)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder("PrixJeu{").append("id=").append(id)
                .append(", jeu=").append(jeu).append(", dateDebut=")
                .append(dateDebut).append(", dateFin=").append(dateFin)
                .append(", prix=").append(prix).append('}')
                .toString();
    }

}
