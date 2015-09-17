/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import fr.imag.util.Util;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Définit le prix d'un jeu à un moment paritculier
 *
 * @author aaitmouloud
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "GetPrixJeuByJeuId", query = "SELECT j.prix FROM Jeu j WHERE j.id = :id"),
    @NamedQuery(name = "GetAllPrixJeu", query = "SELECT p FROM PrixJeu p"),
    @NamedQuery(name = "GetMaxPrix", query = "SELECT max(p2.prix) FROM PrixJeu p2 WHERE p2.dateFin IS NULL")
})
@Table(name = "PRIX_JEU")
public class PrixJeu implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_DEBUT")
    private Calendar dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_FIN")
    private Calendar dateFin;

    @Column(nullable = false)
    private Double prix;

    /**
     *
     */
    public PrixJeu() {
    }

    /**
     *
     * @param jeu
     * @param dateDebut
     * @param dateFin
     * @param prix
     */
    public PrixJeu(Jeu jeu, Calendar dateDebut, Calendar dateFin, Double prix) {
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu est null");
        }

        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;

        jeu.addPrix((this));
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

    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
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
        final PrixJeu other = (PrixJeu) obj;
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
                .append(", dateDebut=")
                .append(Util.formatCalendar(dateDebut)).append(", dateFin=")
                .append(Util.formatCalendar(dateFin))
                .append(", prix=").append(prix).append('}')
                .toString();
    }

}
