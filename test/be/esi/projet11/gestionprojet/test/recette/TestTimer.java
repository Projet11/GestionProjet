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
public class TestTimer extends TestDeBase {
            
      @Test
    public void testChangementDeProjet() {
        seConnecter();
        attendre();
        selenium.click("link=Projet 1");
        attendre();
        selenium.click("id=formAfficherTaches:resultat:0:demarreTimer");
        attendre();
        selenium.click("link=Timers");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("Tache1 démarrée"));
        selenium.click("id=navform:stopTimerForm:boutonArreter1");
        attendre();
        selenium.click("link=Timers");
        attendre();
        Assert.assertFalse(selenium.isTextPresent("Tache1 démarrée"));
    }
}
