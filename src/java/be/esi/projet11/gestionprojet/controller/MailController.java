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
import be.esi.projet11.gestionprojet.entity.Tache;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author g34771
 */
@ManagedBean(name="mailCtrl")
@RequestScoped
public class MailController {

    @ManagedProperty(value = "#{param.idProjet}")
    private String idProjet;
    @ManagedProperty(value = "#{param.idMembre}")
    private String idMembre;
    @ManagedProperty(value = "#{param.idTache}")
    private String idTache;
    private Membre membre;
    private Projet projet;
    private Tache tache;
    @EJB
    private ProjetEJB projetEJB;
    @EJB
    private MembreEJB membreEJB;
    @EJB
    private TacheEJB tacheEJB;

    /**
     * Get the value of projet
     *
     * @return the value of projet
     */
    public Projet getProjet() {
        return projet;
    }

    public String getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(String idMembre) {
        this.idMembre = idMembre;
    }

    public String getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(String idProjet) {
        this.idProjet = idProjet;
    }
    
    public String getIdTache() {
        return idTache;
    }

    public void setIdTache(String idTache) {
        this.idTache = idTache;
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

    public void ajoutMembreProjet() {
        try {
            long projectlong = Long.parseLong(idProjet);
            long membrelong = Long.parseLong(idMembre);
            projet = projetEJB.getProjetById(projectlong);
            membre = membreEJB.getMembreById(membrelong);
            projet.accepterParticipant(membre);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, "Impossible de convertir les identifiant en long", nfe);
        } catch (Exception e) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, "Erreur dans UserActivation.ajoutMembreProjet", e);
        }
    }

    public void refusAjoutMembreProjet() {
        try {
            long projectlong = Long.parseLong(idProjet);
            long membrelong = Long.parseLong(idMembre);
            projet = projetEJB.getProjetById(projectlong);
            membre = membreEJB.getMembreById(membrelong);
            projetEJB.removeParticipeProjet(projet, membre);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, "Impossible de convertir les identifiant en long", nfe);
        }
//        } catch (Exception e) {
//            Logger.getLogger(UserActivation.class.getName()).log(Level.SEVERE, "Erreur dans UserActivation.refusAjoutMembreProjet", e);
//        }
    }
    
    public void ajoutMembreTache() {
        try {
            long tachelong = Long.parseLong(idTache);
            long membrelong = Long.parseLong(idMembre);
            tache = tacheEJB.getTache(tachelong);
            membre = membreEJB.getMembreById(membrelong);
            tache.accepterMembre(membre);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, "Impossible de convertir les identifiant en long", nfe);
        } catch (Exception e) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, "Erreur dans MailController.ajoutMembreTache", e);
        }
    }
    
    public void refusAjoutMembreTache() {
        try {
            long tachelong = Long.parseLong(idTache);
            long membrelong = Long.parseLong(idMembre);
            tache = tacheEJB.getTache(tachelong);
            membre = membreEJB.getMembreById(membrelong);
            tacheEJB.removeParticipeTache(tache, membre);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, "Impossible de convertir les identifiant en long", nfe);
        }
    }

    /**
     * Creates a new instance of UserActivation
     */
    public MailController() {
    }
}
