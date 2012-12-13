/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author g33252
 */
public class TestLogout extends TestDeBase {

    public TestLogout() {
    }


    @Test
    public void testLogoutReussi() {
        seConnecter();
        selenium.click("id=navform:logout");
        selenium.waitForPageToLoad("30000");
        boolean ok = selenium.isTextPresent("Connexion");
        Assert.assertTrue(ok);
    }
}
