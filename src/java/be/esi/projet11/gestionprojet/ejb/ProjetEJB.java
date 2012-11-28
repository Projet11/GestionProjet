/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.ParticipeProjet;
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
    
    public Projet creerProjet(String nom, String description){
        Projet unProjet = new Projet(0l,nom, description);
        em.persist(unProjet);
        em.flush();
        return unProjet;        
    }
    
    
    public Projet getProjetById(Long id){
        return em.find(Projet.class, id);
    }
    
    public Projet getProjet(){
        return em.find(Projet.class, 1l);
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
