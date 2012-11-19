/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
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
    
    @Override
    public void startTimer(long id) {
        Tache tache=(Tache)em.find(Tache.class, id);
        tache.setTimerLaunched(true);
    }
    
    @Override
    public void stopTimer(long id) {
        Tache tache=(Tache)em.find(Tache.class, id);
        tache.setTimerLaunched(true);
    }

    @Override
    public Time getTimer(long id) {
        Tache tache=(Tache)em.find(Tache.class, id);
        Date currDate = new Date();
        return new Time(currDate.getTime() - tache.getDateDeb().getTime());
    }

    @Override
    public boolean isTimerLaunched(long id) {
        Tache tache=(Tache)em.find(Tache.class, id);
        return tache.isTimerLaunched();
    }
    
    /*@Override
    public String inscrireMembresATache(HttpServletRequest request) {
        Tache tache = null;
        String page = "";
        try {
            int tacheId = Integer.parseInt(request.getParameter("tache"));
            try {
                tache = em.find(Tache.class, 1l);
            } catch (Exception ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex); // FIXME
            }
            
            for (String strId : request.getParameterValues("membres")) {
                Long membreId = 0l;
                try {
                    membreId = Long.parseLong(strId);
                    //Membre membre = MembreEJB.getById(membreId);
                    Membre membre = new Membre(membreId, "Membre " + membreId, "Membre" + membreId + "@gmail.com");

                    tache.addMembre(membre);
                }
                catch (NumberFormatException ex) { // Ne devrait pas arriver
                    request.setAttribute("erreurTitre", "ID de membre invalide");
                    request.setAttribute("erreurContenu", "Attribution de membres à une tâche : id de membre invalide : " + strId);
                    page = "WEB-INF/pages/Erreur.jsp";
                }
            }
            
            em.merge(tache);
            page = "WEB-INF/pages/Tache.jsp";
        } catch (SecurityException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NumberFormatException ex) {
            request.setAttribute("erreurTitre", "Numéro de tâche invalide");
            request.setAttribute("erreurContenu", "Attribution de membres à une tâche : numéro de tâche invalide : " + request.getParameter("tache"));
            page = "WEB-INF/pages/Erreur.jsp";
        }
        
        System.out.println("returning " + page);
        return page;
    }*/
}
