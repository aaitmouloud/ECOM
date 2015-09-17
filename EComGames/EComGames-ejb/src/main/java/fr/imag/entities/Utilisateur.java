/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import fr.imag.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Définit un utilisateur de l'application.
 *
 * @author aaitmouloud
 */
@Entity
@NamedQueries({
    @NamedQuery(name="GetUserByAchatId", query="SELECT a.utilisateur FROM Achat a WHERE a.id = :id"),
    @NamedQuery(name="GetAllUser", query="SELECT u FROM Utilisateur u"),
    @NamedQuery(name="GetUserByLoginAndPass", query="SELECT u FROM Utilisateur u WHERE (u.nom = :login OR u.email = :login) AND u.hashMdp = :hashMdp"),
    @NamedQuery(name="GetUserByUsername", query="SELECT u FROM Utilisateur u WHERE (u.nom = :login OR u.email = :email)")
})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 25, updatable = true)
    private String nom;

    @Column(length = 100, updatable = true)
    private String hashMdp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Calendar dateNaissance;

    @Column(length = 50, updatable = true)
    private String email;

    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Achat> achats;

    /**
     * Constructeur public par défaut.
     */
    public Utilisateur() {
    }

    /**
     * Crée un utilisateur à partir de ses infomations.
     *
     * @param nom Le nom d'utilisateur.
     * @param mdp Le mot de passe.
     * @param dateNaissance La date de naissance.
     * @param email L'email.
     * @param achats La liste d'achats de l'utilisateur.
     */
    public Utilisateur(String nom, String mdp, Calendar dateNaissance, String email, Collection<Achat> achats) {
        this.nom = nom;
        this.setHashMdp(mdp);
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.achats = new HashSet<>();
        
        if (achats != null) {
            for (Achat a : achats) {
                this.addAchat(a);
            }
        }
    }

    /**
     * Crée un utilisateur à partir de ses infomations.
     *
     * @param nom Le nom d'utilisateur.
     * @param mdp Le mot de passe.
     * @param dateNaissance La date de naissance.
     * @param email L'email.
     */
    public Utilisateur(String nom, String mdp, Calendar dateNaissance, String email) {
        this(nom, mdp, dateNaissance, email, null);
    }
    
    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - getDateNaissance().get(Calendar.YEAR);
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return Identifiant unique de l'utilisateur.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Affecte un nom à l'utilisateur.
     *
     * @param nom Nom à affecter.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le MDP hashé de l'utilisateur.
     *
     * @return Le mot de passe hashé de l'utilisateur.
     */
    public String getHashMdp() {
        return hashMdp;
    }

    /**
     * Hash et affecte un mot de passe à l'utilisateur.
     *
     * @param mdp Mot de passe à hasher et à stocker en base
     */
    public final void setHashMdp(String mdp) {
        this.hashMdp = Util.hashMdp(mdp);
    }

    /**
     * Retourne la date de naissance de l'utilisateur.
     *
     * @return Date de naissance de l'utilisateur.
     */
    public Calendar getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Affecte une date de naissance à l'utilisateur.
     *
     * @param dateNaissance Date de naissance à affecter.
     */
    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Affecte une date de naissance à l'utilisateur.
     *
     * @see #setDateNaissance(java.util.Calendar)
     *
     * @param dateNaissance Date de naissance à affecter.
     */
    public void setDateNaissance(Date dateNaissance) {
        Calendar tmp = Calendar.getInstance();
        tmp.setTime(dateNaissance);
        this.dateNaissance = tmp;
    }

    /**
     * Retourne l'email de l'utilisateur.
     *
     * @return Email de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Affecte un email à l'utilisateur.
     *
     * @param email Email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne la liste des achats de l'utilisateur.
     *
     * @return Liste des achats de l'utilisateur.
     */
    public Collection<Achat> getAchats() {
        return new HashSet<>(achats);
    }
    
    /**
     * Ajoute un achat à l'utilisateur
     *
     * @param achat - L'achat
     * @return Si l'ajout s'est effectué avec succès ou non.
     */
    final public boolean addAchat(Achat achat) {
        if (achat == null)
            return false;
        
        if (this.achats.add(achat)) {
            achat.setUtilisateur(this);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.hashMdp);
        hash = 97 * hash + Objects.hashCode(this.dateNaissance);
        hash = 97 * hash + Objects.hashCode(this.email);
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
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.hashMdp, other.hashMdp)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder("Utilisateur{").append("id=").append(id)
                .append(", nom=").append(nom).append(", hashMdp=")
                .append(hashMdp).append(", dateNaissance=")
                .append(Util.formatCalendar(dateNaissance)).append(", email=").append(email)
                .append(", ").append(achats == null? "aucun" : achats.size()).append(" achats}")
                .toString();
    }

}
