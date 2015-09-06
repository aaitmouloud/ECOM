/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author aaitmouloud
 */
public class AchatDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final short NOTE_MAX = 10;
    private Long id;
    private Calendar date;
    private Short note;
    private String commentaire;
    private CleDTO cle;
    private UtilisateurDTO utilisateur;

    /**
     *
     */
    public AchatDTO(Achat a) {
        this(a.getId(), new UtilisateurDTO(a.getUtilisateur()),
                new CleDTO(a.getCle()), a.getDate(), a.getNote(), a.getCommentaire());
    }

    /**
     *
     * @param utilisateur
     * @param cle
     * @param date
     * @param note
     * @param commentaire
     */
    public AchatDTO(Long id, UtilisateurDTO utilisateur, CleDTO cle, Calendar date, Short note, String commentaire) {
        this.id = id;
        this.date = date;
        this.utilisateur = utilisateur;
        this.note = note;
        this.commentaire = commentaire;
        this.cle = cle;
    }

    /**
     *
     * @param utilisateur
     * @param date
     * @param cle
     */
    public AchatDTO(Long id, UtilisateurDTO utilisateur, Calendar date, CleDTO cle) {
        this(id, utilisateur, cle, date, null, null);
    }
    
    public static Collection<AchatDTO> fromBusiness(Collection<Achat> businessObjects) {
        Collection<AchatDTO> tmp = new HashSet<>();
        for (Achat businessObject : businessObjects) {
            tmp.add(new AchatDTO(businessObject));
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
    public CleDTO getCle() {
        return cle;
    }

    /**
     *
     * @return
     */
    public UtilisateurDTO getUtilisateur() {
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
        final AchatDTO other = (AchatDTO) obj;
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
