/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import be.esi.projet11.gestionprojet.exception.MailException;
import be.esi.projet11.gestionprojet.exception.ProjetException;
import be.esi.projet11.gestionprojet.mail.Mailer;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author g34771
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Projet.findByNom", query = "SELECT p FROM Projet p WHERE p.nom = :nom"),
    @NamedQuery(name = "Projet.findAll", query = "SELECT p FROM Projet p")})
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nom;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet1")
    private List<ParticipeProjet> participants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    Collection<Tache> listeTaches;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeb;

    public Projet() {
        this(0l, "Projet sans nom", "");
    }

    public Projet(Long id, String nom) {
        this(id, nom, "");
    }

    public Projet(Long id, String nom, String description) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        this.id = id;
        this.nom = nom;
        this.description = description;
        listeTaches = new ArrayList<Tache>();
        participants = new ArrayList<ParticipeProjet>();
        dateDeb = new Date();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Tache> getListeTaches() {
        return listeTaches;
    }

    public void setListeTaches(Collection<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
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

    public ParticipeProjet refuserParticipant(Membre mbr) {
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
                    Logger.getLogger(Projet.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }
        return pp;
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
        System.out.println("all participant");
        List<Membre> membres = new ArrayList<Membre>();
        for (ParticipeProjet pp : participants) {
            System.out.println("membre");
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
    public void ajouterMembre(Membre mbr) throws ProjetException {
        if (!containsMembre(mbr)) {
            participants.add(new ParticipeProjet(mbr, this, false));
            try {
                String objet = "Ajout à un projet";
                String corps = "<html>Vous etes invité à etre ajouté au projet " + nom + ". </br> ";

                corps += "<p><a href='http://localhost:8080/GestionProjet/pages/accepterProjet.xhtml?idMembre=" + mbr.getId() + "&idProjet=" + id + "'>Accepter</a></p>";
                corps += "<p><a href='http://localhost:8080/GestionProjet/pages/refuserProjet.xhtml?idMembre=" + mbr.getId() + "&idProjet=" + id + "'>Refuser</a></p>";
                Mailer.send(mbr.getMail(), objet, corps, true);
            } catch (MailException ex) {
                throw new ProjetException("Impossible d'envoyer un mail à " + mbr.getMail());
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
    
    public String getInformations(){
        DateFormat df=new SimpleDateFormat("dd MMM yyyy");
        return description+" <br/>Créé Le "+df.format(dateDeb);
    }
}
