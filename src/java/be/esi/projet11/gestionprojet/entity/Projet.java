/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import be.esi.projet11.gestionprojet.exception.MailException;
import be.esi.projet11.gestionprojet.mail.Mailer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author g34771
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Projet.findAll", query = "SELECT p FROM Projet p")})
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic(optional = false)
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet1")
    private List<ParticipeProjet> participants;

    public Projet() {
        participants = new ArrayList<ParticipeProjet>();
        nom = "nom du projet";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<ParticipeProjet> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipeProjet> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projet)) {
            return false;
        }
        Projet other = (Projet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionProjet[ id=" + id + " ]";
    }

    public void accepterParticipant(Membre mbr) {
        if (mbr.getMail() == null || mbr.getMail().equals("")) {
            return;
        }
        ParticipeProjet pp = getParticipeProjet(mbr);
        if (pp != null) {
            pp.setAccepter(true);
        }
    }

    public void refuserParticipant(Membre mbr) {
        ParticipeProjet pp = getParticipeProjet(mbr);
        if (pp != null) {
            participants.remove(pp);
            List<Membre> membres = getParticipantAcceptes();
            String objet = "Refus d'ajout";
            String corps = "Le membre " + mbr.getMail() + " a refusé d'etre ajouté au projet "
                    + nom + ".";
            for (Membre unMembre : membres) {
                try {
                    Mailer.send(unMembre.getMail(), objet, corps);
                } catch (MailException ex) {
                    Logger.getLogger(Projet.class.getName()).log(Level.WARNING, "Impossible d'envoyer un mail a " + unMembre.getMail(), ex);
                }
            }
        }
    }

    public List<Membre> getParticipantAcceptes() {
        List<Membre> membres = new ArrayList<Membre>();
        for (ParticipeProjet pp : participants) {
            if (pp.isAccepter()) {
                membres.add(pp.getMembre());
            }
        }
        return membres;
    }

    public List<Membre> getAllParticipant() {
        List<Membre> membres = new ArrayList<Membre>();
        for (ParticipeProjet pp : participants) {
            membres.add(pp.getMembre());
        }
        return membres;
    }

    /**
     * Ajoute un membre a la liste des participant potentiel et previent par
     * mail les participant actuel
     *
     * @param mbr le nouveau membre
     */
    public void ajouterMembre(Membre mbr) {
        if (!containsMembre(mbr)) {
            participants.add(new ParticipeProjet(mbr, this, false));
            System.out.println("participants size=" + participants.size());
            try {
                String objet = "Ajout a un projet";
                String corps = "<html>Vous etes invité a etre ajouter au projet " + nom + ". </br> ";

                corps += "<p><a href='http://localhost:8080/GestionProjet/faces/pages/accepterProjet.xhtml?idMembre=" + mbr.getId() + "&idProjet=" + id + "'>Accepter</a></p>";
                corps += "<p><a href='http://localhost:8080/GestionProjet/faces/pages/refuserProjet.xhtml?idMembre=" + mbr.getId() + "&idProjet=" + id + "'>Refusser</a></p>";
                Mailer.send(mbr.getMail(), objet, corps, true);
            } catch (MailException ex) {
                Logger.getLogger(Projet.class.getName()).log(Level.WARNING, "Impossible d'envoyer un mail a " + mbr.getMail(), ex);
            }
        }
    }

    private boolean containsMembre(Membre mbr) {
        for (ParticipeProjet pp : participants) {
            if (pp.getMembre().equals(mbr)) {
                return true;
            }
        }
        return false;
    }

    private ParticipeProjet getParticipeProjet(Membre mbr) {
        for (ParticipeProjet pp : participants) {
            if (pp.getMembre().equals(mbr)) {
                return pp;
            }
        }
        return null;
    }
}
