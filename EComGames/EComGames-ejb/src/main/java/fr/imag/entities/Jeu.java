/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Définit un jeu et toutes les informations qui lui sont reliées.
 *
 * @author aaitmouloud
 */
@Entity
public class Jeu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nom;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private short annee;

    @Column(nullable = false)
    private short ageMin;

    @Column(nullable = true)
    @Lob
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Jeu_X_Plateforme", joinColumns = {
        @JoinColumn(name = "id_jeu")}, inverseJoinColumns = {
        @JoinColumn(name = "id_plateforme")})
    private Collection<Plateforme> plateformes;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "Jeu_X_Categorie", joinColumns = {
        @JoinColumn(name = "id_jeu")}, inverseJoinColumns = {
        @JoinColumn(name = "id_categorie")})
    private Collection<Categorie> categories;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "editeur_id")
    private Editeur editeur;

}
