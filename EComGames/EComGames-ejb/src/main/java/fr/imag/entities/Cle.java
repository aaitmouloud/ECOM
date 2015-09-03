/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author aaitmouloud
 */
@Entity
public class Cle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(updatable = false)
    private String cle;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(nullable = false, updatable = false)
    private Jeu jeu;

    @OneToOne(mappedBy = "cle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = true, updatable = true)
    private Achat achat;

    /**
     *
     */
    public Cle() {
        this.cle = UUID.randomUUID().toString();
    }

    /**
     *
     * @param jeu
     * @param achat
     */
    public Cle(Jeu jeu, Achat achat) {
        this();
        this.jeu = jeu;
        this.achat = achat;
    }

    /**
     *
     * @param jeu
     */
    public Cle(Jeu jeu) {
        this(jeu, null);
    }

    /**
     *
     * @return
     */
    public String getCle() {
        return cle;
    }

    /**
     *
     * @return
     */
    public Jeu getJeu() {
        return jeu;
    }

    /**
     *
     * @return
     */
    public Achat getAchat() {
        return achat;
    }

    /**
     *
     * @param achat
     */
    public void setAchat(Achat achat) {
        this.achat = achat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cle);
        hash = 31 * hash + Objects.hashCode(this.jeu);
        hash = 31 * hash + Objects.hashCode(this.achat);
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
        final Cle other = (Cle) obj;
        if (!Objects.equals(this.cle, other.cle)) {
            return false;
        }
        if (!Objects.equals(this.jeu, other.jeu)) {
            return false;
        }
        if (!Objects.equals(this.achat, other.achat)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return new StringBuilder("Cle{").append("cle=").append(cle)
                .append(", jeu=").append(jeu).append(", achat=").append(achat)
                .append('}')
                .toString();
    }

}
