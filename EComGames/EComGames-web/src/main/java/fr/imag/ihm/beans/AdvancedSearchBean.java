/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.dao.remote.IntRemoteCategorieDAO;
import fr.imag.dao.remote.IntRemoteEditeurDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.dao.remote.IntRemotePlateformeDAO;
import fr.imag.dao.remote.IntRemotePrixJeuDAO;
import fr.imag.entities.Categorie;
import fr.imag.entities.Editeur;
import fr.imag.entities.Jeu;
import fr.imag.entities.Plateforme;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author seb
 */
@ManagedBean(name = "aSearchBean")
@SessionScoped
public class AdvancedSearchBean implements Serializable {
    private Map<Long,CategorieBean> cat;
    private Map<Long,EditeurBean> edit;
    private Map<Long,PlateformeBean> plat;
    private int prixMin;
    private int prixMax;
    
    @EJB
    private IntRemoteCategorieDAO catDAO;
    
    @EJB
    private IntRemoteEditeurDAO editDAO;
    
    @EJB
    private IntRemotePlateformeDAO platDAO;
    
    @EJB
    private IntRemotePrixJeuDAO prixDAO;
     
    @EJB
    private IntRemoteJeuDAO jeuDAO;
    
    @PostConstruct
    public void init() {
        
        cat = new HashMap<>();
        edit = new HashMap<>();
        plat = new HashMap<>();
        getAllCategorie();
        getAllEditeur();
        getAllPlateforme();
        getPrix();
        
    }

    private void getAllCategorie() {
        Collection<Categorie> cc = catDAO.findAll();
        Iterator<Categorie> i = cc.iterator();
        while (i.hasNext()){
            Categorie c = i.next();
            cat.put(c.getId(), new CategorieBean(c));
        }
    }

    private void getAllEditeur() {
       Collection<Editeur> ce = editDAO.findAll();
       Iterator<Editeur> i = ce.iterator();
        while (i.hasNext()){
            Editeur e = i.next();
            edit.put(e.getId(), new EditeurBean(e));
        }
    }

    private void getAllPlateforme() {
        Collection<Plateforme> cp = platDAO.findAll();  
        Iterator<Plateforme> i = cp.iterator();
        while (i.hasNext()){
            Plateforme p = i.next();
            plat.put(p.getId(), new PlateformeBean(p));
        }
    }
    
    public Collection<CategorieBean> getCategorie(){
        return new ArrayList<>(cat.values());
    }
    
    public Collection<EditeurBean> getEditeur(){
        return new ArrayList<>(edit.values());
    }
     
    public Collection<PlateformeBean> getPlateforme(){
        return new ArrayList<>(plat.values());
    }
    
    public int getPrixMax(){
        return prixDAO.getMaxPrix().getPrix().intValue();
    }

    private void getPrix() {
        prixMin = 0;
        prixMax = getPrixMax();
    }
   
   public int getMin(){
       return this.prixMin;
   }
   
   public void setMin(int min){
       this.prixMin = min;
   }
   
   public int getMax(){
       return this.prixMax;
   }
   
   public void setMax(int max){
       this.prixMax = max;
   }
   
   private Collection<String> getStringCategorie(){
       Collection<CategorieBean> cc = getCategorie();
       ArrayList<String> s = new ArrayList<>();
       Iterator<CategorieBean> i = cc.iterator();
       while (i.hasNext()){
           CategorieBean cb = i.next();
           if (cb.isValue()){
               s.add(cb.getName());
           }
       }
       return s;
   }
   
   private Collection<String> getStringPlateforme(){
       Collection<PlateformeBean> cp = getPlateforme();
       ArrayList<String> s = new ArrayList<>();
       Iterator<PlateformeBean> i = cp.iterator();
       while (i.hasNext()){
           PlateformeBean pb = i.next();
           if (pb.isChecked()){
               s.add(pb.getName());
           }
       }
       return s;
   }
   
   private Collection<String> getStringEditeur(){
       Collection<EditeurBean> ce = getEditeur();
       ArrayList<String> s = new ArrayList<>();
       Iterator<EditeurBean> i = ce.iterator();
       while (i.hasNext()){
           EditeurBean eb = i.next();
           if (eb.isChecked()){
               s.add(eb.getName());
           }
       }
       return s;
   }
   
   public Collection<Jeu> getSearchResult(){
       Collection<Jeu> j = jeuDAO.Search(getStringCategorie(), getStringEditeur(), getStringPlateforme(), prixMin, prixMax);
      
       return j;
       
   }
}
