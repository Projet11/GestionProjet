package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Commentaire;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.ParticipeTache;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.DBException;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

    public Tache creerTache(String nom, String description, ImportanceEnum importance, Projet p) throws DBException {
        Tache uneTache = null;
        try {
            uneTache = new Tache(nom, description, importance, p);
            em.persist(uneTache);
        } catch (Exception ex) {
            throw new DBException("Création de tâche impossible : " + ex.getMessage());
        }
        return uneTache;
    }

    public Tache getTache(String nom) throws DBException {
        Tache tache = null;
        try {
            Query query = em.createNamedQuery("Tache.findByNom");
            query.setParameter("nom", nom);
            tache = (Tache) query.getSingleResult();
        } catch (Exception ex) {
            throw new DBException("Cette tache n'existe pas dans la base de donnée");
        }
        return tache;
    }

    public Tache getTache(long id) {
        return em.find(Tache.class, id);
    }

    public void saveTache(Tache tache) {
        if (em.find(Tache.class, tache.getId()) != null) {
            em.merge(tache);
        }
    }

    public void ajouterCommentaire(Tache tacheCourante, Membre membre, String commentaire) throws IllegalArgumentException{
        if (tacheCourante != null) {
            if (em.find(Tache.class, tacheCourante.getId()) != null) {
                Commentaire comment = new Commentaire(tacheCourante, membre, commentaire, new Date());
                em.persist(comment);
                tacheCourante.getConversation().add(comment);
            }
        } else {
            throw new IllegalArgumentException("La tache ne peut être null");
        }
    }

    public List<Commentaire> getConversation(Tache tacheCourante) {
        Query query = null;
        if (em.find(Tache.class, tacheCourante.getId()) != null) {
            query = em.createNamedQuery("Commentaire.findByTache");
            query.setParameter("tache", tacheCourante);
        }
        return query.getResultList();
    }

    public void modificationTache(Tache tacheCourante, int pourcentageParam, Long revisionParam, ImportanceEnum importanceParam) throws TacheException {
        tacheCourante.setImportance(importanceParam);
        tacheCourante.setPourcentage(pourcentageParam);
        tacheCourante.setSVNRevision(revisionParam);
        em.merge(tacheCourante);
    }

    public Collection<Tache> getAllTimerLaunched() {
        Query query = em.createNamedQuery("Tache.findTimerLaunched");
        return query.getResultList();
    }

    public void archiverTache(Tache tacheCourante) {
        tacheCourante.setArchive(true);
        em.merge(tacheCourante);
    }

    public void desarchiverTache(Tache tacheCourante) {
        tacheCourante.setArchive(false);
        em.merge(tacheCourante);
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

    public void ajouterMembre(Tache tache, Membre membre) {
        tache.accepterMembre(membre);
        em.merge(tache);
    }

    public void supprimerMembre(Tache tache, Membre membre) {
        ParticipeTache pt = tache.getParticipeTache(membre);
        tache.refuserParticipant(membre);
        Query q = em.createQuery("delete from ParticipeTache pt where pt.membre.id = :idMembre and pt.tache.id = :idTache");
        q.setParameter("idMembre", membre.getId());
        q.setParameter("idTache", tache.getId());
        q.executeUpdate();
        em.merge(tache);
    }
}
