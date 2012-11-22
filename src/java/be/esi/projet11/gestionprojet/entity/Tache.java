/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.MailException;
import be.esi.projet11.gestionprojet.exception.TacheException;
import be.esi.projet11.gestionprojet.mail.Mailer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author g34840
 */
@Entity
@Table(name = "TACHE")
@NamedQueries({
    @NamedQuery(name = "Tache.findByNom", query = "SELECT t FROM Tache t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tache.findAll", query = "SELECT t FROM Tache t")})
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Tache")
    @TableGenerator(name = "Tache", allocationSize = 1)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nom;
    @Column(nullable = false)
    private ImportanceEnum importance;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Byte pourcentage;
    private Long revision;
    private char timerLaunched;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeb;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tempsPasseSurTache;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ParticipeTache> membres;
    @Basic(optional = false)
    @Column(name = "PROJET")
    @ManyToOne
    private Projet projet; // TODO: établir un lien entre projet et tâche avec un ManyToOne comme pour membres

    public Tache() throws TacheException {
        this("<nomInexistant>", "<descriptionInexistante>");
        this.timerLaunched = '0';
        membres = new ArrayList<ParticipeTache>();
        projet = new Projet();
    }

    public Tache(String nom, String description) throws TacheException {
        if (nom == null || nom.equals("")) {
            throw new TacheException("Le nom d'une tâche ne peut pas être vide");
        }
        this.id=0l;
        this.nom = nom;
        this.description = description;
        this.importance = ImportanceEnum.NORMALE;
        this.pourcentage = 0;
        this.revision = null;
        membres = new ArrayList<ParticipeTache>();
        projet = new Projet();
    }

    public Tache(String nom, String description, ImportanceEnum importance) throws TacheException {
        this(nom, description);
        this.importance = importance;
    }

    /**
     * Get the value of pourcentage
     *
     * @return the value of pourcentage
     */
    public Byte getPourcentage() {
        return pourcentage;
    }

    /**
     * Set the value of pourcentage
     *
     * @param pourcentage est le pourcentage ex: 50% = 50
     */
    public void setPourcentage(Byte pourcentage) throws TacheException {
        if (pourcentage < 0 || pourcentage > 100) {
            throw new TacheException("Le pourcentage doit être compris entre 0 et 10000(=100%)");
        }
        this.pourcentage = pourcentage;

    }

    public void setPourcentage(int pourcentage) throws TacheException {
        this.setPourcentage(new Byte((byte) pourcentage));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tache)) {
            return false;
        }
        Tache other = (Tache) object;
        if (this.nom.equals(other.nom) || this.id == other.id) {
            return true;
        }
        return false;
    }

    public ImportanceEnum getImportance() {
        return importance;
    }

    public void setImportance(ImportanceEnum importance) {
        this.importance = importance;
    }

    /**
     * Get the value of revision
     *
     * @return the value of revision
     */
    public Long getSVNRevision() {
        return revision;
    }

    /**
     * Set the value of revision
     *
     * @param revision new value of revision
     */
    public void setSVNRevision(Long revision) throws TacheException {
        if (revision != null && !this.isFinie()) {
            throw new TacheException("La tache n'est pas finie");
        }
        if (revision != null && revision < 1L) {
            throw new TacheException("Le numéro de révision doit être strictement positif");
        }
        if (revision == null && this.isFinie()) {
            throw new TacheException("On ne peut assigner null à une revision quand la tache est finie");

        }
        this.revision = revision;
    }

    public boolean isFinie() {
        return this.pourcentage == 100;
    }

    @Override
    public String toString() {
        return "Tache n°" + id + " Nom : " + nom + " Importance : " + importance + "\n Description : " + this.description;
    }

    public boolean isTimerLaunched() {
        return (timerLaunched == '1' ? true : false);
    }

    /**
     * @param timerLaunched the timerLaunched to set
     */
    public void setTimerLaunched(boolean timerLaunched) {
        this.timerLaunched = (timerLaunched ? '1' : '0');
        setDateDeb(new Date());
    }

    /**
     * @return the dateDeb
     */
    public Date getDateDeb() {
        return dateDeb;
    }

    /**
     * @param dateDeb the dateDeb to set
     */
    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    /**
     * @return the tempsPasseSurTache
     */
    public Date getTempsPasseSurTache() {
        return tempsPasseSurTache;
    }

    /**
     * @param tempsPasseSurTache the tempsPasseSurTache to set
     */
    public void setTempsPasseSurTache(Date tempsPasseSurTache) {
        this.tempsPasseSurTache = tempsPasseSurTache;
    }

    public void addMembre(Membre membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Impossible d'ajouter un membre null à la tâche !");
        }
        if (hasMembre(membre)) {
            return;
        }

        membres.add(new ParticipeTache(this, membre));
        String sujet = "[PROJET MACHIN] Invitation à rejoindre une tâche"; // FIXME
        String corps = "<html><h1>Vous avez reçu une invitation pour participer à la tâche TRUC du projet MACHIN</h1>"; // FIXME
        corps += "<p>Pour accepter ou refuser, cliquez sur un des liens suivants :</p>";
        corps += "<p><a href='http://localhost/GestionProjet/FrontController?action=accepterTache&membre=" + membre.getId() + "&tache=" + getId() + "'>Accepter</a></p>";
        corps += "<p><a href='http://localhost/GestionProjet/FrontController?action=refuserTache&membre=" + membre.getId() + "&tache=" + getId() + "'>Refuser</a></p>";
        corps += "<br/><br/>A bientôt !";
        try {
            Mailer.send(membre.getMail(), "Invitation à rejoindre une tâche", corps);
        } catch (MailException ex) {
            Logger.getLogger(Tache.class.getName()).log(Level.SEVERE, null, ex); // FIXME
        }
    }

    public boolean hasMembre(Membre membre) {
        return membres.contains(new ParticipeTache(this, membre));
    }

    public int getNbMembres() {
        return membres.size();
    }

    public List<Membre> getMembres() {
        List<Membre> ret = new ArrayList<Membre>();
        for (ParticipeTache participant : membres) {
            if (participant.isAccepte()) {
                ret.add(participant.getMembre());
            }
        }

        return ret;
    }

    public List<Membre> getAllMembres() {
        List<Membre> ret = new ArrayList<Membre>();
        for (ParticipeTache participant : membres) {
            ret.add(participant.getMembre());
        }

        return ret;
    }

    public Projet getProjet() {
        return new Projet();
//        return projet; // TODO
    }
}
