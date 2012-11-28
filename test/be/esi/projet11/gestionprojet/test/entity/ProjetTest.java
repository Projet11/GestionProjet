/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g34771
 */
public class ProjetTest {
    
    public ProjetTest() {
    }
    
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
    public void testAjoutProjetNonAccepter(){
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        assertFalse(projet.getParticipantAcceptes().contains(mbr));
    }
    @Test
    public void testAjoutProjetMembreSansMail(){
        Projet projet = new Projet();
        Membre mbr = new Membre();
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        assertFalse(projet.getParticipantAcceptes().contains(mbr));
    }
    @Test
    public void testAjoutProjetAccepter(){
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        assertTrue(projet.getParticipantAcceptes().contains(mbr));
    }
    @Test
    public void testAjoutProjetMembreDejaPresent(){
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        assertTrue(projet.getParticipantAcceptes().size()==1);
    }
    
    @Test
    public void testAccepterMembreDejaAccepter(){
        Projet projet = new Projet();
        Membre mbr = new Membre("truc@machin.be");
        projet.setId(1l);
        mbr.setId(1l);
        projet.ajouterMembre(mbr);
        projet.accepterParticipant(mbr);
        projet.accepterParticipant(mbr);
        assertTrue(projet.getParticipantAcceptes().size()==1);
    }
}
