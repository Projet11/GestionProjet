/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.exception.ProjetException;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g34771
 */
public class ProjetTest {

    public ProjetTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
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
    public void testAjoutProjetNonAccepter() throws ProjetException {
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        assertFalse(projet.getParticipantAcceptes().contains(mbr));
    }

    @Test(expected = ProjetException.class)
    public void testAjoutProjetMembreSansMail() throws ProjetException {
        Projet projet = new Projet();
        Membre mbr = new Membre();
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        assertFalse(projet.getParticipantAcceptes().contains(mbr));
    }

    @Test
    public void testAjoutProjetAccepter() throws ProjetException {
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        assertTrue(projet.getParticipantAcceptes().contains(mbr));
    }

    @Test
    public void testAjoutProjetMembreDejaPresent() throws ProjetException {
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        assertTrue(projet.getParticipantAcceptes().size() == 1);
    }

    @Test
    public void testAccepterMembreDejaAccepter() throws ProjetException {
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        projet.accepterParticipant(mbr);
        assertTrue(projet.getParticipantAcceptes().size() == 1);
    }

    @Test
    public void testCreerProjetSansParametre() {
        Projet projet = new Projet();
        assertTrue(projet.equals(new Projet()));
    }

    @Test
    public void testCreerProjetAvecNom() {
        Projet projet = new Projet(0l, "Projet 1");
        assertTrue(projet.equals(new Projet(0l, "Projet 1")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreerProjetAvecNomVide() {
        Projet projet = new Projet(0l, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreerProjetAvecNomNull() {
        Projet projet = new Projet(0l, null);
    }

    @Test
    public void testCreerProjetAvecNomEtDescription() {
        Projet projet = new Projet(0l, "Projet 1", "description");
        assertTrue(projet.equals(new Projet(0l, "Projet 1", "description")));
    }
}
