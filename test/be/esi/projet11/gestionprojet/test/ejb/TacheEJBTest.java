/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
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

    /**
     * Test of creerTache method, of class TacheEJB.
     */
    @Test
    public void testCreerTache() throws DBException {
        tacheEJB.creerTache("tache1", "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet1", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionNomNul() throws DBException {
        tacheEJB.creerTache(null, "une tache", ImportanceEnum.NORMALE, projetEJB.creerProjet("projet2", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionDescriptionNulle() throws DBException {
        tacheEJB.creerTache("tache3", null, ImportanceEnum.NORMALE, projetEJB.creerProjet("projet3", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionImportanceNulle() throws DBException {
        tacheEJB.creerTache("tache4", "une tache", null, projetEJB.creerProjet("projet4", "un projet"));
    }

    @Test(expected = DBException.class)
    public void testCreerTacheExceptionProjetNul() throws DBException {
        tacheEJB.creerTache("tache5", "une tache", ImportanceEnum.NORMALE, null);
    }

    /**
     * Test of getTache method, of class TacheEJB.
     */
    @Test
    public void testGetTacheNom() throws Exception {
        tacheEJB.getTache("tache1");
    }

    @Test
    public void testGetTacheId() throws Exception {
        tacheEJB.getTache(0l);
    }

    @Test
    public void testGetTacheIdNull() throws Exception {
        assertEquals(null, tacheEJB.getTache(10l));
    }

    @Test(expected = DBException.class)
    public void testGetTacheStringNull() throws Exception {
        assertEquals(null, tacheEJB.getTache("tache5"));
    }


    /**
     * Test of modificationTache method, of class TacheEJB.
     */
    @Test
    public void testModificationTache_Tache() throws Exception {
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
        tacheEJB.ajouterCommentaire(tacheEJB.getTache("tache1"), membreEJB.getUserByAuthentification("fred", "fred"), "EEEEELLLLOOOO");
        assertEquals(2, tacheEJB.getConversation(tacheEJB.getTache("tache1")).size());
    }

    /**
     * Test of getAllTimerLaunched method, of class TacheEJB.
     */
    @Test
    public void testGetAllTimerLaunched() throws Exception {
        Tache tache=tacheEJB.getTache("tache1");
        tache.setTimerLaunched();
        tacheEJB.saveTache(tache);
        assertEquals(1,tacheEJB.getAllTimerLaunched().size());
        tache.setTimerLaunched();
        tacheEJB.saveTache(tache);
        assertEquals(0,tacheEJB.getAllTimerLaunched().size());
    }
}
