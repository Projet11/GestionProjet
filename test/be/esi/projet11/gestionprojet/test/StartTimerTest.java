/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author G34754
 */
public class StartTimerTest {

    private static EJBContainer container;
    private static HashMap<Object, Object> properties;

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
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestLancerTimer1() {
//        try {
//            TacheEJBLocal instance = (TacheEJBLocal) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
//            Tache tache=instance.creerTache("tache1", "   ");
//            instance.startTimer(tache.getId());
//            assertTrue(instance.isTimerLaunched(tache.getId()));
//        } catch (TacheException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Test
    public void TestLancerTimer2() {
//        try {
//            TacheEJBLocal instance = (TacheEJBLocal) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
//            assertFalse(instance.isTimerLaunched(instance.creerTache("tache2", "   ").getId()));
//        } catch (TacheException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Test
    public void TestLancerTimer3() {
//        try {
//            TacheEJBLocal instance = (TacheEJBLocal) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
//            Tache tache=instance.creerTache("tache3", "   ");
//            instance.startTimer(tache.getId());
//            Date debutTimer = new Date();
//            debutTimer.setTime(debutTimer.getTime()+5000l);
//            Date curr = new Date();
//            while(curr.compareTo(debutTimer)<0){
//                curr=new Date();
//            }
//            Time t=instance.getTimer(tache.getId());
//            assertTrue(instance.getTimer(tache.getId()).compareTo(new Time(4950l)) >= 0);
//            assertTrue(instance.getTimer(tache.getId()).compareTo(new Time(5050l)) <= 0);
//        } catch (TacheException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @Test
    public void TestLancerTimer4() {
//        try {
//            TacheEJBLocal instance = (TacheEJBLocal) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
//            Tache tache=instance.creerTache("tache4", "   ");
//            instance.startTimer(tache.getId());
//            Date debutTimer = new Date();
//            debutTimer.setTime(debutTimer.getTime()+3000l);
//            Date curr = new Date();
//            while(curr.compareTo(debutTimer)<0){
//                curr=new Date();
//            }
//            assertFalse(instance.getTimer(tache.getId()).compareTo(new Time(5000l)) >= 0);
//        } catch (TacheException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(StartTimerTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
