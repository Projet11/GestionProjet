/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author g34771
 */
@ManagedBean(name = "mailCtrl")
@SessionScoped
public class MailController {
//
//    @ManagedProperty(value = "#{param.idProjet}")
//    private String idProjet;
//    @ManagedProperty(value = "#{param.idMembre}")
//    private String idMembre;
    @ManagedProperty("#{membreCtrl}")
    private MembreController membreCtrl;
    private Membre membre;
    private Projet projet;
    private Tache tache;
    @EJB
    private ProjetEJB projetEJB;
    @EJB
    private MembreEJB membreEJB;

    /**
     * Creates a new instance of UserActivation
     */
    public MailController() {
    }

    /**
     * Get the value of projet
     *
     * @return the value of projet
     */
    public Projet getProjet() {
        return projet;
    }

//    public String getIdMembre() {
//        return idMembre;
//    }
//
//    public void setIdMembre(String idMembre) {
//        this.idMembre = idMembre;
//    }
//
//    public String getIdProjet() {
//        return idProjet;
//    }
//
//    public void setIdProjet(String idProjet) {
//        this.idProjet = idProjet;
//    }

    public MembreController getMembreCtrl() {
        return membreCtrl;
    }

    public void setMembreCtrl(MembreController membreCtrl) {
        this.membreCtrl = membreCtrl;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
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
     * Get the value of membre
     *
     * @return the value of membre
     */
    public Membre getMembre() {
        return membre;
    }

    /**
     * Set the value of membre
     *
     * @param membre new value of membre
     */
    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public void ajoutMembreProjet() throws BusinessException {
        try {
            Map<String,String> map=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            long projectlong = Long.parseLong(map.get("idProjet"));
            long membrelong = Long.parseLong(map.get("idMembre"));
            projet = projetEJB.getProjetById(projectlong);
            membre = membreEJB.getMembreById(membrelong);
        } catch (Exception e) {
            //Pas de traitement en cas d'exception car ca veux juste dire
            //que le projet et le membre sont bien initialise
        }
        if (membreCtrl.isAuthenticated()) {
            projetEJB.accepterParticipant(projet, membre);
        }
    }

    public void refusAjoutMembreProjet() throws BusinessException {
        try {
            Map<String,String> map=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            long projectlong = Long.parseLong(map.get("idProjet"));
            long membrelong = Long.parseLong(map.get("idMembre"));
            projet = projetEJB.getProjetById(projectlong);
            membre = membreEJB.getMembreById(membrelong);
            projetEJB.removeParticipeProjet(projet, membre);
        } catch (NumberFormatException nfe) {
            throw new BusinessException("Les identifiants du projet et/ou du membre sont incorrects");
        } catch (Exception e) {
            throw new BusinessException("Impossible de refuser l'ajout du membre au projet :" + e.getMessage());
        }
    }
}
