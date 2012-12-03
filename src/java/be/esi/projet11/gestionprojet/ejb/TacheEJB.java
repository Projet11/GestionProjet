package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Commentaire;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
public class TacheEJB {

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;

    public TacheEJB() {
    }

    public Tache creerTache(String nom, String description) throws TacheException {
        return creerTache(nom, description, ImportanceEnum.IMPORTANT);
    }

    public Tache creerTache(String nom, String description, ImportanceEnum importance, Projet p) throws TacheException {
        Tache uneTache = null;
        try {
            uneTache = new Tache(nom, description, importance, p);
            em.persist(uneTache);
        } catch (SecurityException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(TacheEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uneTache;
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
        Tache tache = (Tache) query.getSingleResult();
        return tache;
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

    public void modificationTache(Tache tacheCourante) {
    }

    public void ajouterConversation(Tache tacheCourante, Membre membre, String commentaire) {
        if (em.find(Tache.class, tacheCourante.getId()) != null) {
            tacheCourante.getConversation().add(new Commentaire(tacheCourante, membre, commentaire,new Date()));
        } else {
            throw new IllegalArgumentException("La tache ne peut être null");
        }

    }

    public List<Commentaire> getConversation(Tache tacheCourante) {
         Query query = null;
        if (em.find(Tache.class, tacheCourante.getId()) != null) {
            query = em.createNamedQuery("Commmentaire.findByTache");
            query.setParameter("tache", tacheCourante);
        }
        return query.getResultList();
    }

    public void modificationTache(Tache tacheCourante, int intValue, Long revisionParam, ImportanceEnum importanceParam) throws TacheException {
        tacheCourante.setImportance(importanceParam);
        tacheCourante.setPourcentage(intValue);
        tacheCourante.setSVNRevision(revisionParam);
        em.merge(tacheCourante);
    }

    public Collection<Tache> getAllTimerLaunched() {
        Query query = em.createNamedQuery("Tache.findTimerLaunched");
        return query.getResultList();
    }

    public void archiverTache(Long id) {
        Tache tache = em.find(Tache.class, id);
        tache.setArchive(true);
        em.merge(tache);
    }

    public void desarchiverTache(Long id) {
        Tache tache = em.find(Tache.class, id);
        tache.setArchive(false);
        em.merge(tache);
    }

    public Collection<Tache> getTaches(Boolean archive, Projet p) {
        Query query;
        if (archive == null) {
            query = em.createNamedQuery("Tache.findTachesByProjet");
        } else {
            if (archive) {
                query = em.createNamedQuery("Tache.findTachesArchiveesByProjet");
            } else {
                query = em.createNamedQuery("Tache.findTachesNonArchiveesByProjet");
            }
        }
        query.setParameter("projet", p);
        return query.getResultList();
    }
}
