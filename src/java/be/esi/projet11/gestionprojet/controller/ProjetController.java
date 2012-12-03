/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author g34771
 */
@ManagedBean(name = "projetCtrl")
@SessionScoped
public class ProjetController {
    //Devra etre instancier lors de la selection d'un projet

    private Projet projetCourant;
    private String email;
    private Collection<Membre> membres;
    @EJB
    private MembreEJB membreEJB;
    @EJB
    private ProjetEJB projetEJB;
    private List<Projet> projets;
    @ManagedProperty("#{tacheCtrl}")
    private TacheController tacheCtrl;

    public TacheController getTacheCtrl() {
        return tacheCtrl;
    }

    public void setTacheCtrl(TacheController tacheCtrl) {
        this.tacheCtrl = tacheCtrl;
    }
    /**
     * Get the value of projets
     *
     * @return the value of projets
     */
    public List<Projet> getProjets() {
        if (projets == null) {
            projets = projetEJB.getAllProjets();
        }
        return projets;
    }

    /**
     * Set the value of projets
     *
     * @param projets new value of projets
     */
    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    /**
     * Creates a new instance of ProjetControl
     */
    public ProjetController() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Membre> getMembres() {
        return membres;
    }

    public void setMembres(Collection<Membre> membres) {
        this.membres = membres;
    }

    public Projet getProjetCourant() {
        if (projetCourant == null) {
            projetCourant = projetEJB.creerProjet();
        }
        return projetCourant;
    }

    public String setProjetCourant(Projet projetCourant) {
        this.projetCourant = projetCourant;
        tacheCtrl.affichageTaches(projetCourant);
        return null;
    }

    public String ajouterMembre() {
        membreEJB.ajoutMembreProjet(email, getProjetCourant());
        membres = projetCourant.getAllParticipant();
        return "ajouter";
    }
}
