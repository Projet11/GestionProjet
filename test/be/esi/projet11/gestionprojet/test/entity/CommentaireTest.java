/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.entity.Commentaire;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g34840
 */
public class CommentaireTest {

    static Tache tache;
    static Membre membre;
    static Projet projet;
    static Membre modifMembre;
    static Commentaire comment;
    static Tache modifTache;
    static Date date;
    static Date modifDate;

    public CommentaireTest() {
    }

    @BeforeClass
    public static void setUpClass() throws TacheException {
        projet = new Projet(0l, "abc");
        tache = new Tache("abc", "defgh", ImportanceEnum.NORMALE, projet);
        membre = new Membre("abc@hotmail.com");
        modifMembre = new Membre("alouette@gmail.com");
        date = new Date();
        modifDate = new Date(new Date().getTime() + 1);
        comment = new Commentaire(tache, membre, "Hello", date);
        modifTache = new Tache("321231231", "defgh", ImportanceEnum.NORMALE, projet);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setMembre method, of class Commentaire.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMembreNull() {
        System.out.println("SetMembreNull");
        comment.setMembre(null);
    }

    /**
     * Test of setMembre method, of class Commentaire.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetMembreAlreadyAssigned() {
        System.out.println("SetMembreAlreadyAssigned");
        comment.setMembre(modifMembre);
    }

    /**
     * Test of setCommentaire method, of class Commentaire.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCommentaireNull() {
        System.out.println("SetCommentaireNull");
        comment.setCorps(null);
    }

    /**
     * Test of setCommentaire method, of class Commentaire.
     */
    @Test()
    public void testSetCommentaire() {
        System.out.println("setCommentaireTrue");
        String newComment = "olleH";
        comment.setCorps(newComment);
        assertEquals(comment.getCorps(), newComment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTacheNull() {
        System.out.println("setTacheNull");
        comment.setTache(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTacheAlreadyAssigned() {
        System.out.println("SetTacheAlreadyAssigned");
        comment.setTache(modifTache);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateNull() {
        System.out.println("SetDateNull");
        comment.setDate(null);
    }

    public void testSetDate() {
        System.out.println("SetDateTrue");
        comment.setDate(modifDate);
        assertEquals(comment.getDate(), modifDate);
    }

    public void persist(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>GestionProjetPU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
