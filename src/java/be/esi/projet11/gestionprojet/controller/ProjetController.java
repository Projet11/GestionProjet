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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author g34771
 */
@ManagedBean(name="projetCtrl")
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
        if(projetCourant==null){
            projetCourant= projetEJB.CreerProjet();
        }
        return projetCourant;
    }

    public void setProjetCourant(Projet projetCourant) {
        this.projetCourant = projetCourant;
    }

    public String ajouterMembre(){
        membreEJB.ajoutMembreProjet(email, getProjetCourant());
        membres = projetCourant.getAllParticipant();
        return "ajouter";
    }
}
