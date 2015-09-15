/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;


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
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.derby.impl.io.VFMemoryStorageFactory;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
/**
 * Test des accès en base par les DAO.
 *
 * @author seb
 */
public class DAOTest {
    final private static Logger logger = Logger.getLogger(DAOTest.class.getName());
    
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
            logger.log(Level.INFO, "Exception during testDAO", e);
        }
    }
    
    @Test
    public void testDAO() {
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
            
            
            
            jDAO.setEntityManager(em);
            aDAO.setEntityManager(em);
            cDAO.setEntityManager(em);
            clDAO.setEntityManager(em);
            eDAO.setEntityManager(em);
            pDAO.setEntityManager(em);
            prDAO.setEntityManager(em);
            uDAO.setEntityManager(em);
                    
                  
            Jeu jeu = new Jeu("Legend of Zelda", "Link sauve Zelda", 1990, 3, "url");
            Achat achat = new Achat();
            Categorie categorie = new Categorie("Test");
            Cle cle = new Cle();
            Cle cle2 = new Cle();
            Cle cle3 = new Cle();
            Editeur editeur = new Editeur("Sega Games");
            Plateforme plateforme = new Plateforme("GameCube");
            PrixJeu prixjeu = new PrixJeu(jeu, Calendar.getInstance(),null, 60.0);
            Utilisateur utilisateur = new Utilisateur("AVRIL Sébastien", "password", null, "nothing@empty.fr");
            
            jeu.setEditeur(editeur);
            jeu.addCategorie(categorie);
            jeu.addCle(cle);
            jeu.addCle(cle2);
            jeu.addCle(cle3);
            jeu.addPlateforme(plateforme);
            
            cle.setAchat(achat);
            
            utilisateur.addAchat(achat);
            

            assertTrue("Le jeu n'a pas pu être créé", jDAO.create(jeu));
            assertTrue("L'achat n'a pas pu être créé", aDAO.create(achat));
            assertTrue("La categorie n'a pas pu être créé", cDAO.create(categorie));
            assertTrue("La cle n'a pas pu être créé", clDAO.create(cle));
            assertTrue("L'editeur n'a pas pu être créé", eDAO.create(editeur));
            assertTrue("La plateforme n'a pas pu être créé", pDAO.create(plateforme));
            assertTrue("Le prix n'a pas pu être créé", prDAO.create(prixjeu));
            assertTrue("L'utilisateur n'a pas pu être créé", uDAO.create(utilisateur));
            
            tx = em.getTransaction();
            tx.begin();
            
            TypedQuery<Jeu> qj = em.createQuery("select j from Jeu j", Jeu.class);
            Jeu j = qj.getSingleResult();

            assertFalse("Le jeu n'est pas présent", j == null);
            assertFalse("Le jeu en base est différent "+j, !j.equals(jeu));
            
            TypedQuery<Achat> qa = em.createQuery("select a from Achat a", Achat.class);
            Achat a = qa.getSingleResult();
            
            assertFalse("L'achat n'est pas présent", a == null);
            assertFalse("L'achat en base est différent " + a, !a.equals(achat));
            
            TypedQuery<Categorie> qc = em.createQuery("select c from Categorie c", Categorie.class);
            Categorie c = qc.getSingleResult();
            
            assertFalse("La categorie n'est pas présente", c == null);
            assertFalse("La categorie en base est différente " + c, !c.equals(categorie));
           
            
            TypedQuery<Cle> qcl = em.createQuery("select c from Cle c", Cle.class);
            Collection<Cle> cl = qcl.getResultList();
            
            assertFalse("La cle n'est pas présente", cl.size() == 0);
            //assertFalse("La cle en base est différente " + cl, !cl.equals(cle));
            
            TypedQuery<Editeur> qe = em.createQuery("select e from Editeur e", Editeur.class);
            Editeur e = qe.getSingleResult();
            
            assertFalse("L'editeur n'est pas présent", e == null);
            assertFalse("L'editeur en base est différent " + e, !e.equals(editeur));
            
            TypedQuery<Plateforme> qp = em.createQuery("select p from Plateforme p", Plateforme.class);
            Plateforme p = qp.getSingleResult();
            
            assertFalse("La plateforme n'est pas présente", p == null);
            assertFalse("La plateforme en base est différente " + p, !p.equals(plateforme));
            
            TypedQuery<PrixJeu> qpj = em.createQuery("select p from PrixJeu p", PrixJeu.class);
            PrixJeu pj = qpj.getSingleResult();
            
            assertFalse("Le prix n'est pas présent", pj == null);
            assertFalse("Le prix en base est différent " + pj, !pj.equals(prixjeu));
            
            TypedQuery<Utilisateur> qu = em.createQuery("select u from Utilisateur u", Utilisateur.class);
            Utilisateur u = qu.getSingleResult();
            
            assertFalse("L'utilisateur n'est pas présent", u == null);
            assertFalse("L'utilisateur en base est différent " + u, !u.equals(utilisateur));
           
            Collection<Jeu> rj = jDAO.findAll();
            assertTrue("La fonction FindAll de Jeu est fausse ", !rj.isEmpty());
            
            Collection<Achat> ra = aDAO.findAll();
            assertTrue("La fonction FindAll de Achat est fausse ", !ra.isEmpty());
            
            Collection<Categorie> rc = cDAO.findAll();
            assertTrue("La fonction FindAll de Categorie est fausse ", !rc.isEmpty());
            
            Collection<Cle> rcl = clDAO.findAll();
            assertTrue("La fonction FindAll de clDAO est fausse ",j.getCles().size() == 3);
            
            rcl = clDAO.findAvailableCle(j);
            assertTrue("Le nombre de cle dispo de clDAO est faux " + rcl, rcl.size() == 2);
            
            Collection<Editeur> re = eDAO.findAll();
            assertTrue("La fonction FindAll de Editeur est fausse ", !re.isEmpty());
            
            Collection<Plateforme> rp = pDAO.findAll();
            assertTrue("La fonction FindAll de Plateforme est fausse ", !rp.isEmpty());
            
            Collection<PrixJeu> rpj = prDAO.findAll();
            assertTrue("La fonction FindAll de PrixJeu est fausse ", !rpj.isEmpty());
            
            Collection<Utilisateur> ru = uDAO.findAll();
            assertTrue("La fonction FindAll de Utilisateur est fausse ", !ru.isEmpty());
            
            tx.commit();
            logger.info("Stop testDAO");
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            logger.log(Level.INFO, "Exception during testDAO", ex);
            fail("Exception during testDAO. " + ex.getMessage());
        }
    }
}
