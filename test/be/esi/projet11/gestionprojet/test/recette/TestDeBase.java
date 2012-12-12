/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import be.esi.projet11.gestionprojet.entity.Membre;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author g33252
 */
public class TestDeBase {

    protected static Selenium selenium;
    private static Membre utilisateur;

    public static void creerUser() {
        //Nom, prenom, login, email, mdp
        utilisateur = new Membre(0l, "Pluquet", "Pluquet", "pluquet@gmail.com", "Pluquet", "Frederic");
    }

    public static void seConnecter() {
        System.out.println("seConnecter");
        if (selenium == null) {
            selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:26764");
            selenium.start();
        }
        selenium.open("/GestionProjet/pages/connexion.xhtml");
        selenium.type("id=formIdentification:txtNom", "Pluquet");
        selenium.type("id=formIdentification:txtPass", "Pluquet");
        selenium.click("id=formIdentification:login");
        selenium.waitForPageToLoad("30000");
    }

//    public static void supprimerUser() {
//        MembreController mc = new MembreController().
//        UserDB.supprimerUser(utilisateur);
//    }
    public TestDeBase() {
    }

    @Before
    public void setUp() throws Exception {
        /*if (selenium != null)
            return;*/

        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:26764");
        selenium.start();
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

    public void attendre() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println("Attention probleme sleep" + ex.getMessage());
        }
    }
}
