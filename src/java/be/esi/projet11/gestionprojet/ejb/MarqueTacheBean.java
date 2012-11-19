package be.esi.projet11.gestionprojet.ejb;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author g34840
 */
@ManagedBean(name = "marqueTacheBean")
@RequestScoped
public class MarqueTacheBean {

    private Tache tache;

    public Tache getTache() {
        return tache;
    }
    private Long revision;
    private Long pourcentage;
    private ImportanceEnum importance;

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
    }
    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of MarqueTacheBean
     */
    public MarqueTacheBean() throws TacheException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionProjetPU");
        em = emf.createEntityManager();
        tache = new Tache("abc", "defghij", ImportanceEnum.TRESIMPORTANT);
        this.setImportance(tache.getImportance());
        this.setPourcentage(tache.getPourcentage().longValue());
        this.setRevision(tache.getSVNRevision());
    }

    public void modificationTache() throws TacheException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        utx.begin();
        //tache.setImportance(importance);
        Tache tache2 = em.merge(tache);
        tache2.setPourcentage(pourcentage.intValue());
        //tache2.setImportance(importance);
        tache2.setSVNRevision(revision);
        utx.commit();
    }
}
