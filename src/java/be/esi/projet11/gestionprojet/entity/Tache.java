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
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author g34840
 */
@Entity
@Table(name = "TACHE")
@NamedQueries({
    @NamedQuery(name = "Tache.findByNom", query = "SELECT t FROM Tache t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tache.findAll", query = "SELECT t FROM Tache t"),
    @NamedQuery(name = "Tache.findTachesArchivees", query = "SELECT t FROM Tache t WHERE t.archive = '1'"),
    @NamedQuery(name = "Tache.findTachesNonArchivees", query = "SELECT t FROM Tache t WHERE t.archive = '0'"),
    @NamedQuery(name = "Tache.findTachesByProjet", query = "SELECT t FROM Tache t WHERE t.projet = :projet"),
    @NamedQuery(name = "Tache.findTachesArchiveesByProjet", query = "SELECT t FROM Tache t WHERE t.archive = '1' AND t.projet = :projet"),
    @NamedQuery(name = "Tache.findTachesNonArchiveesByProjet", query = "SELECT t FROM Tache t WHERE t.archive = '0' AND t.projet = :projet"),
    @NamedQuery(name = "Tache.findTimerLaunched", query = "Select t FROM Tache t where t.timerLaunched = '1'")})
public class Tache implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDeb;
    private long tempsPasseSurTache;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tache")
    private Collection<ParticipeTache> membres;
    private char archive;
    @JoinColumn(name = "PROJET", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Projet projet; // TODO: établir un lien entre projet et tâche avec un ManyToOne comme pour membres
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tache")
    private List<Commentaire> conversation;

    public List<Commentaire> getConversation() {
        return conversation;
    }

    public void setConversation(List<Commentaire> conversation) {
        this.conversation = conversation;
    }

    public Tache() throws TacheException {
        this("<nomInexistant>", "<descriptionInexistante>");
        this.timerLaunched = '0';
        membres = new ArrayList<ParticipeTache>();
        projet = new Projet();
        conversation = new ArrayList<Commentaire>();
    }

    public Tache(String nom, String description) throws TacheException {
        if (nom == null || nom.equals("")) {
            throw new TacheException("Le nom d'une tâche ne peut pas être vide");
        }
        this.id = 0l;
        this.nom = nom;
        this.description = description;
        this.importance = ImportanceEnum.NORMALE;
        this.pourcentage = 0;
        this.revision = null;
        conversation = new ArrayList<Commentaire>();
        membres = new ArrayList<ParticipeTache>();
        projet = new Projet();
    }

    public Tache(String nom, String description, ImportanceEnum importance) throws TacheException {
        this(nom, description);
        this.importance = importance;
    }

    public Tache(String nom, String description, ImportanceEnum importance, Projet p) throws TacheException {
        this(nom, description);
        this.importance = importance;
        this.projet = p;
    }

    /**
     * Get the value of pourcentage
     *
     * @return the value of pourcentage
     */
    public Byte getPourcentage() {
        return pourcentage;
    }

    public boolean isArchive() {
        return archive == '1';
    }

    public void setArchive(boolean archive) {
        if (archive) {
            this.archive = '1';
        } else {
            this.archive = '0';
        }
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
        if(revision == null){
            throw new TacheException("La revision ne peut etre null");
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
    public void setTimerLaunched() {
        if (isTimerLaunched()) {
            System.out.println("Timerlaunched: arrêt");
            this.timerLaunched = '0';
            setTempsPasseSurTache(new Date().getTime() - dateDeb.getTime());
        }else{
            System.out.println("Timerlaunched: début");
            this.timerLaunched = '1';
            dateDeb=new Date();
        }
    }

    public Time getTimer() {
        Date currDate = new Date();
        return new Time(currDate.getTime() - getDateDeb().getTime());
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
    public long getTempsPasseSurTache() {
        return tempsPasseSurTache;
    }

    /**
     * @param tempsPasseSurTache the tempsPasseSurTache to set
     */
    public void setTempsPasseSurTache(long tempsEnPlus) {
        this.tempsPasseSurTache+= tempsEnPlus;
    }

    public void addMembre(Membre membre) throws TacheException {
        if (membre == null)
            throw new IllegalArgumentException("Impossible d'ajouter un membre null à la tâche !");

        if (hasMembre(membre))
            return;
        
        membres.add(new ParticipeTache(this, membre));
        String sujet = "[PROJET " + projet.getNom() + "] Invitation à rejoindre une tâche";
        String corps = "<html><h1>Vous avez reçu une invitation pour participer à la tâche " + nom + " du projet " + projet.getNom() + "</h1>";
        corps += "<p>Pour accepter ou refuser, cliquez sur un des liens suivants :</p>";
        corps += "<p><a href='http://localhost:27583/GestionProjet/pages/accepterTache.xhtml?idMembre=" + membre.getId() + "&idTache=" + getId() + "'>Accepter</a></p>";
        corps += "<p><a href='http://localhost:27583/GestionProjet/pages/refuserTache.xhtml?idMembre=" + membre.getId() + "&idTache=" + getId() + "'>Refuser</a></p>"; // TODO: Corriger les liens
        corps += "<br/><br/>A bientôt !";
        try {
            Mailer.send(membre.getMail(), "Invitation à rejoindre une tâche", corps, true);
        } catch (MailException ex) {
            throw new TacheException("Impossible d'envoyer un mail à " + membre.getMail());
        }
    }
    
    public boolean hasMembre(Membre membre) {
        if (this.getId() == null) // Si un tâche n'a pas encore été persistée, elle n'a pas de membres
        {
            return false;
        }
        return membres.contains(new ParticipeTache(this, membre));
    }

    public Collection<ParticipeTache> getParticipations() {
        return membres;
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
    
    public List<Membre> getParticipantsAcceptes() {
        List<Membre> ret = new ArrayList<Membre>();
        for (ParticipeTache pt : membres) {
            if (pt.isAccepte()) {
                ret.add(pt.getMembre());
            }
        }
        return ret;
    }
    
    public void accepterMembre(Membre mbr) {
        if (mbr.getMail() == null || mbr.getMail().equals("")) {
            return;
        }
        ParticipeTache pt = getParticipeTache(mbr);
        if (pt != null) {
            pt.setAccepte(true);
        }
    }
    
    public ParticipeTache getParticipeTache(Membre mbr) {
        for (ParticipeTache pt : membres) {
            if (pt.getMembre().equals(mbr)) {
                return pt;
            }
        }
        return null;
    }

    public List<Membre> getAllMembres() {
        List<Membre> ret = new ArrayList<Membre>();
        for (ParticipeTache participant : membres) {
            ret.add(participant.getMembre());
        }

        return ret;
    }

    public Projet getProjet() {
        return projet; // TODO
    }

    public void refuserParticipant(Membre membre) {
        ParticipeTache pt = getParticipeTache(membre);
        if (pt != null) {
            membres.remove(pt);
            List<Membre> membres = getParticipantsAcceptes();
            String objet = "Refus d'ajout";
            String corps = "Le membre " + membre.getMail() + " a refusé d'être ajouté à la tâche "
                    + nom + ".";
            for (Membre unMembre : membres) {
                try {
                    Mailer.send(unMembre.getMail(), objet, corps);
                } catch (MailException ex) {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage("inscrireMembresATache", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible d'envoyer un mail à " + unMembre.getMail() + " <br/>" + ex.getMessage(), ""));
                }
            }
        }
    }
    
    public String getDate(){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("kk:mm:ss");
        return formatTime.format(dateDeb)+" le "+formatDate.format(dateDeb);
    }
}
