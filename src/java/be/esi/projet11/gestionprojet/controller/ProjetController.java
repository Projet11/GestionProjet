/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
        if (projetCourant != null) {
            projetCourant = projetEJB.refresh(projetCourant);
        }
        return projetCourant;
    }

    public String setProjetCourant(Projet projetCourant) {
        this.projetCourant = projetCourant;
        tacheCtrl.affichageTaches(projetCourant);
        tacheCtrl.setProjetCourant(projetCourant);
        return null;
    }

    public String ajouterMembre() throws BusinessException {
        try {
            membreEJB.ajoutMembreProjet(email, getProjetCourant());
        } catch (DBException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("ajoutMembre", 
                    new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, 
                    "Ajout du membre au projet impossible :  <br/> Email invalide"
                    , ""));
        }
        membres = projetCourant.getAllParticipant();
        return "ajouter";
    }

    public String ajouterMembreProjet() {
        if (getProjetCourant() == null) {
            return null;
        }
        membres = getProjetCourant().getAllParticipant();
        return "ajouteMembre";
    }

    public boolean isCurrentProject(Projet projet) {
        return projet.equals(projetCourant);
    }
}
