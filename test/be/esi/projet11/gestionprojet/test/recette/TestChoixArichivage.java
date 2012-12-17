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
public class TestChoixArichivage extends TestDeBase {

    @Test
    public void testClickArchivee() {
        seConnecter();
        attendre();
        selenium.click("link=Projet 1");
        attendre();
        selenium.click("id=formAfficherTaches:selection:1");
        attendre();
        Assert.assertFalse(selenium.isTextPresent("Tache2"));
    }
    
    @Test
    public void testClickNonArchivee() {
        seConnecter();
        attendre();
        selenium.click("link=Projet 1");
        attendre();
        selenium.click("id=formAfficherTaches:selection:2");
        attendre();
        Assert.assertFalse(selenium.isTextPresent("Tache3"));
    }
    
    @Test
    public void testClickNonArchiveePuisToutes() {
        seConnecter();
        attendre();
        selenium.click("link=Projet 1");
        attendre();
        selenium.click("id=formAfficherTaches:selection:2");
        attendre();
        selenium.click("id=formAfficherTaches:selection:0");
        attendre();
        Assert.assertTrue(selenium.isTextPresent("Tache3"));
        Assert.assertTrue(selenium.isTextPresent("Tache2"));
    }
}
