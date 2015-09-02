/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Définit un utilisateur de l'application.
 *
 * @author aaitmouloud
 */
@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 25)
    private String nom;
    
    @Column(length = 100)
    private String hashMdp;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateNaissance;
    
    @Column(length = 50)
    private String email;

    public Long getId() {
        return id;
    }

    

}
