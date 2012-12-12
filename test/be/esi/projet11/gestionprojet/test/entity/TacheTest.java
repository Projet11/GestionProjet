/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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

    @Test
    public void testAjouterUnMembreOk() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 1", "Tâche de test 1");
        tache.addMembre(new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAjouterUnMembreNull() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 2", "Tâche de test 2");
        tache.addMembre(null);
    }

    @Test
    public void testMembreEstSurTache() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 3", "Tâche de test 3");
        Membre membre = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");
        Membre membrePasPresent = new Membre(1l, "Membre 2", "pass", "mail2@example.com", "Nom2", "Prenom2");
        tache.addMembre(membre);

        Assert.assertTrue(tache.hasMembre(membre));
        Assert.assertFalse(tache.hasMembre(membrePasPresent));
    }

    @Test
    public void testNbMembreEnAjoutantDeuxFoisLeMemeMembre() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 5", "Tâche de test 5");
        Membre membre = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");

        tache.addMembre(membre);
        tache.addMembre(membre);

        Assert.assertEquals(tache.getNbMembres(), 1);
    }

    @Test
    public void testNbMembresAvecDeuxMembres() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 6", "Tâche de test 6");
        Membre membre1 = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");
        Membre membre2 = new Membre(1l, "Membre 2", "pass", "mail2@example.com", "Nom2", "Prenom2");

        tache.addMembre(membre1);
        tache.addMembre(membre2);

        Assert.assertEquals(2, tache.getNbMembres());
    }

    @Test
    public void testRecupererListeMembresSansMembre() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 7", "Tâche de test 7");
        Collection<Membre> membres = tache.getMembres();
        Collection<Membre> listeVide = new ArrayList<Membre>();

        Assert.assertArrayEquals(membres.toArray(), listeVide.toArray());
    }

    @Test
    public void testRecupererListeMembresPlusieursMembres() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 8", "Tâche de test 8");
        Membre membre1 = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");
        Membre membre2 = new Membre(1l, "Membre 2", "pass", "mail2@example.com", "Nom2", "Prenom2");

        tache.addMembre(membre1);
        tache.addMembre(membre2);

        Collection<Membre> membres = tache.getAllMembres();
        Collection<Membre> attendu = new ArrayList<Membre>();
        attendu.add(membre1);
        attendu.add(membre2);

        Assert.assertArrayEquals(membres.toArray(), attendu.toArray());
    }

    @Test
    public void testRecupererListeMembresPlusieursMembresNonActifs() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tâche 9", "Tâche de test 9");
        Membre membre1 = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");
        Membre membre2 = new Membre(0l, "Membre 2", "pass", "mail2@example.com", "Nom2", "Prenom2");

        tache.addMembre(membre1);
        tache.addMembre(membre2);

        Collection<Membre> membres = tache.getMembres();
        Collection<Membre> attendu = new ArrayList<Membre>();

        Assert.assertArrayEquals(membres.toArray(), attendu.toArray());
    }

    @Test
    public void testCompterMembresAvantAcceptation() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tache 10", "Tâche de test 10");

        Membre membre1 = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");

        tache.addMembre(membre1);
        assertEquals(0, tache.getParticipantsAcceptes().size());
    }

    @Test
    public void testCompterMembresApresAcceptation() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tache 11", "Tâche de test 11");

        Membre membre1 = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");

        tache.addMembre(membre1);
        tache.accepterMembre(membre1);
        assertEquals(1, tache.getParticipantsAcceptes().size());
    }

    @Test
    public void testCompterMembresApresAcceptation2() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tache 12", "Tâche de test 12");

        Membre membre1 = new Membre(1l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");
        Membre membre2 = new Membre(2l, "Membre 2", "pass", "mail2@example.com", "Nom2", "Prenom2");

        tache.addMembre(membre1);
        tache.accepterMembre(membre1);
        tache.addMembre(membre2);
        tache.accepterMembre(membre2);
        assertEquals(2, tache.getParticipantsAcceptes().size());
    }

    @Test
    public void testAccepterMemeMembreDeuxFois() throws TacheException {
        Tache tache = null;
        tache = new Tache("Tache 13", "Tâche de test 13");

        Membre membre1 = new Membre(0l, "Membre 1", "pass", "mail@example.com", "Nom1", "Prenom1");

        tache.addMembre(membre1);
        tache.accepterMembre(membre1);
        tache.accepterMembre(membre1);
        assertEquals(1, tache.getParticipantsAcceptes().size());
    }
}
