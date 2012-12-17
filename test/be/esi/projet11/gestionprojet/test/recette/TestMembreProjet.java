/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author g34771
 */
public class TestMembreProjet extends TestDeBase {

    @Test
    public void ajoutMembreEmailValide() {
        seConnecter();
        selenium.click("link=Projet 1");
        attendre();
        ajouteMembre("simon@dwebconsulting.eu");
        selenium.type("id=ajoutMembre:email", "");
        Assert.assertTrue(selenium.isTextPresent("simon@dwebconsulting.eu"));
    }
    @Test
    public void ajoutMembreEmailInvalide() {
        seConnecter();
        selenium.click("link=Projet 1");
        attendre();
        ajouteMembre("nimportequoi");
        selenium.type("id=ajoutMembre:email", "");
        Assert.assertFalse(selenium.isTextPresent("nimportequoi"));
    }
    
    private void ajouteMembre(String email){
        attendre();
        selenium.click("id=formAfficherTaches:ajoutMembreProjet");
        attendre();
        selenium.type("id=ajoutMembre:email", email);
        selenium.click("id=ajoutMembre:ajouter");
        attendre();
    }
}
