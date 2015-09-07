/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.Utilisateur;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Définit un DTO d'utilisateur de l'application.
 *
 * @author aaitmouloud
 */
public class UtilisateurDTO implements Serializable {

    private Long id;
    private String nom;
    private String hashMdp;
    private Calendar dateNaissance;
    private String email;
    private Collection<AchatDTO> achats;

    /**
     * Constructeur public par défaut.
     *
     * @param user - Entité utilisateur.
     */
    public UtilisateurDTO(Utilisateur user) {
        this(user.getId(), user.getNom(), user.getHashMdp(),
                user.getDateNaissance(), user.getEmail(),
                AchatDTO.fromBusiness(user.getAchats()));
    }

    /**
     * Crée un utilisateur à partir de ses infomations.
     *
     * @param id L'identifiant de l'utilisateur.
     * @param nom Le nom d'utilisateur.
     * @param hashMdp Le mot de passe hashé.
     * @param dateNaissance La date de naissance.
     * @param email L'email.
     * @param achats La liste d'achats de l'utilisateur.
     */
    public UtilisateurDTO(Long id, String nom, String hashMdp, Calendar dateNaissance, String email, Collection<AchatDTO> achats) {
        this.id = id;
        this.nom = nom;
        this.hashMdp = hashMdp;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.achats = achats;
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
    public void setHashMdp(String mdp) {
        this.hashMdp = hashMdp(mdp);
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
    public Collection<AchatDTO> getAchats() {
        return achats;
    }

    /**
     * Ajoute un achat à l'utilisateur
     *
     * @param achat - L'achat
     * @return Si l'ajout s'est effectué avec succès ou non.
     */
    public boolean addAchat(AchatDTO achat) {
        return this.achats.add(achat);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.hashMdp);
        hash = 97 * hash + Objects.hashCode(this.dateNaissance);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.achats);
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
        final UtilisateurDTO other = (UtilisateurDTO) obj;
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
        if (!Objects.equals(this.achats, other.achats)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder("Utilisateur{").append("id=").append(id)
                .append(", nom=").append(nom).append(", hashMdp=")
                .append(hashMdp).append(", dateNaissance=")
                .append(dateNaissance).append(", email=").append(email)
                .append(", achats=").append(achats).append('}')
                .toString();
    }

    public static String hashMdp(String mdp) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(mdp.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException ex) {
           throw new RuntimeException(ex);
        }

    }
}
