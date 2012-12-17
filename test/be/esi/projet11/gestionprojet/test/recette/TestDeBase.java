/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

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

    public void seConnecter() {
        selenium.open("/GestionProjet/pages/connexion.xhtml");
        selenium.type("id=formIdentification:txtNom", "mbr");
        selenium.type("id=formIdentification:txtPass", "mbr");
        selenium.click("id=formIdentification:login");
        attendre();
    }

    public TestDeBase() {
    }

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:14186");
        selenium.start();
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

    public void attendre() {
        try {
            selenium.waitForPageToLoad("4000");
        } catch (Exception ex) {
            //Cas normal quand la page n'est pas rechargée
        }
    }
}
