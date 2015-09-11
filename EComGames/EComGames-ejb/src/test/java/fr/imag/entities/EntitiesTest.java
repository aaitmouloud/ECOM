/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import fr.imag.dao.JeuDAO;
import static org.junit.Assert.*;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.derby.impl.io.VFMemoryStorageFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de l'unité de persistence.
 *
 * @author aaitmouloud
 */
public class EntitiesTest {

    final private static Logger logger = Logger.getLogger(EntitiesTest.class.getName());

    private EntityManagerFactory emFactory;

    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        try {
            logger.info("Starting memory database for unit tests");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
        } catch (ClassNotFoundException | SQLException ex) {
            logger.log(Level.SEVERE, "Exception during database startup.", ex);
            fail("Exception during database startup.");
        }
        try {
            logger.info("BuildingEntityManager for unit tests");
            emFactory = Persistence.createEntityManagerFactory("testPU");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            logger.log(Level.INFO, "Exception during JPA EntityManager instanciation.", ex);
            fail("Exception during JPA EntityManager instanciation.");
        }
    }

    @After
    public void tearDown() {
        try {
            logger.info("Shuting Eclipselink JPA layer.");
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emFactory != null && emFactory.isOpen()) {
                emFactory.close();
            }
            logger.info("Stoppingemory database.");
            try {
                DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
            } catch (SQLNonTransientConnectionException ex) {
                if (ex.getErrorCode() != 45000) {
                    throw ex;
                }
                // Shutdown success
            }
            VFMemoryStorageFactory.purgeDatabase(new File("unit-testing-jpa").getCanonicalPath());
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception during testPersistence", e);
        }
    }

    @Test
    public void testStatelessPersistence() {
        EntityTransaction tx = null;
        try {
            JeuDAO jdao = new JeuDAO();
            jdao.setEntityManager(em);

            Editeur editeur = new Editeur("Sega Games");
            Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3, "http://");
            jeu.setEditeur(editeur);
            Categorie c = new Categorie("RPG");
            Categorie c1 = new Categorie("Action");
            jeu.addCategorie(c);
            jeu.addCategorie(c1);
            jeu.addCurrentPrix(50D);

            assertTrue("Le jeu n'a pas pu être créé", jdao.create(jeu));

            tx = em.getTransaction();
            tx.begin();
            TypedQuery<Jeu> q = em.createQuery("select j from Jeu j", Jeu.class);
            List<Jeu> r = q.getResultList();
            tx.commit();
            assertFalse("Le jeu n'est pas présent ", r.isEmpty());
            Jeu j = r.iterator().next();
            assertTrue("Le jeu a le mauvais prix ", j.getCurrentPrix().getPrix() == 50D);
            assertTrue("Le jeu n'a pas assez de catégories ", j.getCategories().size() == 2);
            
            Query qq = em.createQuery("SELECT j2.id FROM Jeu j2 INNER JOIN j2.categories cc "
                    + "WHERE cc.nom IN :cid GROUP BY j2.id HAVING (COUNT(DISTINCT cc.id) = :csize)");
            qq.setParameter("cid", Arrays.asList("RPG", "Action"));
            qq.setParameter("csize", 2);
            List res = qq.getResultList();
            assertFalse("Aucun résultat", res.isEmpty());
                   
            logger.info("Stop testPersistence");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            } 
            logger.log(Level.INFO, "Exception during testPersistence", ex);
            fail("Exception during testPersistence. " + ex.getMessage());
        }
    }

    //@Test
    public void testPersistence() {
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            Editeur editeur = new Editeur("Sega Games", "C'est plus fort que toi", null);
            Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3, "ftp://");
            jeu.setEditeur(editeur);

            PrixJeu prixJeu = new PrixJeu(jeu, Calendar.getInstance(), null, 25D);
            PrixJeu prixJeu2 = new PrixJeu(jeu, Calendar.getInstance(), null, 77D);

            em.persist(jeu);
            assertTrue("Le prix du jeu n'est pas présent", em.contains(prixJeu));
            assertTrue("Le jeu n'est pas présent", em.contains(jeu));
            assertTrue("L'éditeur n'est pas présent", em.contains(editeur));

            Jeu j1 = em.find(Jeu.class, jeu.getId());
            assertTrue("Le jeu a le mauvais éditeur (1).", j1.getEditeur().equals(editeur));

            assertTrue("L'éditeur n'est pas mis à jour", j1.setEditeur(new Editeur("Nintendo", "Mario et cie.", null)));
            Editeur e = em.find(Editeur.class, editeur.getId());
            assertTrue("L'éditeur n'est pas mis à jour - 2", e.getJeux().isEmpty());

            em.merge(j1);

            Jeu j2 = em.find(Jeu.class, j1.getId());
            assertTrue("Le jeu a le mauvais éditeur (2).", j2.getEditeur().equals(j1.getEditeur()));

            assertTrue("L'éditeur a encore un jeu affecté. " + j1.getId() + " " + editeur.getJeux(), editeur.getJeux().isEmpty());

            Utilisateur user = new Utilisateur("testUser", "monMdp", Calendar.getInstance(), "bla@bla.bel");
            em.persist(user);
            assertTrue("L'utilisateur n'est pas présent", em.contains(user));

            Cle cle = new Cle(jeu);
            em.persist(cle);
            assertTrue("La clé n'est pas présente", em.contains(cle));

            Achat achat = new Achat(user, Calendar.getInstance(), cle);
            em.persist(achat);
            assertTrue("L'achat n'est pas présent", em.contains(achat));
            assertTrue("L'utilisateur ne contient pas l'achat ",
                    user.getAchats().iterator().next().equals(achat));

            final String newEmail = "newemail@google.com";
            user.setEmail(newEmail);
            em.merge(user);
            Utilisateur u = em.find(Utilisateur.class, user.getId());
            assertTrue("L'email n'a pas été mis à jour.", u.getEmail().equals(newEmail));

            em.remove(prixJeu);
            assertFalse("Le 1er prix du jeu est toujours présent.", em.contains(prixJeu));
            assertTrue("Le jeu a disparu après la suppression du 1er prix.", em.contains(jeu));

            em.remove(prixJeu2);
            assertFalse("Le 2e prix du jeu est toujours présent.", em.contains(prixJeu2));
            assertTrue("Le jeu a disparu après la suppression du 2e prix.", em.contains(jeu));

            tx.commit();
            logger.info("Stop testPersistence");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            logger.log(Level.INFO, "Exception during testPersistence", ex);
            fail("Exception during testPersistence. " + ex.getMessage());
        }
    }

    //@Test
    public void toStringTest() {
        Editeur editeur = new Editeur("Sega Games", "C'est plus fort que toi", null);
        Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3, "ftp://");
        Cle cle = new Cle(jeu);
        jeu.setEditeur(editeur);

        PrixJeu prixJeu = new PrixJeu(jeu, Calendar.getInstance(), null, 25D);
        PrixJeu prixJeu2 = new PrixJeu(jeu, Calendar.getInstance(), null, 77D);

        Utilisateur user = new Utilisateur("testUser", "monMdp", Calendar.getInstance(), "bla@bla.bel");

        user.setEmail("blabla@car.com");

        Achat achat = new Achat(user, Calendar.getInstance(), cle);

        Jeu jeu2 = new Jeu("Mario Bros", "Mario sauve Peach", 1980, 3, "ftp://");
        jeu2.setEditeur(new Editeur("Nintendo", "Forever and ever after", null));
        jeu2.addCategorie(new Categorie("Jeu de plateforme"));
        jeu2.addPlateforme(new Plateforme("NES"));

        logger.log(Level.INFO, editeur.toString());
        logger.log(Level.INFO, jeu.toString());
        logger.log(Level.INFO, jeu2.toString());
        logger.log(Level.INFO, achat.toString());
        logger.log(Level.INFO, jeu2.getCategories().iterator().next().toString());
        logger.log(Level.INFO, jeu2.getPlateformes().iterator().next().toString());
        logger.log(Level.INFO, jeu2.getEditeur().toString());
        logger.log(Level.INFO, prixJeu.toString());
        logger.log(Level.INFO, prixJeu2.toString());
        logger.log(Level.INFO, cle.toString());
    }
}
