/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.business.remote.JeuManagerRemote;
import fr.imag.business.remote.JeuManagerRemote.Element;
import fr.imag.business.remote.JeuManagerRemote.Sens;
import fr.imag.dao.remote.IntRemoteCategorieDAO;
import fr.imag.dao.remote.IntRemoteEditeurDAO;
import fr.imag.dao.remote.IntRemoteJeuDAO;
import fr.imag.dao.remote.IntRemotePlateformeDAO;
import fr.imag.dao.remote.IntRemotePrixJeuDAO;
import fr.imag.entities.Categorie;
import fr.imag.entities.Editeur;
import fr.imag.entities.Jeu;
import fr.imag.entities.Plateforme;
import fr.imag.entities.PrixJeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author seb
 */
@ManagedBean(name = "aSearchBean")
@SessionScoped
public class AdvancedSearchBean implements Serializable {

    private Map<Long, CategorieBean> cat;
    private Map<Long, EditeurBean> edit;
    private Map<Long, PlateformeBean> plat;
    private Collection<Jeu> cjeu;
    private int prixMin;
    private int prixMax;
    private String searchTerm;
    private Element e;
    private Sens s;

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

    @EJB
    private JeuManagerRemote jeuMan;

    @PostConstruct
    public void init() {
        this.e = Element.Defaut;
        this.s = Sens.Decroissant;
        this.searchTerm = null;
        cjeu = null;
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
        while (i.hasNext()) {
            Categorie c = i.next();
            cat.put(c.getId(), new CategorieBean(c));
        }
    }

    private void getAllEditeur() {
        Collection<Editeur> ce = editDAO.findAll();
        Iterator<Editeur> i = ce.iterator();
        while (i.hasNext()) {
            Editeur e = i.next();
            edit.put(e.getId(), new EditeurBean(e));
        }
    }

    private void getAllPlateforme() {
        Collection<Plateforme> cp = platDAO.findAll();
        Iterator<Plateforme> i = cp.iterator();
        while (i.hasNext()) {
            Plateforme p = i.next();
            plat.put(p.getId(), new PlateformeBean(p));
        }
    }

    public Collection<CategorieBean> getCategorie() {
        return new ArrayList<>(cat.values());
    }

    public Collection<EditeurBean> getEditeur() {
        return new ArrayList<>(edit.values());
    }

    public Collection<PlateformeBean> getPlateforme() {
        return new ArrayList<>(plat.values());
    }

    public Double getPrixMax() {
        Double pj =  prixDAO.getMaxPrix();
        return pj;
    }

    private void getPrix() {
        prixMin = 0;
        prixMax = getPrixMax().intValue();
    }

    public int getMinValue() {
        return this.prixMin;
    }

    public void setMinValue(int value) {
        this.prixMin = value;
    }

    public int getMaxValue() {
        return this.prixMax;
    }

    public void setMaxValue(int value) {
        this.prixMax = value;
    }
    
    public String note(Jeu j){
       Float note = jeuMan.getAverageNote(j);
        return "0";
    }
    
    public int nbSell(Jeu j){
        return jeuMan.getNbSell(j).size();
    }

    private Collection<String> getStringCategorie() {
        Collection<CategorieBean> cc = getCategorie();
        ArrayList<String> s = new ArrayList<>();
        Iterator<CategorieBean> i = cc.iterator();
        while (i.hasNext()) {
            CategorieBean cb = i.next();
            if (cb.getValue()) {
                s.add(cb.getName());
            }
        }
        return s;
    }

    private Collection<String> getStringPlateforme() {
        Collection<PlateformeBean> cp = getPlateforme();
        ArrayList<String> s = new ArrayList<>();
        Iterator<PlateformeBean> i = cp.iterator();
        while (i.hasNext()) {
            PlateformeBean pb = i.next();
            if (pb.getValue()) {
                s.add(pb.getName());
            }
        }
        return s;
    }

    private Collection<String> getStringEditeur() {
        Collection<EditeurBean> ce = getEditeur();
        ArrayList<String> s = new ArrayList<>();
        Iterator<EditeurBean> i = ce.iterator();
        while (i.hasNext()) {
            EditeurBean eb = i.next();
            if (eb.getValue()) {
                s.add(eb.getName());
            }
        }
        return s;
    }

    public Collection<Jeu> getCachedJeu() {
        return cjeu;
    }

    public Collection<Jeu> getSearchResult() {
        cjeu = jeuDAO.Search(getStringCategorie(), getStringEditeur(), getStringPlateforme(), prixMin, prixMax);
        if (this.isSearching()) {
            cjeu = getSearchGame(cjeu);
        }
        jeuMan.setElement(e);
        jeuMan.setSens(s);
        return jeuMan.orderBy(cjeu);

    }

    public Collection<Jeu> getSearchGame(Collection<Jeu> cjeu) {
        HashSet<Jeu> toReturn = new HashSet<>();
        List<String> queryParts = Arrays.asList(searchTerm.split((" ")));

        for (Jeu jeu : cjeu) {
            for (String term : queryParts) {
                if (jeu.getNom().toLowerCase().contains(term.toLowerCase())) {
                    toReturn.add(jeu);
                }
            }
        }

        return new ArrayList<>(toReturn);
    }

    public boolean isSearching() {
        return (searchTerm != null && !searchTerm.isEmpty());
    }

    public void updateSearchTerm(ActionEvent event) {
        String s = (String) event.getComponent().getAttributes().get("search");
        if (s != null && !s.isEmpty()) {
            this.init();
            this.searchTerm = s;
        } else {
            this.searchTerm = null;
        }
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public void removeSearchTerm() {
        this.searchTerm = null;
    }
    
    public void orderByNom(){
        if (this.e == Element.Defaut){
            if (this.s == Sens.Croissant){
                this.s = Sens.Decroissant;
            } else {
                this.s = Sens.Croissant;
            }
        }else{
            this.e = Element.Defaut;
            this.s = Sens.Croissant;
        }
    }
    
    public void orderByAnnee(){
        if (this.e == Element.Annee){
            if (this.s == Sens.Croissant){
                this.s = Sens.Decroissant;
            } else {
                this.s = Sens.Croissant;
            }
        }else{
            this.e = Element.Annee;
            this.s = Sens.Croissant;
        }
    }
    
    public void orderByPrix(){
        if (this.e == Element.Prix){
            if (this.s == Sens.Croissant){
                this.s = Sens.Decroissant;
            } else {
                this.s = Sens.Croissant;
            }
        }else{
            this.e = Element.Prix;
            this.s = Sens.Croissant;
        }
    }
    
    public void orderByNote(){
        if (this.e == Element.Note){
            if (this.s == Sens.Croissant){
                this.s = Sens.Decroissant;
            } else {
                this.s = Sens.Croissant;
            }
        }else{
            this.e = Element.Note;
            this.s = Sens.Croissant;
        }
    }
}
