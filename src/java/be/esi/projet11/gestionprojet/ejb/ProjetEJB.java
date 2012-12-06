/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.ParticipeProjet;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.exception.DBException;
import be.esi.projet11.gestionprojet.exception.ProjetException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author g34771
 */
@Stateless
public class ProjetEJB {

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;

    public Projet getProjetById(long projetId) {
        return em.find(Projet.class, projetId);
    }

    public Projet creerProjet() {
        Projet p = new Projet();
        em.persist(p);
        return p;
    }

    public void removeParticipeProjet(Projet projet, Membre mbr) {
        ParticipeProjet pp = projet.refuserParticipant(mbr);
        Query q = em.createQuery("delete from ParticipeProjet pp where pp.membre1.id = :idMembre and pp.projet1.id = :idProjet");
        q.setParameter("idMembre", mbr.getId());
        q.setParameter("idProjet", projet.getId());
        q.executeUpdate();
    }

    public List<Projet> getAllProjets() {
        Query query = em.createNamedQuery("Projet.findAll");
        return query.getResultList();
    }

    public Projet creerProjet(String nom, String description) throws DBException {
        Query query = em.createNamedQuery("Projet.findByNom");
        query.setParameter("nom", nom);
        Projet unProjet = null;
        try {
            unProjet = (Projet) query.getSingleResult();
            throw new DBException("Il y a deja un projet ayant ce nom");
        } catch (Exception e) {
            unProjet = new Projet(0l, nom, description);
            em.persist(unProjet);
            em.flush();
        }
        return unProjet;
    }

    public void accepterParticipant(Projet projet, Membre membre) {
        projet.accepterParticipant(membre);
        em.merge(projet);
    }
    
    
}
