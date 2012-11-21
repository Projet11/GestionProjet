/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author user0
 */
@ManagedBean(name = "TacheEJB")
@SessionScoped
public class ManageTacheEJB {

    @EJB
    private TacheEJBLocal tacheEJB;
    private Tache tache;
    private String nom;
    private Long revision;
    private Long pourcentage;
    private ImportanceEnum importance;

    /**
     * Creates a new instance of ManageTacheEJB
     */
    public ManageTacheEJB() throws NamingException {
        InitialContext ctx = new InitialContext();
        tacheEJB = (TacheEJBLocal) ctx.lookup("java:global/GestionProjet/TacheEJB");
//        tacheEJB.creerTache("abc", "defgh", ImportanceEnum.TRESIMPORTANT);
//        tacheEJB.creerTache("cba", "defgh", ImportanceEnum.NORMALE);
        tache = tacheEJB.getTache("abc");
        this.nom = tache.getNom();
        this.pourcentage = tache.getPourcentage().longValue();
        this.importance = tache.getImportance();
        this.revision = tache.getSVNRevision();
    }
  public SelectItem[] getImportanceValues() {
    SelectItem[] items = new SelectItem[ImportanceEnum.values().length];
    int i = 0;
    for(ImportanceEnum g: ImportanceEnum.values()) {
      items[i++] = new SelectItem(g, g.getLibelle());
    }
    return items;
  }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Long getRevision() {
        return revision;
    }

    public void setRevision(Long revision) {
        this.revision = revision;
    }

    public Long getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Long pourcentage) {
        this.pourcentage = pourcentage;
    }

    /**
     * Get the value of importance
     *
     * @return the value of importance
     */
    public ImportanceEnum getImportance() {
        return importance;
    }

    /**
     * Set the value of importance
     *
     * @param importance new value of importance
     */
    public void setImportance(ImportanceEnum importance) {
        this.importance = importance;
    }

    public void modificationTache() throws TacheException {
        tache.setPourcentage(pourcentage.intValue());
        tache.setSVNRevision(revision);
        tache.setImportance(importance);
        tacheEJB.modificationTache(tache);
    }
}
