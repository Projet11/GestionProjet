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
import javax.persistence.PersistenceContext;

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

    public void setTache(Tache tache) {
        this.tache = tache;
    }


    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of MarqueTacheBean
     */
    public MarqueTacheBean() {
    }
    
    public void modificationTache() throws TacheException {
        tache.setPourcentage(tache.getPourcentage().intValue());
        tache.setSVNRevision(tache.getSVNRevision());
        tache.setImportance(tache.getImportance());
        em.persist(tache);
    }
}
