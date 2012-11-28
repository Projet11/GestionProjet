/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    // Attributs utilisés par le formulaire de création d'une tâche uniquement
    private String nomParam;
    private String descriptionParam;
    private ImportanceEnum importanceParam;
    private Long revisionParam;
    private Long pourcentageParam;
    private Collection<Membre> membresSel;
    //
    private Tache tacheCourante;
    
    /**
     * Creates a new instance of TacheController
     */
    public TacheController() {
    }

    public String getNomParam() {
        return nomParam;
    }

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
        if (tacheCourante == null){
            try {
                tacheCourante = tacheEJB.creerTache("Temporaire", "Tache courante automatique");
            } catch (TacheException ex) {
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
    for(ImportanceEnum g: ImportanceEnum.values()) {
      items[i++] = new SelectItem(g, g.getLibelle());
    }
    return items;
  }
    public String creerTache() {
        try {
            tacheEJB.creerTache(nomParam, descriptionParam, importanceParam);
        } catch (TacheException ex) {
            return "failure";
        }
        return "success";
    }

    public String annulerCreation() {
        return "failure"; // TODO: return annulation pour un comportement différent ?
    }
    
    public void startTimer() {
        getTacheCourante().setTimerLaunched(true);
        tacheEJB.saveTache(tacheCourante);
    }

    public void stopTimer() {
        getTacheCourante().setTimerLaunched(false);
        tacheEJB.saveTache(tacheCourante);
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
}
