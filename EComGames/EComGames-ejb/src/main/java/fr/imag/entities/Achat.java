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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aaitmouloud
 */
@Entity
@NamedQueries({
    @NamedQuery(name="GetAchatByUserId", query="SELECT u.achats FROM Utilisateur u where u.id = :id"),
    @NamedQuery(name="GetAllAchat", query="SELECT a FROM Achat a")
})
public class Achat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Calendar date;

    @Column(nullable = true, updatable = true)
    private Short note;

    @Column(length = 255, nullable = true, updatable = true)
    private String commentaire;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cle_id", nullable = false, updatable = false)
    private Cle cle;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(nullable = false, updatable = false)
    private Utilisateur utilisateur;

    /**
     *
     */
    public Achat() {
    }

    /**
     *
     * @param utilisateur
     * @param cle
     * @param date
     * @param note
     * @param commentaire
     */
    public Achat(Utilisateur utilisateur, Cle cle, Calendar date, Short note, String commentaire) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("L'utilisateur est null");
        }
        if (cle == null) {
            throw new IllegalArgumentException("La clé est null");
        }

        this.date = date;
        this.note = note;
        this.commentaire = commentaire;
        
        cle.setAchat((this));
        utilisateur.addAchat((this));
    }

    /**
     *
     * @param utilisateur
     * @param date
     * @param cle
     */
    public Achat(Utilisateur utilisateur, Calendar date, Cle cle) {
        this(utilisateur, cle, date, null, null);
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
    public Calendar getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public Short getNote() {
        return note;
    }

    /**
     *
     * @param note
     */
    public void setNote(Short note) {
        this.note = note;
    }

    /**
     *
     * @return
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     *
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     *
     * @return
     */
    public Cle getCle() {
        return cle;
    }

    final void setCle(Cle cle) {
        this.cle = cle;
    }

    final void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     *
     * @return
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.date);
        hash = 37 * hash + Objects.hashCode(this.note);
        hash = 37 * hash + Objects.hashCode(this.commentaire);
        hash = 37 * hash + Objects.hashCode(this.cle);
        hash = 37 * hash + Objects.hashCode(this.utilisateur);
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
        final Achat other = (Achat) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.cle, other.cle)) {
            return false;
        }
        if (!Objects.equals(this.utilisateur, other.utilisateur)) {
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
        return new StringBuilder("Achat{").append("id=").append(id)
                .append(", date=").append(date).append(", note=")
                .append(note).append(", commentaire=").append(commentaire)
                .append(", cle=").append(cle).append(", utilisateur=")
                .append(utilisateur).append('}')
                .toString();
    }

}
