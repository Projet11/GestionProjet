/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.DBException;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author g33252
 */
public class TestDeBase {

    protected static Selenium selenium;
    private static HashMap<Object, Object> properties;
    private static EJBContainer container;
    private static MembreEJB membreEJB;

    public static void creerUser() throws DBException, NamingException {
        //Nom, prenom, login, email, mdp
//         properties = new HashMap<Object, Object>();
//        properties.put(EJBContainer.APP_NAME, "GestionProjet");
//        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
//        membreEJB = (MembreEJB) container.getContext().lookup("java:global/GestionProjet/classes/MembreEJB");
//        membreEJB.addUser("Pluquet", "Pluquet", "pluquet@gmail.com", "Pluquet", "Frederic");
    }

    public void seConnecter() {
        selenium.open("/GestionProjet/pages/connexion.xhtml");
        selenium.type("id=formIdentification:txtNom", "mbr");
        selenium.type("id=formIdentification:txtPass", "mbr");
        selenium.click("id=formIdentification:login");
        attendre();
    }

//    public static void supprimerUser() {
//        MembreController mc = new MembreController().
//        UserDB.supprimerUser(utilisateur);
//    }
    public TestDeBase() {
    }

    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:18217");
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
            //Cas normal quand la page n'est pas recharger
        }
    }
}
