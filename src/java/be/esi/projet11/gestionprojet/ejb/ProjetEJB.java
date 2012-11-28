/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
