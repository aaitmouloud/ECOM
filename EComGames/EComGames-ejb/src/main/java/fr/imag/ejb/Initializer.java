/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ejb;

import fr.imag.entities.Achat;
import fr.imag.entities.Categorie;
import fr.imag.entities.Cle;
import fr.imag.entities.Editeur;
import fr.imag.entities.Jeu;
import fr.imag.entities.Plateforme;
import fr.imag.entities.PrixJeu;
import fr.imag.entities.Utilisateur;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 * EJB Singleton qui remplit la BD au démarrage. A enelever en prod
 *
 * @author aaitmouloud
 */
@Singleton
@LocalBean
@Startup
public class Initializer {
    
    final static private Logger LOGGER = Logger.getLogger(Initializer.class);
    
    @PersistenceContext(unitName = "EComGamesPU")
    private EntityManager em;
    
    @PostConstruct
    void init() {
        LOGGER.info("Initialisation des données.");
        Editeur editeur = new Editeur("Sega Games", LOGGER.getAllAppenders() + " C'est plus fort que toi", null);

        Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3, "ftp://");
        Cle cle = new Cle(jeu);
        Cle cle2 = new Cle(jeu);
        Cle cle3 = new Cle(jeu);
        PrixJeu prixJeu = new PrixJeu(jeu, Calendar.getInstance(), null,25D);
        PrixJeu prixJeu2 = new PrixJeu(jeu, Calendar.getInstance(), Calendar.getInstance(), 100D);
        Plateforme p = new Plateforme("Playstation 3");
        Plateforme p2 = new Plateforme("PC");
        jeu.setEditeur(editeur);
        jeu.addPlateforme(p);
        em.persist(jeu);
        
        
        Utilisateur user = new Utilisateur("toto", "toto", Calendar.getInstance(), "bla@bla.bel");
        em.persist(user);
        
        user.setEmail("blabla@car.com");
        em.merge(user);

        Achat a = new Achat(user, cle,Calendar.getInstance() , new Short("5"), "Ce jeu est trop bien");
        em.persist(a);
        
        Jeu jeu2 = new Jeu("Mario Bros", "Mario sauve Peach", 1980, 3, "ftp://");
        jeu2.setEditeur(new Editeur("Nintendo", "Forever and ever after", null));
        jeu2.addCategorie(new Categorie("Jeu de plateforme"));
        jeu2.addPlateforme(p);
        jeu2.addPlateforme(p2);
        PrixJeu prixJeu3 = new PrixJeu(jeu2, Calendar.getInstance(), null, 45D);
        Cle cle4 = new Cle(jeu2);
        Cle cle5 = new Cle(jeu2);
        em.persist(jeu2);
        LOGGER.info("Fin de l'initialisation des données.");
    }
}
