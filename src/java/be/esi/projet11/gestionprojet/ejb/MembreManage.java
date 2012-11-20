/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.DBException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author g34754
 */
@ManagedBean(name = "MembreManage")
@SessionScoped
public class MembreManage {
    @EJB
    private MembreEJB membreEJB;

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    @ManagedProperty("#{ProjetManage}")
    private ProjetManage projetBean;
    

    /**
     * Get the value of projetBean
     *
     * @return the value of projetBean
     */
    public ProjetManage getProjetBean() {
        return projetBean;
    }

    /**
     * Set the value of projetBean
     *
     * @param projetBean new value of projetBean
     */
    public void setProjetBean(ProjetManage projetBean) {
        this.projetBean = projetBean;
    }
    private Collection<String> membreSel;

    /**
     * Get the value of membreSel
     *
     * @return the value of membreSel
     */
    public Collection<String> getMembreSel() {
        return membreSel;
    }

    /**
     * Set the value of membreSel
     *
     * @param membreSel new value of membreSel
     */
    public void setMembreSel(Collection<String> membreSel) {
        this.membreSel = membreSel;
    }

    /**
     * Creates a new instance of MembreManage
     */
    public MembreManage() {
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

    public Collection<Membre> getAllMembres() {
        try {
            return membreEJB.getAllUsers();
        } catch (DBException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
