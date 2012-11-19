/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Projet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author g34754
 */
@ManagedBean(name="ProjetManage")
@SessionScoped
public class ProjetManage {

    private Projet projet;

    /**
     * Get the value of projet
     *
     * @return the value of projet
     */
    public Projet getProjet() {
        return projet;
    }

    /**
     * Set the value of projet
     *
     * @param projet new value of projet
     */
    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    /**
     * Creates a new instance of ProjetManage
     */
    public ProjetManage() {
    }
    
    
}
