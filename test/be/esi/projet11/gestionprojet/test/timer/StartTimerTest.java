/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.timer;

import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class StartTimerTest {

    private static EJBContainer container;
    private static HashMap<Object, Object> properties;
    private static TacheEJB tacheEJB;

    public StartTimerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
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
    public void TestLancerTimer1() throws TacheException {
        Tache tache = tacheEJB.creerTache("tache1", "   ");
        tache.setTimerLaunched(true);
        tacheEJB.saveTache(tache);
        Tache tachePersistee = tacheEJB.getTache(tache.getId());
        assertTrue(tachePersistee.isTimerLaunched());
    }

    @Test
    public void TestLancerTimer2() throws TacheException {
        Tache tache = tacheEJB.creerTache("tache2", "   ");
        tacheEJB.saveTache(tache);
        Tache tachePersistee = tacheEJB.getTache(tache.getId());
        assertFalse(tachePersistee.isTimerLaunched());
    }

    @Test
    public void TestLancerTimer3() throws TacheException {
        Tache tache = tacheEJB.creerTache("tache3", "   ");
        tache.setTimerLaunched(true);
        Date debutTimer = new Date();
        debutTimer.setTime(debutTimer.getTime() + 5000l);
        Date curr = new Date();
        while (curr.compareTo(debutTimer) < 0) {
            curr = new Date();
        }
        Time t = tache.getTimer();
        assertTrue(t.compareTo(new Time(4950l)) >= 0);
        assertTrue(t.compareTo(new Time(5050l)) <= 0);
    }

    @Test
    public void TestLancerTimer4() throws TacheException {
        Tache tache = tacheEJB.creerTache("tache4", "   ");
        tache.setTimerLaunched(true);
        Date debutTimer = new Date();
        debutTimer.setTime(debutTimer.getTime() + 3000l);
        Date curr = new Date();
        while (curr.compareTo(debutTimer) < 0) {
            curr = new Date();
        }
        assertFalse(tache.getTimer().compareTo(new Time(5000l)) >= 0);
    }
    
    
}
