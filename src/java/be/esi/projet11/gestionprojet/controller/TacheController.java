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
import javax.annotation.PostConstruct;
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
    private Membre membreCourantParam;
    private String commentaireParam;

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
    //
    private Tache tacheCourante;

    private void test() {
        try {
            tacheCourante = tacheEJB.creerTache("alouetteeeee", "blouette", ImportanceEnum.IMPORTANT);
        } catch (TacheException ex) {
            System.out.println("---------------------------------------------");
            Logger.getLogger(TacheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    private void init() {
        test();
        this.nomParam = tacheCourante.getNom();
        this.importanceParam = tacheCourante.getImportance();
        this.pourcentageParam = tacheCourante.getPourcentage().longValue();
        this.revisionParam = tacheCourante.getSVNRevision();
        membreCourantParam = new Membre("alouette@gmail.com");
        commentaireParam = "youhouuuuu";
        this.ajouterConversation();
        commentaireParam = "youhouuuuuuu222222222";
        membreCourantParam = new Membre("abcdef@hotmail.com");
        this.ajouterConversation();

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
        if (tacheCourante == null) {
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
        for (ImportanceEnum g : ImportanceEnum.values()) {
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

    public Collection<Tache> getAllTimerLaunched() {
        return tacheEJB.getAllTimerLaunched();
    }

    public String inscrireMembresATache() {
        for (Membre membre : membresSel) {
            getTacheCourante().addMembre(membre);
        }
        tacheEJB.saveTache(getTacheCourante());
        return "success";
    }

    public void modificationTache() {
        try {
            tacheEJB.modificationTache(tacheCourante, pourcentageParam.intValue(), revisionParam, importanceParam);
        } catch (TacheException ex) {
            System.err.println("Il existe au moins une donnée entrés  dans les paramètres entrés");
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
}
