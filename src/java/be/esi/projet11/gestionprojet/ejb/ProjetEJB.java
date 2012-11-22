/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Projet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author g32460
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

}
