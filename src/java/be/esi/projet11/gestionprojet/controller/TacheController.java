/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//    @ManagedProperty("#{projetCtrl}")
//    private ProjetController projetCtrl;
    // Attributs utilisés par le formulaire de création d'une tâche uniquement
    private String nomParam;
    private String descriptionParam;
    private ImportanceEnum importanceParam;
    private Long revisionParam;
    private Long pourcentageParam;
    private Collection<Membre> membresSel;
    private String archive;
    private Collection<Tache> taches;
    //
    private Tache tacheCourante;
    private String creationNom;
    private ImportanceEnum creationImportance;
    private String creationDescription;
    private Projet projet;

    public String getCreationDescription() {
        return creationDescription;
    }

    public void setCreationDescription(String creationDescription) {
        this.creationDescription = creationDescription;
    }

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

    /**
     * Creates a new instance of TacheController
     */
    public TacheController() {
        archive = "toutes";
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

    public Tache getTacheCourante() {
        if (tacheCourante == null) {
            try {
                tacheCourante = tacheEJB.creerTache("Temporaire", "Tache courante automatique");
            } catch (TacheException ex) {
                // TODO Retourner null si null.! 
                Logger.getLogger(TacheController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tacheCourante;
    }

    public void setTacheCourante(Tache tacheCourante) {
        this.tacheCourante = tacheCourante;
    }

    public Collection<Membre> getMembresSel() {
        return membresSel;
    }

    public void setMembresSel(Collection<Membre> membresSel) {
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
        } catch (TacheException ex) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("creerTache", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible de créer la tâche <br/>"+ex.getMessage(), ""));
            return "failure";
        }
        return "success";
    }

    public String annulerCreation() {
        return "annuler";
    }

    public void startTimer() {
        startTimer(getTacheCourante());
    }

    public void stopTimer() {
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

    public Time getTimer() {
        Date currDate = new Date();
        return new Time(currDate.getTime() - getTacheCourante().getDateDeb().getTime());
    }

    public boolean isTimerLaunched() {
        return getTacheCourante().isTimerLaunched();
    }
    
    public Collection<Tache> getAllTimerLaunched(){
        return tacheEJB.getAllTimerLaunched();
    }

    public String inscrireMembresATache() {
        for (Membre membre : membresSel) {
            getTacheCourante().addMembre(membre);
        }
        tacheEJB.saveTache(getTacheCourante());
        return "success";
    }

    public void modificationTache() throws TacheException {
        tacheCourante.setPourcentage(pourcentageParam.intValue());
        tacheCourante.setSVNRevision(revisionParam);
        tacheCourante.setImportance(importanceParam);
        tacheEJB.modificationTache(tacheCourante);
    }

    public String modifierTache(Tache tache) {
        tacheEJB.modificationTache(tache);
        // TODO navigation vers modification page
        return null;
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
        this.projet=projet;
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

    public String fenetreCreeTache() {
        return "creeTache";
    }
}
