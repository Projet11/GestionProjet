/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
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

    public void removeParticipeProjet(Projet projet, Membre mbr) throws DBException {
        try {
            projet.refuserParticipant(mbr);
        } catch (ProjetException ex) {
            throw new DBException("Retrait du membre de la liste des participants au projet impossible : "+ex.getMessage());
        }
        em.merge(projet);
        System.out.println("projet size" + projet.getAllParticipant().size());
    }

    public List<Projet> getAllProjets() {
        Query query = em.createNamedQuery("Projet.findAll");
        return query.getResultList();
    }

    public Projet creerProjet(String nom, String description) throws DBException {
        Projet unProjet = null;
        try {
            unProjet = new Projet(0l, nom, description);
        } catch (IllegalArgumentException ex) {
            throw new DBException("Création du projet impossible : "+ex.getMessage());
        }
        em.persist(unProjet);
        em.flush();
        return unProjet;
    }
}
