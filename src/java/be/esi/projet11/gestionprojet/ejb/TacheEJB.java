/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author g34840
 */
@Stateless
public class TacheEJB implements TacheEJBLocal {

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;

    @Override
    public Tache creerTache(String nom, String description) throws TacheException {
        Tache uneTache = new Tache(nom, description);
        em.persist(uneTache);
        return uneTache;
    }

    @Override
    public Tache creerTache(String nom, String description, ImportanceEnum importance) throws TacheException{
        Tache uneTache = new Tache(nom, description, importance);
        em.persist(uneTache);
        return uneTache;
    }

    @Override
    public Tache getTache(String nom) {
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", nom);
        return (Tache) query.getSingleResult();

    }

    @Override
    public Tache getTache(long id) {
        return em.find(Tache.class, id);
    }

    @Override
    public Collection<Tache> getAllTache() {
        Query query = em.createNamedQuery("Tache.findAll");
        return query.getResultList();

    }
}
