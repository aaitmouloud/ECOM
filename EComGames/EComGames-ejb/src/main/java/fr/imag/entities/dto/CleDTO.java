/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities.dto;

import fr.imag.entities.Cle;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author aaitmouloud
 */
public class CleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cle;
    private JeuDTO jeu;
    private AchatDTO achat;

    /**
     *
     * @param c
     */
    public CleDTO(Cle c) {
        this(c.getCle(), new JeuDTO(c.getJeu()), new AchatDTO(c.getAchat()));
    }

    /**
     *
     * @param cle
     * @param jeu
     * @param achat
     */
    public CleDTO(String cle, JeuDTO jeu, AchatDTO achat) {
        this.cle = cle;
        this.jeu = jeu;
        this.achat = achat;
    }

    /**
     *
     * @param cle
     * @param jeu
     */
    public CleDTO(String cle, JeuDTO jeu) {
        this(cle, jeu, null);
    }

    public static Collection<CleDTO> fromBusiness(Collection<Cle> businessObjects) {
        Collection<CleDTO> tmp = new HashSet<>();
        for (Cle businessObject : businessObjects) {
            tmp.add(new CleDTO(businessObject));
        }

        return tmp;
    }

    /**
     *
     * @return
     */
    public String getCle() {
        return cle;
    }

    /**
     *
     * @return
     */
    public JeuDTO getJeu() {
        return jeu;
    }

    /**
     *
     * @return
     */
    public AchatDTO getAchat() {
        return achat;
    }

    /**
     *
     * @param achat
     */
    public void setAchat(AchatDTO achat) {
        this.achat = achat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cle);
        hash = 31 * hash + Objects.hashCode(this.jeu);
        hash = 31 * hash + Objects.hashCode(this.achat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CleDTO other = (CleDTO) obj;
        if (!Objects.equals(this.cle, other.cle)) {
            return false;
        }
        if (!Objects.equals(this.jeu, other.jeu)) {
            return false;
        }
        if (!Objects.equals(this.achat, other.achat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder("Cle{").append("cle=").append(cle)
                .append(", jeu=").append(jeu).append(", achat=").append(achat)
                .append('}')
                .toString();
    }

}
