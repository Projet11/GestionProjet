/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author g34771
 */
@ManagedBean(name = "tacheCtrl")
@SessionScoped
public class TacheController {

    @EJB
    private TacheEJB tacheEJB;
    @EJB
    private MembreEJB membreEJB;
//    @ManagedProperty("#{projetCtrl}")
//    private ProjetController projetCtrl;
    // Attributs utilisés par le formulaire de création d'une tâche uniquement
    private String nomParam;
    private String descriptionParam;
    private ImportanceEnum importanceParam;
    private Long revisionParam;
    private Long pourcentageParam;
    private Collection<String> membresSel;
    private Membre membreCourantParam;
    private String commentaireParam;
    private Tache tacheCourante;
    private String archive;
    private Collection<Tache> taches;
    private String creationNom;
    private ImportanceEnum creationImportance;
    private String creationDescription;
    private Projet projet;

    public String getCreationNom() {
        return creationNom;
    }

    public void setCreationNom(String creationNom) {
        this.creationNom = creationNom;
    }

    public ImportanceEnum getCreationImportance() {
        return creationImportance;
    }

    public void setCreationImportance(ImportanceEnum creationImportance) {
        this.creationImportance = creationImportance;
    }

    public String getCreationDescription() {
        return creationDescription;
    }

    public void setCreationDescription(String creationDescription) {
        this.creationDescription = creationDescription;
    }

    public Membre getMembreCourantParam() {
        return membreCourantParam;
    }

    public void setMembreCourantParam(Membre membreCourantParam) {
        this.membreCourantParam = membreCourantParam;
    }

    public String getCommentaireParam() {
        return commentaireParam;
    }

    public void setCommentaireParam(String commentaireParam) {
        this.commentaireParam = commentaireParam;
    }

    @PostConstruct
    private void init() {
        this.archive = "toutes";
        if (tacheCourante != null) {
            this.nomParam = tacheCourante.getNom();
            this.importanceParam = tacheCourante.getImportance();
            this.pourcentageParam = tacheCourante.getPourcentage().longValue();
            this.revisionParam = tacheCourante.getSVNRevision();
        }
    }

    public String getNomParam() {
        return nomParam;
    }

//    public ProjetController getProjetCtrl() {
//        return projetCtrl;
//    }
//
//    public void setProjetCtrl(ProjetController projetCtrl) {
//        this.projetCtrl = projetCtrl;
//    }
    public void setNomParam(String nomParam) {
        this.nomParam = nomParam;
    }

    public String getDescriptionParam() {
        return descriptionParam;
    }

    public void setDescriptionParam(String descriptionParam) {
        this.descriptionParam = descriptionParam;
    }

    public ImportanceEnum getImportanceParam() {
        return importanceParam;
    }

    public void setImportanceParam(ImportanceEnum importanceParam) {
        this.importanceParam = importanceParam;
    }

    public Long getRevisionParam() {
        return revisionParam;
    }

    public void setRevisionParam(Long revisionParam) {
        this.revisionParam = revisionParam;
    }

    public Long getPourcentageParam() {
        return pourcentageParam;
    }

    public void setPourcentageParam(Long pourcentageParam) {
        this.pourcentageParam = pourcentageParam;
    }

    public Tache getTacheCourante() throws BusinessException {
        if (tacheCourante == null) {
            try {
                tacheCourante = tacheEJB.creerTache("Temporaire", "Tache courante automatique");
            } catch (DBException ex) {
                throw new BusinessException("Il n'y a pas de tache courante ! : " + ex.getMessage());
            }
        }
        return tacheCourante;
    }

    public void setTacheCourante(Tache tacheCourante) {
        this.tacheCourante = tacheCourante;
    }

    public Collection<String> getMembresSel() {
        return membresSel;
    }

    public void setMembresSel(Collection<String> membresSel) {
        this.membresSel = membresSel;
    }

    public SelectItem[] getImportanceValues() {
        SelectItem[] items = new SelectItem[ImportanceEnum.values().length];
        int i = 0;
        for (ImportanceEnum g : ImportanceEnum.values()) {
            items[i++] = new SelectItem(g, g.getLibelle());
        }
        return items;
    }

