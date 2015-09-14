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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Définit une clé de jeu-vidéo dans la base de données.
 *
 * @author aaitmouloud
 */
@Entity
@NamedQueries({
    @NamedQuery(name="GetCleByAchatId", query="SELECT a.cle FROM Achat a WHERE a.id = :id"),
    @NamedQuery(name="GetCleByJeuId", query="SELECT j.cles FROM Jeu j WHERE j.id = :id"),
    @NamedQuery(name="GetAllCle", query="SELECT c FROM Cle c"),
        @NamedQuery(name="GetAvailableCleByJeu", query="SELECT COUNT(c.cle) FROM Cle c INNER JOIN c.jeu j WHERE j.id = :id GROUP BY c.cle HAVING (c.achat IS NULL)")
})
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
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu est null");
        }

        this.achat = achat;
        jeu.addCle((this));
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

    final void setJeu(Jeu jeu) {
        this.jeu = jeu;
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
     * @return
     */
    public boolean setAchat(Achat achat) {
        if (achat == null || achat.getCle() != null) {
            return false;
        }

        achat.setCle(this);
        this.achat = achat;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cle);
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
                .append(", jeu=").append(jeu == null ? "aucun" : jeu.getNom())
                .append(", achat=").append(achat == null ? "aucun" : achat.getId())
                .append('}')
                .toString();
    }

}
