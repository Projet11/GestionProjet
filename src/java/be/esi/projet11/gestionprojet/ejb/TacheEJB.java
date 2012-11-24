/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user0
 */
@Stateless
public class TacheEJB implements TacheEJBLocal {

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;

    public TacheEJB() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Tache creerTache(String nom, String description) {
        Tache tache = null;
        try {
            tache = new Tache(nom, description);
            em.persist(tache);
        } catch (TacheException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tache;
    }

    @Override
    public Tache creerTache(String nom, String description, ImportanceEnum imp) {
        Tache tache = null;
        try {
            tache = new Tache(nom, description, imp);
            em.persist(tache);
        } catch (TacheException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tache;
    }

    @Override
    public Tache getTache(String nom) {
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", nom);
        Tache tache = (Tache) query.getSingleResult();
        return tache;
    }

    @Override
    public Tache getTache(Long id) {
        return em.find(Tache.class, id);
    }

    @Override
    public Collection<Tache> getAllTache() {
        Query query = em.createNamedQuery("Tache.findAll");
        return query.getResultList();
    }

    @Override
    public void modificationTache(Tache tache) {
        try {
            Tache newTache = em.find(Tache.class, tache.getId());
            newTache.setImportance(tache.getImportance());
            newTache.setPourcentage(tache.getPourcentage());
            newTache.setSVNRevision(tache.getSVNRevision());
        } catch (TacheException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
