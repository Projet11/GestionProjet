/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.ParticipeProjet;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import javax.ejb.EJBException;
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

    public Projet CreerProjet() {
        Projet p = new Projet();
        em.persist(p);
        return p;
    }

    public void removeParticipeProjet(Projet projet, Membre mbr) {
        projet.refuserParticipant(mbr);
        em.merge(projet);
        System.out.println("projet size" + projet.getAllParticipant().size());
    }

    public Projet creerProjet(String nom, String description) {
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", nom);
        if (query.getSingleResult() == null) {
            Projet unProjet = new Projet(0l, nom, description);
            em.persist(unProjet);
            em.flush();
            return unProjet;
        } else {
            throw new EJBException("Le projet est déjà existant");
        }
    }
}