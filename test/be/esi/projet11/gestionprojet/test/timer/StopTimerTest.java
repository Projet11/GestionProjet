/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.timer;

import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.DBException;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G34754
 */
public class StopTimerTest {

    private static EJBContainer container;
    private static HashMap<Object, Object> properties;
    private static TacheEJB tacheEJB;
    private static ProjetEJB projetEJB;

    public StopTimerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        projetEJB = (ProjetEJB) container.getContext().lookup("java:global/GestionProjet/classes/ProjetEJB");

        tacheEJB = (TacheEJB) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() throws TacheException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestStopTimer1() throws DBException {
        Projet projet=projetEJB.creerProjet("projet1", "projet1");
        Tache tache = tacheEJB.creerTache("tache1", "   ", ImportanceEnum.IMPORTANT, projet);
        tache.setTimerLaunched();
        tacheEJB.saveTache(tache);
        Tache tachePersistee = tacheEJB.getTache(tache.getId());
        tachePersistee.setTimerLaunched();
        tacheEJB.saveTache(tachePersistee);
        tache = tacheEJB.getTache(tachePersistee.getId());
        assertFalse(tache.isTimerLaunched());
    }

    @Test
    public void TestStopTimer2() throws DBException {
        Projet projet=projetEJB.creerProjet("projet2", "");
        Tache tache = tacheEJB.creerTache("tache2", "   ", ImportanceEnum.IMPORTANT, projet);
        tache.setTimerLaunched();
        tacheEJB.saveTache(tache);
        Date finTimer = new Date();
        finTimer.setTime(finTimer.getTime() + 3000l);
        Date curr = new Date();
        while (curr.compareTo(finTimer) < 0) {
            curr = new Date();
        }
        Tache tachePersistee = tacheEJB.getTache(tache.getId());
        tachePersistee.setTimerLaunched();
        tacheEJB.saveTache(tachePersistee);
        tache = tacheEJB.getTache(tachePersistee.getId());
        assertTrue(tache.getTempsPasseSurTache() >= 3000 && tache.getTempsPasseSurTache() <= 3500);
    }

    @Test
    public void TestStopTimer3() throws DBException {
        Projet projet=projetEJB.creerProjet("projet3", "");
        Tache tache = tacheEJB.creerTache("tache3", "   ", ImportanceEnum.IMPORTANT, projet);
        tache.setTimerLaunched();
        tacheEJB.saveTache(tache);
        Date finTimer = new Date();
        finTimer.setTime(finTimer.getTime() + 5000l);
        Date curr = new Date();
        while (curr.compareTo(finTimer) < 0) {
            curr = new Date();
        }
        Tache tachePersistee = tacheEJB.getTache(tache.getId());
        tachePersistee.setTimerLaunched();
        tacheEJB.saveTache(tachePersistee);
        tache = tacheEJB.getTache(tachePersistee.getId());
        assertFalse(tache.getTempsPasseSurTache() >= 3000 && tache.getTempsPasseSurTache() <= 3500);
    }
}
