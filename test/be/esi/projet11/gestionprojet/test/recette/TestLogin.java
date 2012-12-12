/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import com.thoughtworks.selenium.DefaultSelenium;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g33252
 */
public class TestLogin extends TestDeBase {

    public String connexionEchouee = "Identifiant ou mot de passe invalide";
    public String deconnexionButtonText = "Déconnexion";
    String messageChampVide = "Connexion";

    public TestLogin() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        creerUser();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
//        supprimerUser();
    }

    public void remplirChamps(String identifiant, String mdp) {
        selenium.open("/GestionProjet/pages/connexion.xhtml");
        selenium.type("id=formIdentification:txtNom", identifiant);
        selenium.type("id=formIdentification:txtPass", mdp);
    }

    @Test
    public void testIdentifiantReussiAvecLogin() {
        remplirChamps("Pluquet", "Pluquet");
        boolean ok = cliquerEtTesterMessage(deconnexionButtonText);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantReussiAvecEmail() {
        remplirChamps("pluquet@gmail.com", "Pluquet");
        boolean ok = cliquerEtTesterMessage(deconnexionButtonText);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecEmailInvalide() {
        remplirChamps("pluquezeftt@gmail.com", "Pluquet");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecLoginIncorrecte() {
        remplirChamps("zefze", "Pluquet");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecLoginValideEtMDPIncorrect() {
        remplirChamps("Pluquet", "ergyre");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecEmailValideEtMDPIncorrect() {
        remplirChamps("pluquet@gmail.com", "ergyre");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecLoginVide() {
        remplirChamps("", "Pluquet");
        boolean ok = cliquerEtTesterMessage(messageChampVide);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecMDPVide() {
        remplirChamps("Pluquet", "");
        boolean ok = cliquerEtTesterMessage(messageChampVide);
        Assert.assertTrue(ok);
    }

    private boolean cliquerEtTesterMessage(String messageAttendu) {
        selenium.click("id=formIdentification:login");
        attendre();
        boolean ok = selenium.isTextPresent(messageAttendu);
        return ok;
    }
}
