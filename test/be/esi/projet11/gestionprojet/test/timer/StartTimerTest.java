/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.timer;

import be.esi.projet11.gestionprojet.ejb.TacheEJBOLD;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
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
    private TacheEJBOLD instance;

    public StartTimerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() throws TacheException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        if (instance == null) {
            instance = new TacheEJBOLD();
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestLancerTimer1() {
        try {
            Tache tache = instance.creerTache("tache1", "   ");
            instance.setTache(tache);
            instance.startTimer(tache);
            assertTrue(instance.isTimerLaunched());
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void TestLancerTimer2() {
        try {
            Tache tache = instance.creerTache("tache2", "   ");
            instance.setTache(tache);
            assertFalse(instance.isTimerLaunched());
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void TestLancerTimer3() {
        try {
            Tache tache = instance.creerTache("tache3", "   ");
            instance.setTache(tache);
            instance.startTimer(tache);
            Date debutTimer = new Date();
            debutTimer.setTime(debutTimer.getTime() + 5000l);
            Date curr = new Date();
            while (curr.compareTo(debutTimer) < 0) {
                curr = new Date();
            }
            Time t = instance.getTimer();
            assertTrue(instance.getTimer().compareTo(new Time(4950l)) >= 0);
            assertTrue(instance.getTimer().compareTo(new Time(5050l)) <= 0);
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void TestLancerTimer4() {
        try {
            Tache tache = instance.creerTache("tache4", "   ");
            instance.setTache(tache);
            instance.startTimer(tache);
            Date debutTimer = new Date();
            debutTimer.setTime(debutTimer.getTime() + 3000l);
            Date curr = new Date();
            while (curr.compareTo(debutTimer) < 0) {
                curr = new Date();
            }
            assertFalse(instance.getTimer().compareTo(new Time(5000l)) >= 0);
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TestArretTimer1(){
        try {
            Tache tache = instance.creerTache("tache4", "   ");
            instance.setTache(tache);
            instance.startTimer(tache);
            instance.stopTimer(tache);
            assertTrue(instance.isTimerLaunched());
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TestArretTimer2(){
        try {
            Tache tache = instance.creerTache("tache4", "   ");
            instance.setTache(tache);
            instance.startTimer(tache);
            assertFalse(instance.isTimerLaunched());
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TestArretTimer3(){
        try {
            Tache tache = instance.creerTache("tache4", "   ");
            instance.setTache(tache);
            instance.startTimer(tache);
            Date debutTimer = new Date();
            debutTimer.setTime(debutTimer.getTime() + 4900l);
            Date curr = new Date();
            while (curr.compareTo(debutTimer) < 0) {
                curr = new Date();
            }
            instance.stopTimer(tache);
            assertTrue((instance.getCurrentTache().getTempsPasseSurTache()).compareTo(new Date(5000l)) <= 0);
        } catch (TacheException ex) {
            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
