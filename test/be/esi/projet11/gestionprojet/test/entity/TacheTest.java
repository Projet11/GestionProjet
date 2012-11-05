/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        assertTrue(tache.equals(new Tache("1", "a", ImportanceEnum.TRESIMPORTANT)));
    }

    @Test(expected = TacheException.class)
    public void testCreateTacheExceptionParNom() throws TacheException {
        Tache tache = new Tache("", "a", ImportanceEnum.TRESIMPORTANT);
    }

    @Test
    public void testGetPourcentageNewTache() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        assertEquals(new Double(0), new Double(tache.getPourcentage()));
    }

    @Test
    public void testGetPourcentageMidTache() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(50);
        assertEquals(new Double(50), new Double(tache.getPourcentage()));
    }

    @Test(expected = TacheException.class)
    public void testSetPourcentageNegatif() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(-1);
    }

    @Test(expected = TacheException.class)
    public void testSetPourcentageOverflow() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(101);
    }

    @Test
    public void testGetPourcentageEndTache() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(100);
        assertEquals(new Double(100), new Double(tache.getPourcentage()));
    }

    @Test
    public void testGetSVNRevisionNewTache() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        assertNull(tache.getSVNRevision());
    }

    @Test
    public void testGetSVNRevisionMidTache() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(100);
        tache.setSVNRevision(50L);
        assertEquals(50L, (long) tache.getSVNRevision());
    }

    @Test(expected = TacheException.class)
    public void testSetSVNRevisionNegative() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(100);
        tache.setSVNRevision(-1L);
    }

    @Test(expected = TacheException.class)
    public void testSetSVNRevisionZero() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(100);
        tache.setSVNRevision(0L);
    }

    @Test(expected = TacheException.class)
    public void testSetSVNRevisionNull() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(100);  
        tache.setSVNRevision(null);
    }

    @Test(expected = TacheException.class)
    public void testSetSVNRevisionTacheNotFinish() throws TacheException {
        Tache tache = new Tache("1", "a", ImportanceEnum.TRESIMPORTANT);
        tache.setPourcentage(99);
        tache.setSVNRevision(50L);
    }
}
