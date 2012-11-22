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
 * @author g34840
 */
@Stateless
public class TacheEJB {

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;

    public TacheEJB() {
    }

    public Tache creerTache(String nom, String description) throws TacheException {
        return creerTache(nom, description, ImportanceEnum.IMPORTANT);
    }

    public Tache creerTache(String nom, String description, ImportanceEnum importance) throws TacheException {
        Tache uneTache = null;
        try {
            uneTache = new Tache(nom, description, importance);
            em.persist(uneTache);
        } catch (SecurityException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uneTache;
    }

    public Tache getTache(String nom) {
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", nom);
        return (Tache) query.getSingleResult();
    }

    public Tache getTache(long id) {
        return em.find(Tache.class, id);
    }

    public Collection<Tache> getAllTache() {
        Query query = em.createNamedQuery("Tache.findAll");
        return query.getResultList();
    }

    public void saveTache(Tache tache) {
        if (em.find(Tache.class, tache.getId()) != null) {
            em.merge(tache);
        }
    }
}