/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.ParticipeProjet;
import be.esi.projet11.gestionprojet.entity.Projet;
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
        System.out.println("projet size" + projet.getAllParticipant().size());
        ParticipeProjet pp = projet.refuserParticipant(mbr);
        Query q = em.createQuery("delete from ParticipeProjet pp where pp.membre1.id = :idMembre and pp.projet1.id = :idProjet");
        q.setParameter("idMembre", mbr.getId());
        q.setParameter("idProjet", projet.getId());
        q.executeUpdate();
        System.out.println("projet size" + projet.getAllParticipant().size());
    }

    public List<Projet> getAllProjets() {
        Query query=em.createNamedQuery("Projet.findAll");
        return query.getResultList();
    }
    
    public Projet creerProjet(String nom, String description){
        Projet unProjet = new Projet(0l,nom, description);
        em.persist(unProjet);
        em.flush();
        return unProjet;        
    }

    public void accepterParticipant(Projet projet, Membre membre) {
        projet.accepterParticipant(membre);
        em.merge(projet);
    }
    
    
}
