/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.dao;

import fr.imag.dao.local.IntLocalJeuDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.entities.Jeu;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author seb
 */
@Stateless
public class JeuDAO extends AbstractDAO implements IntLocalJeuDAO, IntRemoteJeuDAO {

    @Override
    public Collection<Jeu> findAll() {
        try {
            TypedQuery<Jeu> query = em.createNamedQuery("GetAllJeu", Jeu.class);
            return query.getResultList();
            
        } catch (Exception e) {
            throw (new RuntimeException(e));
        }
    }

    @Override
    public Collection<Jeu> Search(Collection<String> c, Collection<String> e, Collection<String> p, double min, double max) {
        ArrayList<Jeu> cjd = new ArrayList<>();
        try {
            
            
            StringBuilder query = new StringBuilder("SELECT j FROM Jeu j");
            
            if (e != null && !e.isEmpty()) {
                query.append(" INNER JOIN j.editeur e");
            }
            
            query.append(" INNER JOIN j.prix p");
            query.append(" WHERE");
            query.append(" p.dateFin IS NULL");
            query.append(" AND p.prix >= :prixMin");
            query.append(" AND p.prix <= :prixMax");
            
            if (c != null && !c.isEmpty()){
                query.append(" AND (j.id IN");
                query.append("(SELECT j2.id FROM Jeu j2");
                query.append(" INNER JOIN j2.categories cc");
                query.append(" WHERE cc.nom IN :cats");
                query.append(" GROUP BY j2.id");
                query.append(" HAVING (COUNT(DISTINCT cc.id) = :catsnb)))");
            }
            
            if (e != null && !e.isEmpty()) {
                query.append(" AND (e.nom IN :edits)");
            }
            
            if (p != null && !p.isEmpty()) {
                query.append(" AND (j.id IN");
                query.append("(SELECT j3.id FROM Jeu j3");
                query.append(" INNER JOIN j3.plateformes plat");
                query.append(" WHERE plat.nom IN :plats))");
            }
            
            
            TypedQuery<Jeu> tquery = em.createQuery(query.toString(), Jeu.class);

    
            tquery.setParameter("prixMin", min);
            tquery.setParameter("prixMax", max);
            if (c != null && !c.isEmpty()) {
                tquery.setParameter("cats", c);
                tquery.setParameter("catsnb", c.size());
            }
            

           if (e != null && !e.isEmpty()) {
               tquery.setParameter("edits", e);
           }

          if (p != null && !p.isEmpty()) {
                tquery.setParameter("plats", p);
           }
            
            return tquery.getResultList();

        } catch (Exception ex) {
            throw new RuntimeException("Erreur lors de l'éxécution de la requete: " + ex.toString());
        }
    }

    @Override
    public Jeu findById(String id) {
        try {
            Jeu j = em.find(Jeu.class, id);
            return j;
        } catch (Exception e) {
            throw (new RuntimeException(e));
        }
    }

}
