package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author g34840
 */
@ManagedBean(name = "tacheEJB")
@SessionScoped
public class TacheEJB {

    @ManagedProperty("#{MembreManage}")
    private MembreManage membreBean;
    private EntityManager em;
    // Attributs utilisés par le formulaire de création d'une tâche uniquement
    private String creationNom;
    private String creationDescription;
    private ImportanceEnum creationImportance;
    private Tache tache;
    private Long revision;
    private Long pourcentage;
    private ImportanceEnum importance;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Get the value of creationImportance
     *     
* @return the value of creationImportance
     */
    public ImportanceEnum getCreationImportance() {
        return creationImportance;
    }

    /**
     * Set the value of creationImportance
     *     
* @param creationImportance new value of creationImportance
     */
    public void setCreationImportance(ImportanceEnum creationImportance) {
        this.creationImportance = creationImportance;
    }

    /**
     * Get the value of creationDescription
     *     
* @return the value of creationDescription
     */
    public String getCreationDescription() {
        return creationDescription;
    }

    /**
     * Set the value of creationDescription
     *     
* @param creationDescription new value of creationDescription
     */
    public void setCreationDescription(String creationDescription) {
        this.creationDescription = creationDescription;
    }

    /**
     * Get the value of creationNom
     *     
* @return the value of creationNom
     */
    public String getCreationNom() {
        return creationNom;
    }

    /**
     * Set the value of creationNom
     *     
* @param creationNom new value of creationNom
     */
    public void setCreationNom(String creationNom) {
        this.creationNom = creationNom;
    }

    /**
     * Get the value of membreBean
     *     
* @return the value of membreBean
     */
    public MembreManage getMembreBean() {
        return membreBean;
    }

    public TacheEJB() throws TacheException {
        System.out.println("---------MarqueTacheBean");
        tache = new Tache();
        tache.setNom("abc");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionProjetPU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", tache.getNom());
        tache = (Tache) query.getSingleResult();
        em.close();
        System.out.println("DB " + tache.getImportance());
        this.setImportance(tache.getImportance());
        this.setPourcentage(tache.getPourcentage().longValue());
        this.setRevision(tache.getSVNRevision());
        System.out.println("---------FIN - MarqueTacheBean");
    }

    /**
     * Set the value of membreBean
     *     
* @param membreBean new value of membreBean
     */
    public void setMembreBean(MembreManage membreBean) {
        this.membreBean = membreBean;
    }

    /**
     * Get the value of tache
     *     
* @return the value of tache
     */
    public Tache getTache() {
        return tache;
    }

    /**
     * Set the value of tache
     *     
* @param tache new value of tache
     */
    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Tache creerTache(String nom, String description) throws TacheException {
        try {
            Tache uneTache = new Tache(nom, description);
            tache = uneTache;
            persist(tache);
        } catch (SecurityException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tache;
    }

    public Tache creerTache(String nom, String description, ImportanceEnum importance) throws TacheException {
        try {
            Tache uneTache = new Tache(nom, description, importance);
            tache = uneTache;
            persist(tache);
        } catch (SecurityException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tache;
    }

    public String creerTache() {
        try {
            creerTache(creationNom, creationDescription, ImportanceEnum.IMPORTANT);
        } catch (TacheException ex) {
            return "failure";
        }

        return "success";
    }

    public String annulerCreation() {
        return "failure"; // TODO: return annulation pour un comportement différent ?
    }

    public Tache getTache(String nom) {
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", nom);
        return (Tache) query.getSingleResult();
    }

    public Tache getTache(long id) {
        return em.find(Tache.class, id);
    }

    public Tache getCurrentTache() {
        return tache;
    }

    public Collection<Tache> getAllTache() {
        Query query = em.createNamedQuery("Tache.findAll");
        return query.getResultList();

    }

    public void startTimer() {
        tache.setTimerLaunched(true);
    }

    public void stopTimer() {
        tache.setTimerLaunched(true);
    }

    public Time getTimer() {
        Date currDate = new Date();
        return new Time(currDate.getTime() - tache.getDateDeb().getTime());
    }

    public boolean isTimerLaunched() {
        return tache.isTimerLaunched();
    }

    public String inscrireMembresATache() {
        for (Membre membre : membreBean.getMembreSel()) {
            tache.addMembre(membre);
        }
        em.merge(tache);
        return "success";
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
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

    public ImportanceEnum getImportance() {
        return importance;
    }

    public void setImportance(ImportanceEnum importance) {
        this.importance = importance;

        System.out.println("-------- CHANGEMENT IMPORTANCE " + importance);
    }

    public void modificationTache() throws TacheException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        System.out.println("---------ModficicationTache");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionProjetPU");
        em = emf.createEntityManager();
        utx.begin();
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", tache.getNom());
        tache = (Tache) query.getSingleResult();
        tache.setPourcentage(pourcentage.intValue());
        tache.setSVNRevision(revision);
        System.out.println(importance);
        tache.setImportance(importance);
        utx.commit();
        em.close();
        System.out.println("---------FIN - ModficicationTache");

    }
}
