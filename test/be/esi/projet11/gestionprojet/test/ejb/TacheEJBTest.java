package be.esi.projet11.gestionprojet.test.ejb;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import be.esi.projet11.gestionprojet.ejb.TacheEJBLocal;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    private static HashMap<Object, Object> properties;
    private TacheEJB instance;

    @BeforeClass
    public static void setUpClass() throws Exception {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
    }

    @Before
    public void setUp() throws Exception {
        if (instance == null)
            instance = new TacheEJB();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void creerTacheReussi() throws NamingException, TacheException {
        Tache uneTache = instance.creerTache("uneTache", "Lorem");
        Tache uneTache2 = new Tache("uneTache", "Lorem");
        assertEquals(uneTache, uneTache2);
    }

    @Test
    public void creerTacheDifférent() throws NamingException, TacheException {
        Tache uneTache = instance.creerTache("uneTache2", "");
        Tache uneTache2 = new Tache("uneAutreTache2", "Lorem");
        assertTrue(!uneTache.equals(uneTache2));
    }

    @Test(expected = TacheException.class)
    public void creerTacheException() throws NamingException, TacheException {
        instance.creerTache("", "");
    }

    @Test
    public void creerTacheSansDescription() throws NamingException, TacheException {
        instance.creerTache("3", "");
    }

    @Test
    public void getTacheParIdReussi() throws NamingException, TacheException {
        long id = instance.creerTache("4", "").getId();
        assertTrue(id == instance.getTache(id).getId());
    }

    @Test
    public void getTacheParNomReussi() throws NamingException, TacheException {
        String nom = instance.creerTache("5", "").getNom();
        System.out.println(nom);
        assertEquals(nom, instance.getTache("5").getNom());
    }

    @Test
    public void getTacheParNomDifferent() throws NamingException, TacheException {
        String nom = instance.creerTache("6", "").getNom();
        String nom2 = instance.creerTache("7", "").getNom();
        assertTrue(!nom.equals(instance.getTache("7").getNom()));
    }

    @Test
    public void getTacheParIdDifferent() throws NamingException, TacheException {
        long id = instance.creerTache("8", "").getId();
        long id2 = instance.creerTache("9", "").getId();
        assertTrue(id != instance.getTache(id2).getId());
    }

    @Test
    public void getAllTacheNombreDeTache() throws NamingException {
        assertEquals(9,instance.getAllTache().size());
    }

    @Test
    public void getAllTacheNomDeLaDerniereTache() throws NamingException {
        List<Tache> taches = new ArrayList<Tache>();
        Iterator<Tache> iterator = instance.getAllTache().iterator();
        while (iterator.hasNext()) {
            taches.add(iterator.next());
        }
        assertTrue(taches.get(8).getNom().equals("9"));
    }
}
