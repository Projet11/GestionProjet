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
    private String creationNom;
    private String creationDescription;
    private ImportanceEnum creationImportance;
    private Collection<Membre> membresSel;
    //
    private Tache tacheCourante;
    
    /**
     * Creates a new instance of TacheController
     */
    public TacheController() {
    }

    public String getCreationNom() {
        return creationNom;
    }

    public void setCreationNom(String creationNom) {
        this.creationNom = creationNom;
    }

    public String getCreationDescription() {
        return creationDescription;
    }

    public void setCreationDescription(String creationDescription) {
        this.creationDescription = creationDescription;
    }

    public ImportanceEnum getCreationImportance() {
        return creationImportance;
    }

    public void setCreationImportance(ImportanceEnum creationImportance) {
        this.creationImportance = creationImportance;
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
    
    public String creerTache() {
        try {
            tacheEJB.creerTache(creationNom, creationDescription, creationImportance);
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
    }

    public void stopTimer() {
        getTacheCourante().setTimerLaunched(true);
    }

    public Time getTimer() {
        Date currDate = new Date();
        return new Time(currDate.getTime() - getTacheCourante().getDateDeb().getTime());
    }

    public boolean isTimerLaunched() {
        return getTacheCourante().isTimerLaunched();
    }

    public String inscrireMembresATache() {
        for (Membre membre : membresSel) {
            getTacheCourante().addMembre(membre);
        }
        tacheEJB.saveTache(getTacheCourante());
        return "success";
    }

}
