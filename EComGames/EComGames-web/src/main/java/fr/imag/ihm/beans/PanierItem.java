/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.ihm.beans;

import fr.imag.entities.Jeu;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author seb
 */
public class PanierItem implements Serializable {

    final private Jeu j;
    final private static NumberFormat DOUBLE_FORMAT = new DecimalFormat("#0.00€");
    private int nb;

   

    public PanierItem(Jeu j) {
        this.j = j;
        this.nb = 0;
    }

    public String getId() {
        return j.getId();
    }

    public String getNom() {
        return j.getNom();
    }

    public String getEditeur() {
        if (j.getEditeur() != null) {
            return j.getEditeur().getNom();
        } else {
            return "Editeur inconnu";
        }

    }

    public boolean isPrixNull() {
        return getPrixUnitDouble() <= 0;
    }

    public String getPrixUnit() {
        return DOUBLE_FORMAT.format(getPrixUnitDouble());
    }

    public double getPrixUnitDouble() {
        if (j.getCurrentPrix() != null) {
            return j.getCurrentPrix().getPrix();
        }
        return -1;
    }

    public int getNombre() {
        return nb;
    }

    public void setNombre(int nb) {
        if (nb >= 1) {
            this.nb = nb;
        }
    }

    public String getPrix() {
        return DOUBLE_FORMAT.format(getPrixDouble());
    }

    public double getPrixDouble() {
        return nb * j.getCurrentPrix().getPrix();
    }


}
