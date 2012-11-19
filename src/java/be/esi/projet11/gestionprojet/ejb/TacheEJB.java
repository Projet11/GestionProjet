/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.controller.FrontController;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
*
* @author g34840
*/
@ManagedBean(name = "tacheEJB")
@SessionScoped
public class TacheEJB {

    @ManagedProperty("#{MembreManage}")
    private MembreManage membreBean;

    /**
* Get the value of membreBean
*
* @return the value of membreBean
*/
    public MembreManage getMembreBean() {
        return membreBean;
    }

    public TacheEJB() {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("GestionProjetPU");
        em=emf.createEntityManager();
    }

    /**
* Set the value of membreBean
*
* @param membreBean new value of membreBean
*/
    public void setMembreBean(MembreManage membreBean) {
        this.membreBean = membreBean;
    }
    private Tache tache;

    /**
* Get the value of tache
*
* @return the value of tache
*/
    public Tache getTache() {
        return tache;
    }

    /**
* Set the value of tache
*
* @param tache new value of tache
*/
    public void setTache(Tache tache) {
        this.tache = tache;
    }
    private EntityManager em;

    public Tache creerTache(String nom, String description) throws TacheException {
        Tache uneTache = new Tache(nom, description);
        System.out.println("tache: "+uneTache);
        System.out.println("em: "+em);
        em.persist(uneTache);
        tache = uneTache;
        return uneTache;
    }

    public Tache creerTache(String nom, String description, ImportanceEnum importance) throws TacheException {
        Tache uneTache = new Tache(nom, description, importance);
        em.persist(uneTache);
        tache = uneTache;
        return uneTache;
    }

    public Tache getTache(String nom) {
        Query query = em.createNamedQuery("Tache.findByNom");
        query.setParameter("nom", nom);
        return (Tache) query.getSingleResult();
    }

    public Tache getTache(long id) {
        return em.find(Tache.class, id);
    }

    public Tache getCurrentTache() {
        return tache;
    }

    public Collection<Tache> getAllTache() {
        Query query = em.createNamedQuery("Tache.findAll");
        return query.getResultList();

    }

    public void startTimer() {
        tache.setTimerLaunched(true);
    }

    public void stopTimer() {
        tache.setTimerLaunched(true);
    }

    public Time getTimer() {
        Date currDate = new Date();
        return new Time(currDate.getTime() - tache.getDateDeb().getTime());
    }

    public boolean isTimerLaunched() {
        return tache.isTimerLaunched();
    }

    public String inscrireMembresATache() {
        for (Membre membre : membreBean.getMembreSel()) {
            tache.addMembre(membre);
        }
        em.merge(tache);
        return "success";
    }
}