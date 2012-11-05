/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.TacheException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author j4un3
 */
public class TacheTest {

    @BeforeClass
    public static void setUpClass() {
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

    @Test
    public void testCreateTache2ParametresReussi() throws TacheException {
        Tache tache = new Tache("1", "a");
        assertTrue(tache.equals(new Tache("1", "a")));
    }
        @Test
    public void testCreateTache3ParametresReussi() throws TacheException {
        Tache tache = new Tache("1", "a", "Très important");
        assertTrue(tache.equals(new Tache("1", "a", "Très important")));
    }

    @Test(expected = TacheException.class)
    public void testCreateTacheExceptionParNom() throws TacheException {
        Tache tache = new Tache("", "a", "Très important");
    }

    @Test(expected = TacheException.class)
    public void testCreateTacheExceptionParImportant() throws TacheException {
        Tache tache = new Tache("1", "a", "Pas très important");
    }
    
}
