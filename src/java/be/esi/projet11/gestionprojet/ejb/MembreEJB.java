/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.exception.DBException;
import be.esi.projet11.gestionprojet.exception.MailException;
import be.esi.projet11.gestionprojet.exception.ProjetException;
import be.esi.projet11.gestionprojet.mail.Mailer;
import java.util.Collection;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author g35001
 */
@Stateless
public class MembreEJB {

    @PersistenceContext(unitName = "GestionProjetPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public Membre getUserByAuthentification(String login, String password) throws DBException {
        try {
            Query qry = em.createNamedQuery("Membre.findByAuthentification");
            qry.setParameter("login", login);
            qry.setParameter("password", password);
            return (Membre) qry.getSingleResult();
        } catch (Exception e) {
            throw new DBException("Impossible d'identifier le client !\n"+e.getMessage());
        }
    }

    public Membre getById(long id) {
        return em.find(be.esi.projet11.gestionprojet.entity.Membre.class, id);
    }

    public Collection<Membre> getAllUsers() throws DBException {
        try {
            return em.createNamedQuery("Membre.findAll").getResultList();
        } catch (Exception e) {
            throw new DBException("Impossible de charger l'ensemble des membres !\n");
        }
    }

    public Membre updateUser(Long id, String login, String password, String mail, String nom, String prenom) throws DBException {
        Membre mbr = em.find(Membre.class, id);
        if (mbr == null) {
            throw new DBException("Aucun utilisateur ne possède cet identifiant.\n");
        }
        mbr.setLogin(login);
        mbr.setNom(nom);
        mbr.setMail(mail);
        mbr.setPrenom(prenom);
        mbr.setPassword(password);
        return mbr;
    }

    public Membre addUser(String login, String password, String mail, String nom, String prenom) throws DBException {
        String erreur = "Impossible d'enregistrer ce nouveau client !\n";
        try {
            /*
             * On vérifie si le login et/ou l'email existent car il n'est plus 
             * possible de le faire après le em.persist(usr);
             * car la transaction sera cloturée par em.flush(); Il ne sera donc 
             * plus possible d'accéder à la BD grâce à 
             * cette transaction !
             */
            if (userExists(login)) {
                erreur += "L'identifiant est déjà utilisé.\n";
            }
            if (mailExists(mail)) {
                erreur += "L'adresse mail est déjà utilisée.\n";
            }
            Membre usr = new Membre(0l, login, password, mail, nom, prenom);
            em.persist(usr);
            em.flush();
            Mailer.send(mail, "GestionProjet - Inscription", "Mlle, Mme, Mr " + nom + "\n"
                    + "Votre inscription a été validée.\n"
                    + "Votre login est : " + login);
            return usr;
        } catch (PersistenceException e) {
            throw new DBException(erreur, e);
        } catch (ConstraintViolationException e) {
            erreur += "Un ou plusieurs champ contiennent trop de caractères.\n";
            Set<ConstraintViolation<?>> contraintes = e.getConstraintViolations();
            for (ConstraintViolation<?> c : contraintes) {
                erreur += c.getPropertyPath().toString() + " : " + c.getMessage() + "\n";
            }
            throw new DBException(erreur);
        } catch (IllegalArgumentException e) {
            erreur += e.getMessage();
            throw new DBException(erreur);
        } catch (MailException e) {
            erreur += e.getMessage();
            throw new DBException(erreur);
        } catch (Exception e) {
            erreur += "Erreur interne inattendue : " + e;
            throw new DBException(erreur);
        }
    }

    public boolean userExists(String login) {
        boolean exists = true;
        try {
            if (login == null) {
                return false;
            }
            Query qry = em.createNamedQuery("Membre.findByLogin");
            qry.setParameter("login", login);
            qry.getSingleResult();
        } catch (NoResultException e) {
            exists = false;
        }
        return exists;
    }

    public boolean mailExists(String mail) {
        try {
            if (mail == null) {
                return false;
            }
            Membre mbr = getMembreByEmail(mail);
            if (mbr == null) {
                return false;
            }
        } catch (DBException e) {
            return false;
        }
        return true;
    }

    public Membre getMembreById(long mbrId) {
        return em.find(Membre.class, mbrId);
    }

    public void ajoutMembreProjet(String email, Projet projet) throws DBException {
        if (email == null || email.equals("")) {
            return;
        }
        Membre mbr = getMembreByEmail(email);
        if (mbr == null) {
            mbr = new Membre(email);
            em.persist(mbr);
        }
        try {
            projet.ajouterMembre(mbr);
        } catch (ProjetException ex) {
            throw new DBException("Ajout du membre au projet impossible : " + ex.getMessage());
        }
        em.merge(projet);
    }

    public Membre getMembreByEmail(String email) throws DBException {
        Membre mbr;
        Query q = em.createNamedQuery("Membre.findByMail");
        q.setParameter("mail", email);
        try {
            mbr = (Membre) q.getSingleResult();
            return mbr;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
