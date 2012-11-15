package be.esi.projet11.gestionprojet.ejb;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author g34840
 */
@ManagedBean(name = "bean")
@RequestScoped
public class MarqueTacheBean {

    private ImportanceEnum importance;

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

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of MarqueTacheBean
     */
    public MarqueTacheBean() {
    }

    public void chgmtImportance() {
        
    }
}
