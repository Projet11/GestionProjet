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
@ManagedBean(name = "marqueTacheBean")
@SessionScoped
public class MarqueTacheBean {

    private Tache tache;
    private Long revision;
    private Long pourcentage;
    private ImportanceEnum importance;

    public Tache getTache() {
        return tache;
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
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of MarqueTacheBean
     */
    public MarqueTacheBean() throws TacheException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException, HeuristicRollbackException {
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
