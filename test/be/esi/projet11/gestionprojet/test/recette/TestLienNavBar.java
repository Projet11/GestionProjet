/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author g34771
 */
public class TestLienNavBar extends TestDeBase {

    @Test
    public void testConnexionBouton() {
        selenium.open("/GestionProjet");
        attendre();
        selenium.click("id=connexion");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("Pas encore inscrit?"));
    }
    
    @Test
    public void testInscriptionBouton() {
        selenium.open("/GestionProjet");
        attendre();
        selenium.click("id=inscription");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("(confirmation)"));
    }
    
    @Test
    public void testAcceuil() {
        seConnecter();
        attendre();
        selenium.click("id=acceuil");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("Noms des projets"));
    }
    
    @Test
    public void testLogo() {
        seConnecter();
        attendre();
        selenium.click("id=logo");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("Noms des projets"));
    }
}
