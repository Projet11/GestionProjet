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
public class TestChoixProjet extends TestDeBase{
     @Test
    public void testChangementDeProjet() {
        seConnecter();
        attendre();
        selenium.click("link=Projet 1");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("Tache3"));
        selenium.click("link=Projet 2");
        attendre();
        Assert.assertFalse(selenium.isTextPresent("Tache3"));
        Assert.assertTrue(selenium.isTextPresent("Tache6"));
    }
    
}
