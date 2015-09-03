/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ejb;

import fr.imag.entities.Achat;
import fr.imag.entities.Cle;
import fr.imag.entities.Jeu;
import fr.imag.entities.Utilisateur;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author min
 */
@Singleton
@LocalBean
@Startup
public class Initializer {

    @PersistenceContext(unitName = "EComGamesPU")
    private EntityManager em;

    @PostConstruct
    void init() {
        Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3);
        Utilisateur user = new Utilisateur("testUser", "monMdp", Calendar.getInstance(), "bla@bla.bel");
        Cle cle = new Cle(jeu);
        Achat achat = new Achat(user, Calendar.getInstance(), cle);
        em.persist(jeu);
        em.persist(user);
        em.persist(cle);
        em.persist(achat);
    }
}
