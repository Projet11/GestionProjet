/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class TacheEJBTest {
    TacheEJB tacheEJB = lookupTacheEJBBean();

    private static EJBContainer container;
    private static ProjetEJB projetEJB;
    private static MembreEJB membreEJB;
    private static HashMap<Object, Object> properties;

    public TacheEJBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        projetEJB = (ProjetEJB) container.getContext().lookup("java:global/GestionProjet/classes/ProjetEJB");
        membreEJB = (MembreEJB) container.getContext().lookup("java:global/GestionProjet/classes/MembreEJB");
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of creerTache method, of class TacheEJB.
     */
    @Test
    public void testCreerTache() throws Exception {
        System.out.println("creerTache");
        
    }

    /**
     * Test of getTache method, of class TacheEJB.
     */
    @Test
    public void testGetTache_String() throws Exception {
        System.out.println("getTache");
        String nom = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        Tache expResult = null;
        Tache result = instance.getTache(nom);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTache method, of class TacheEJB.
     */
    @Test
    public void testGetTache_long() throws Exception {
        System.out.println("getTache");
        long id = 0L;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        Tache expResult = null;
        Tache result = instance.getTache(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTache method, of class TacheEJB.
     */
    @Test
    public void testGetAllTache() throws Exception {
        System.out.println("getAllTache");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        Collection expResult = null;
        Collection result = instance.getAllTache();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveTache method, of class TacheEJB.
     */
    @Test
    public void testSaveTache() throws Exception {
        System.out.println("saveTache");
        Tache tache = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.saveTache(tache);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificationTache method, of class TacheEJB.
     */
    @Test
    public void testModificationTache_Tache() throws Exception {
        System.out.println("modificationTache");
        Tache tacheCourante = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.modificationTache(tacheCourante);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterCommentaire method, of class TacheEJB.
     */
    @Test
    public void testAjouterCommentaire() throws Exception {
        System.out.println("ajouterCommentaire");
        Tache tacheCourante = null;
        Membre membre = null;
        String commentaire = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.ajouterCommentaire(tacheCourante, membre, commentaire);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConversation method, of class TacheEJB.
     */
    @Test
    public void testGetConversation() throws Exception {
        System.out.println("getConversation");
        Tache tacheCourante = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        List expResult = null;
        List result = instance.getConversation(tacheCourante);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificationTache method, of class TacheEJB.
     */
    @Test
    public void testModificationTache_4args() throws Exception {
        System.out.println("modificationTache");
        Tache tacheCourante = null;
        int intValue = 0;
        Long revisionParam = null;
        ImportanceEnum importanceParam = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.modificationTache(tacheCourante, intValue, revisionParam, importanceParam);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTimerLaunched method, of class TacheEJB.
     */
    @Test
    public void testGetAllTimerLaunched() throws Exception {
        System.out.println("getAllTimerLaunched");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        Collection expResult = null;
        Collection result = instance.getAllTimerLaunched();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of archiverTache method, of class TacheEJB.
     */
    @Test
    public void testArchiverTache() throws Exception {
        System.out.println("archiverTache");
        Tache tacheCourante = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.archiverTache(tacheCourante);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desarchiverTache method, of class TacheEJB.
     */
    @Test
    public void testDesarchiverTache() throws Exception {
        System.out.println("desarchiverTache");
        Tache tacheCourante = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.desarchiverTache(tacheCourante);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTaches method, of class TacheEJB.
     */
    @Test
    public void testGetTaches() throws Exception {
        System.out.println("getTaches");
        Boolean archive = null;
        Projet p = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        Collection expResult = null;
        Collection result = instance.getTaches(archive, p);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeParticipeTache method, of class TacheEJB.
     */
    @Test
    public void testRemoveParticipeTache() throws Exception {
        System.out.println("removeParticipeTache");
        Tache tache = null;
        Membre membre = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.removeParticipeTache(tache, membre);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterMembre method, of class TacheEJB.
     */
    @Test
    public void testAjouterMembre() throws Exception {
        System.out.println("ajouterMembre");
        Tache tache = null;
        Membre membre = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.ajouterMembre(tache, membre);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerMembre method, of class TacheEJB.
     */
    @Test
    public void testSupprimerMembre() throws Exception {
        System.out.println("supprimerMembre");
        Tache tache = null;
        Membre membre = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TacheEJB instance = (TacheEJB) container.getContext().lookup("java:global/classes/TacheEJB");
        instance.supprimerMembre(tache, membre);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private TacheEJB lookupTacheEJBBean() {
        try {
            Context c = new InitialContext();
            return (TacheEJB) c.lookup("java:global/GestionProjet/TacheEJB!be.esi.projet11.gestionprojet.ejb.TacheEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
