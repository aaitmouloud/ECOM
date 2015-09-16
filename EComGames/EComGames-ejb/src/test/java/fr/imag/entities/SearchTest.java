/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import fr.imag.business.JeuManager;
import fr.imag.dao.AchatDAO;
import fr.imag.dao.CategorieDAO;
import fr.imag.dao.CleDAO;
import fr.imag.dao.EditeurDAO;
import fr.imag.dao.JeuDAO;
import fr.imag.dao.PlateformeDAO;
import fr.imag.dao.PrixJeuDAO;
import fr.imag.dao.UtilisateurDAO;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.derby.impl.io.VFMemoryStorageFactory;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;

/**
 *
 * @author seb
 */
public class SearchTest {
    final private static Logger logger = Logger.getLogger(SearchTest.class.getName());
    
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
            logger.log(Level.INFO, "Exception during testSearch", e);
        }
    }
    
    //@Test
    public void testSearch(){
        EntityTransaction tx = null;
        try {
            
            JeuDAO jDAO = new JeuDAO();
            AchatDAO aDAO = new AchatDAO();
            CategorieDAO cDAO = new CategorieDAO();
            CleDAO clDAO = new CleDAO();
            EditeurDAO eDAO = new EditeurDAO();
            PlateformeDAO pDAO = new PlateformeDAO();
            PrixJeuDAO prDAO = new PrixJeuDAO();
            UtilisateurDAO uDAO = new UtilisateurDAO();
            JeuManager jM = new JeuManager();
            
            
            jDAO.setEntityManager(em);
            aDAO.setEntityManager(em);
            cDAO.setEntityManager(em);
            clDAO.setEntityManager(em);
            eDAO.setEntityManager(em);
            pDAO.setEntityManager(em);
            prDAO.setEntityManager(em);
            uDAO.setEntityManager(em);
                    
                  
            Jeu jeu = new Jeu("Tekken", "Rien", 2013, 3, "url");
            Jeu jeu2 = new Jeu("Skyrim", "Dragonborn save the world", 2011, 3, "url");
            Achat achat = new Achat();
            Achat achat2 = new Achat();
            Achat achat3 = new Achat();
            Categorie categorie = new Categorie("Action");
            Categorie categorie2 = new Categorie("Combat");
            Categorie categorie3 = new Categorie("RPG");
            Categorie categorie4 = new Categorie("Aventure");
            Cle cle = new Cle();
            Cle cle2 = new Cle();
            Cle cle3 = new Cle();
            Cle cle4 = new Cle();
            Editeur editeur = new Editeur("Sega Games");
            Editeur editeur2 = new Editeur("Bethesda");
            Plateforme plateforme = new Plateforme("Playstation 3");
            Plateforme plateforme2 = new Plateforme("PC");
            PrixJeu prixjeu = new PrixJeu(jeu, Calendar.getInstance(),null, 60.0);
            PrixJeu prixjeu2 = new PrixJeu(jeu2, Calendar.getInstance(),null, 30.0);
            PrixJeu prixjeu3 = new PrixJeu(jeu2, Calendar.getInstance(),Calendar.getInstance(), 120.0);
            Utilisateur utilisateur = new Utilisateur("AVRIL Sébastien", "password", null, "nothing@empty.fr");
            
            jeu.setEditeur(editeur);
            jeu.addCategorie(categorie);
            jeu.addCategorie(categorie2);
            jeu.addCle(cle);
            jeu.addCle(cle2);
            jeu.addPlateforme(plateforme);
            
            jeu2.setEditeur(editeur2);
            jeu2.addCategorie(categorie);
            jeu2.addCategorie(categorie3);
            jeu2.addCategorie(categorie4);
            jeu2.addCle(cle3);
            jeu2.addCle(cle4);
            jeu2.addPlateforme(plateforme);
            jeu2.addPlateforme(plateforme2);
            
            cle.setAchat(achat);
            cle3.setAchat(achat2);
            cle4.setAchat(achat3);
            
            utilisateur.addAchat(achat);
            utilisateur.addAchat(achat2);
            utilisateur.addAchat(achat3);
            
            achat.setNote((short)10);
            achat2.setNote((short)16);
            achat3.setNote((short)12);
            
            assertTrue(jDAO.create(jeu));
            assertTrue(jDAO.create(jeu2));
            
            aDAO.create(achat);
            aDAO.create(achat2);
            aDAO.create(achat3);
            
            cDAO.create(categorie);
            cDAO.create(categorie2);
            cDAO.create(categorie3);
            cDAO.create(categorie4);
            
            clDAO.create(cle);
            clDAO.create(cle2);
            clDAO.create(cle3);
            clDAO.create(cle4);
            
            eDAO.create(editeur);
            eDAO.create(editeur2);
            
            pDAO.create(plateforme);
            pDAO.create(plateforme2);
            
            prDAO.create(prixjeu);
            prDAO.create(prixjeu2);
            prDAO.create(prixjeu3);
            
            uDAO.create(utilisateur);
            
            
            
            assertTrue("Le nombre de vente de Skyrim est faux", jM.getNbSell(jeu2) == 2);
            assertTrue("La note moyenne de Skyrim est fausse", jM.getAverageNote(jeu2) == 14);
            assertTrue("Le prix en cours de Skyrim est faux", jM.getPrix(jeu2) == 30);
            
            tx = em.getTransaction();
            tx.begin();
            
            assertTrue("Le prix en cours de Skyrim est faux", prDAO.getMaxPrix().equals(prixjeu));
            
            Collection<Jeu> cj = jDAO.findAll();
            assertTrue("Aucun jeu trouvé dans la base", !cj.isEmpty());
    
            
//            cj = jM.orderBy(cj, JeuManager.Element.Note, JeuManager.Sens.Croissant);
//            assertTrue("La collection par Note Croissante est vide ",!cj.isEmpty());
//            Jeu top = cj.iterator().next();
//            assertTrue("Le tri par Note Croissante est faux", top.equals(jeu));
//            
//            cj = jM.orderBy(cj, JeuManager.Element.Annee, JeuManager.Sens.Croissant);
//            assertTrue("La collection par Année Croissante est vide ", !cj.isEmpty());
//            top = cj.iterator().next();
//            assertTrue("Le tri par Année Croissante est faux", top.equals(jeu2));
//            
//            cj = jM.orderBy(cj, JeuManager.Element.BestSell, JeuManager.Sens.Croissant);
//            assertTrue("La collection par MeilleurVente Croissante est vide ", !cj.isEmpty());
//            top = cj.iterator().next();
//            assertTrue("Le tri par MeilleurVente Croissante est faux", top.equals(jeu));
//            
//            cj = jM.orderBy(cj, JeuManager.Element.Editeur, JeuManager.Sens.Croissant);
//            assertTrue("La collection par Editeur Croissant est vide ", !cj.isEmpty());
//            top = cj.iterator().next();
//            assertTrue("Le tri par Editeur Croissant est faux", top.equals(jeu2));
//            
//             cj = jM.orderBy(cj, JeuManager.Element.Prix, JeuManager.Sens.Croissant);
//            assertTrue("La collection par Prix Croissant est vide ", !cj.isEmpty());
//            top = cj.iterator().next();
//            assertTrue("Le tri par Prix Croissant est faux", top.equals(jeu2));
//            
//             cj = jM.orderBy(cj, JeuManager.Element.Defaut, JeuManager.Sens.Croissant);
//            assertTrue("La collection par Defaut Croissante est vide ", !cj.isEmpty());
//            top = cj.iterator().next();
//            assertTrue("Le tri par Defaut Croissante est faux", top.equals(jeu2));
//            
            
            
            ArrayList<String> ac = new ArrayList<>();
            ArrayList<String> ae = new ArrayList<>();
            ArrayList<String> ap = new ArrayList<>();
            
            ac.add(categorie2.getNom());
            
            Collection<Jeu> result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche par categorie est fausse " + result.size(),result.size() == 1);
            assertTrue("La recherche par categorie renvoit le mauvais jeu",result.iterator().next().equals(jeu));
            
            ac.remove(categorie2.getNom());
            ac.add(categorie.getNom());
            ac.add(categorie4.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche par multiple categorie est fausse " + result.size(),result.size() == 1);
            assertTrue("La recherche par multiple categorie renvoit le mauvais jeu",result.iterator().next().equals(jeu2));
            
            ac.remove(categorie.getNom());
            ac.remove(categorie4.getNom());
            ae.add(editeur2.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche par editeur est fausse " + result.size(),result.size() == 1);
            assertTrue("La recherche par editeur renvoit le mauvais jeu",result.iterator().next().equals(jeu2));
            
            ae.add(editeur.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche par multiple editeur est fausse " + result.size(),result.size() == 2);
            
            ae.remove(editeur.getNom());
            ae.remove(editeur2.getNom());
            ap.add(plateforme2.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche par plateforme est fausse " + result.size(),result.size() == 1);
            assertTrue("La recherche par plateforme renvoit le mauvais jeu",result.iterator().next().equals(jeu2));
            
            ap.add(plateforme.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche par multiple plateforme est fausse",result.size() == 2);
            
            ap.remove(plateforme.getNom());
            ap.remove(plateforme2.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 30);
            assertTrue("La recherche par prix est fausse " + result.size(),result.size() == 1);
            assertTrue("La recherche par prix renvoit le mauvais jeu",result.iterator().next().equals(jeu2));
            
            result = jDAO.Search(ac, ae, ap, 30, 60);
            assertTrue("La recherche par prix est fausse " + result.size(),result.size() == 2);
            
            result = jDAO.Search(ac, ae, ap, 100, 1200);
            assertTrue("La recherche par prix est fausse " + result.size(), result.isEmpty());
            
            ac.add(categorie.getNom());
            ae.add(editeur.getNom());
            ae.add(editeur2.getNom());
            
            result = jDAO.Search(ac, ae, ap, 0, 120);
            assertTrue("La recherche globale(etape 1) est fausse " + result.size(),result.size() == 2);
            
            
            result = jDAO.Search(ac, ae, ap, 30, 60);
            assertTrue("La recherche globale(etape 2) est fausse " + result.size(),result.size() == 2);
            
            ap.add(plateforme.getNom());
            result = jDAO.Search(ac, ae, ap, 30, 60);
            assertTrue("La recherche globale(etape 4) est fausse " + result.size(),result.size() == 2);
            
            ac.add(categorie2.getNom());
            
            result = jDAO.Search(ac, ae, ap, 30, 60);
            assertTrue("La recherche globale(etape finale) est fausse",result.size() == 1);
            assertTrue("La recherche globale renvoit le mauvais jeu",result.iterator().next().equals(jeu));
            
            tx.commit();
            logger.info("Stop testSearch");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            logger.log(Level.INFO, "Exception during testSearch", ex);
            fail("Exception during testSearch. " + ex.getMessage());
        }
    }
}
