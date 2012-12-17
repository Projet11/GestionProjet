/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

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
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    public void remplirChamps(String identifiant, String mdp) {
        selenium.open("/GestionProjet/pages/connexion.xhtml");
        selenium.type("id=formIdentification:txtNom", identifiant);
        selenium.type("id=formIdentification:txtPass", mdp);
    }

    @Test
    public void testIdentifiantReussiAvecLogin() {
        remplirChamps("mbr", "mbr");
        boolean ok = cliquerEtTesterMessage(deconnexionButtonText);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantReussiAvecEmail() {
        remplirChamps("mbr@mbr.be", "mbr");
        boolean ok = cliquerEtTesterMessage(deconnexionButtonText);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecEmailInvalide() {
        remplirChamps("pluquezeftt@gmail.com", "mbr");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecLoginIncorrecte() {
        remplirChamps("zefze", "mbr");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecLoginValideEtMDPIncorrect() {
        remplirChamps("mbr", "ergyre");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecEmailValideEtMDPIncorrect() {
        remplirChamps("mbr@mbr.be", "ergyre");
        boolean ok = cliquerEtTesterMessage(connexionEchouee);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecLoginVide() {
        remplirChamps("", "mbr");
        boolean ok = cliquerEtTesterMessage(messageChampVide);
        Assert.assertTrue(ok);
    }

    @Test
    public void testIdentifiantRateAvecMDPVide() {
        remplirChamps("mbr", "");
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