    public String creerTache() {
        try {
            tacheEJB.creerTache(nomParam, descriptionParam, importanceParam);
        } catch (DBException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("creerTache", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible de créer la tâche <br/>" + ex.getMessage(), ""));
            return "failure";
        }
        return "success";
    }

    public String annulerCreation() {
        return "annuler";
    }

    public void startTimer() throws BusinessException {
        startTimer(getTacheCourante());
    }

    public void stopTimer() throws BusinessException {
        stopTimer(getTacheCourante());
    }

    public void startTimer(Tache tache) {
        tache.setTimerLaunched(true);
        tacheEJB.saveTache(tache);
    }

    public void stopTimer(Tache tache) {
        tache.setTimerLaunched(false);
        tacheEJB.saveTache(tache);
    }

    public Time getTimer() throws BusinessException {
        Date currDate = new Date();
        return new Time(currDate.getTime() - getTacheCourante().getDateDeb().getTime());
    }

    public boolean isTimerLaunched() throws BusinessException {
        return getTacheCourante().isTimerLaunched();
    }

    public Collection<Tache> getAllTimerLaunched() {
        return tacheEJB.getAllTimerLaunched();
    }

    public String inscrireMembresATache() {
        try {
            for (String membreId : membresSel) {
                Membre membre = membreEJB.getById(Long.parseLong(membreId));
                getTacheCourante().addMembre(membre);
            }
            tacheEJB.saveTache(getTacheCourante());
        }
        catch (TacheException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("inscrireMembresATache", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible d'ajouter un membre à la tâche <br/>" + ex.getMessage(), ""));
        }
        catch (BusinessException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("inscrireMembresATache", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible d'ajouter un membre à la tâche <br/>" + ex.getMessage(), ""));
        }
        return null; // Retour sur la même page
    }

    public String modificationTache() {
        try {
            if (pourcentageParam != null) {
                tacheEJB.modificationTache(tacheCourante, pourcentageParam.intValue(), revisionParam, importanceParam);
            } else {
                throw new TacheException();
            }
            return retourneAccueil();
        } catch (TacheException ex) {
            System.err.println("Il existe au moins une donnée entrés  dans les paramètres entrés");
            return null;
        }
    }

    public void ajouterConversation() {
        if (tacheCourante != null && membreCourantParam != null
                && commentaireParam != null && !commentaireParam.isEmpty()) {
            tacheEJB.ajouterConversation(tacheCourante, membreCourantParam, commentaireParam);
        } else {
            System.err.println("la tache, le membre ou le commentaire ne peut être vide");
        }
    }

    public String modifierTache(Tache tache) {
        if (tache != null) {
            tacheCourante = tache;
            nomParam = tache.getNom();
            descriptionParam = tache.getDescription();
            importanceParam = tache.getImportance();
            revisionParam = tache.getSVNRevision();
            pourcentageParam = tache.getPourcentage().longValue();
            return "modificationTache";
        }
        return null;
    }

    public String retourneAccueil() {
        return "retourneAccueil";
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public Collection<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Collection<Tache> taches) {
        this.taches = taches;
    }

    public void archiverTache() {
        tacheCourante.setArchive(true);
    }

    public void desarchiverTache() {
        tacheCourante.setArchive(false);
    }

    public String affichageTaches() {
        taches = null;
        if (archive.equals("toutes")) {
            taches = tacheEJB.getTaches(null, projet);
        } else {
            if (archive.equals("archivees")) {
                taches = tacheEJB.getTaches(true, projet);
            } else {
                taches = tacheEJB.getTaches(false, projet);
            }
        }
        //return "success";
        return null;
    }

    public String affichageTaches(Projet projet) {
        taches = null;
        this.projet = projet;
        if (archive.equals("toutes")) {
            taches = tacheEJB.getTaches(null, projet);
        } else {
            if (archive.equals("archivees")) {
                taches = tacheEJB.getTaches(true, projet);
            } else {
                taches = tacheEJB.getTaches(false, projet);
            }
        }
        return null;
    }

    public String fenetreCreeTache() {
        return "creeTache";
    }
}
