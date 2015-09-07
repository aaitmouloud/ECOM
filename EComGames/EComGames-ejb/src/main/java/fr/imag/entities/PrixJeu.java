/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Définit le prix d'un jeu à un moment paritculier
 *
 * @author aaitmouloud
 */
@Entity
@Table(name = "PRIX_JEU", 
        uniqueConstraints = @UniqueConstraint(name = "PRIX_JEU_UNIQUE",
        columnNames = {"ID_JEU", "DATE_DEBUT", "DATE_FIN"}))
public class PrixJeu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ID_JEU", nullable = false)
    private Jeu jeu;

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
        if (jeu == null)
            throw new IllegalArgumentException("Le jeu est null");
         
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
    public Jeu getJeu() {
        return jeu;
    }
    
    final void setJeu(Jeu jeu) {
        this.jeu = jeu;
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
        final PrixJeu other = (PrixJeu) obj;
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
