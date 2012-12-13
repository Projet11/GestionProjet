/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.DBException;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
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

    private static EJBContainer container;
    private static ProjetEJB projetEJB;
    private static MembreEJB membreEJB;
    private static TacheEJB tacheEJB;
    private static HashMap<Object, Object> properties;

    public TacheEJBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        tacheEJB = (TacheEJB) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
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

    @Test
    public void testGetAllTacheNull() throws Exception {
        System.out.println("getAllTacheNull");
        assertEquals(0, tacheEJB.getAllTache().size());
    }

    /**
     * Test of creerTache method, of class TacheEJB.
     */
    @Test
    public void testCreerTache() throws DBException {
        System.out.println("creerTache");
        tacheEJB.creerTache("tache1", "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet1", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionNomNul() throws DBException {
        System.out.println("creerTacheExceptionNomNul");
        tacheEJB.creerTache(null, "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet2", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionDescriptionNulle() throws DBException {
        System.out.println("creerTacheExceptionDescriptionNulle");
        tacheEJB.creerTache("tache3", null, ImportanceEnum.NORMALE, projetEJB.creerProjet("projet3", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionImportanceNulle() throws DBException {
        System.out.println("creerTacheExceptionNomNul");
        tacheEJB.creerTache("tache4", "une tache", null, projetEJB.creerProjet("projet4", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionProjetNul() throws DBException {
        System.out.println("creerTacheExceptionProjetNul");
        tacheEJB.creerTache("tache5", "une tache", ImportanceEnum.NORMALE, null);
    }

    /**
     * Test of getTache method, of class TacheEJB.
     */
    @Test
    public void testGetTacheNom() throws Exception {
        System.out.println("getTacheNom");
        tacheEJB.getTache("tache1");
    }

    @Test
    public void testGetTacheId() throws Exception {
        System.out.println("getTacheId");
        tacheEJB.getTache(0l);
    }

    @Test
    public void testGetTacheIdNull() throws Exception {
        System.out.println("getTacheIdNull");
        assertEquals(null, tacheEJB.getTache(10l));
    }

    @Test(expected = DBException.class)
    public void testGetTacheStringNull() throws Exception {
        System.out.println("getTacheStringNull");
        assertEquals(null, tacheEJB.getTache("tache5"));
    }

    /**
     * Test of getAllTache method, of class TacheEJB.
     */
    @Test
    public void testGetAllTache() throws Exception {
        System.out.println("getAllTache");
        assertEquals(1, tacheEJB.getAllTache().size());
    }

    @Test
    public void testGetAllTache5() throws Exception {
        System.out.println("getAllTache5");
        tacheEJB.creerTache("tache2", "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet6", "un projet"));
        tacheEJB.creerTache("tache3", "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet7", "un projet"));
        tacheEJB.creerTache("tache4", "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet8", "un projet"));
        tacheEJB.creerTache("tache5", "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet9", "un projet"));
        assertEquals(5, tacheEJB.getAllTache().size());
    }

    /**
     * Test of modificationTache method, of class TacheEJB.
     */
    @Test
    public void testModificationTache_Tache() throws Exception {
        System.out.println("modificationTache");
        tacheEJB.modificationTache(tacheEJB.getTache("tache1"), 100, 10l, ImportanceEnum.TRESIMPORTANT);
    }

    /**
     * Test of ajouterCommentaire method, of class TacheEJB.
     */
    @Test
    public void testAjouterCommentaire() throws Exception {
        tacheEJB.ajouterCommentaire(tacheEJB.getTache("tache1"), membreEJB.addUser("fred", "fred", "fred@hotmail.com", "fred", "fred"), "OLEEEEEE");
    }

    @Test(expected = Exception.class)
    public void testAjouterCommentaireExceptionTacheNull() throws Exception {
        tacheEJB.ajouterCommentaire(null, membreEJB.getUserByAuthentification("fred", "fred"), "OLEEEEEE");
    }

    /**
     * Test of getConversation method, of class TacheEJB.
     */
    @Test
    public void testGetConversation() throws Exception {
        System.out.println("getConversation");
        tacheEJB.ajouterCommentaire(tacheEJB.getTache("tache1"), membreEJB.getUserByAuthentification("fred", "fred"), "EEEEELLLLOOOO");
        assertEquals(2, tacheEJB.getConversation(tacheEJB.getTache("tache1")).size());
    }

    /**
     * Test of getAllTimerLaunched method, of class TacheEJB.
     */
    @Test
    public void testGetAllTimerLaunched() throws Exception {
        System.out.println("getAllTimerLaunched");
    }

    /**
     * Test of archiverTache method, of class TacheEJB.
     */
    @Test
    public void testArchiverTache() throws Exception {
        System.out.println("archiverTache");
    }

    /**
     * Test of desarchiverTache method, of class TacheEJB.
     */
    @Test
    public void testDesarchiverTache() throws Exception {
        System.out.println("desarchiverTache");
    }

    /**
     * Test of getTaches method, of class TacheEJB.
     */
    @Test
    public void testGetTaches() throws Exception {
        System.out.println("getTaches");
    }

    /**
     * Test of removeParticipeTache method, of class TacheEJB.
     */
    @Test
    public void testRemoveParticipeTache() throws Exception {
        System.out.println("removeParticipeTache");
    }

    /**
     * Test of ajouterMembre method, of class TacheEJB.
     */
    @Test
    public void testAjouterMembre() throws Exception {
        System.out.println("ajouterMembre");
    }

    /**
     * Test of supprimerMembre method, of class TacheEJB.
     */
    @Test
    public void testSupprimerMembre() throws Exception {
        System.out.println("supprimerMembre");
    }
}
