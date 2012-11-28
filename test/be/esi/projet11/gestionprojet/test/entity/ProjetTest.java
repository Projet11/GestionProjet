/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.entity;

import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.entity.Projet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g32460
 */
public class ProjetTest {
    
    static ProjetEJB instanceProjet;
    private static EJBContainer container;
    private static HashMap<Object, Object> properties;
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instanceProjet = (ProjetEJB) container.getContext().lookup("java:global/GestionProjet/classes/ProjetEJB");

    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreerProjetSansParametre(){
        Projet projet = new Projet();
        assertTrue(projet.equals(new Projet()));
    }
    
    @Test
    public void testCreerProjetAvecNom(){
        Projet projet = new Projet(0l,"Projet 1");
        assertTrue(projet.equals(new Projet(0l,"Projet 1")));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreerProjetAvecNomVide(){
        Projet projet = new Projet(0l,"");        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreerProjetAvecNomNull(){
        Projet projet = new Projet(0l,null);        
    }
    
    @Test (expected = EJBException.class)
    public void testCreerProjetAvecNomDejaExistant(){
        instanceProjet.creerProjet("Projet 1", null);
        instanceProjet.creerProjet("Projet 1", null);    
    }
	
    @Test
    public void testCreerProjetAvecNomEtDescription(){
        Projet projet = new Projet(0l,"Projet 1", "description");
        assertTrue(projet.equals(new Projet(0l,"Projet 1","description")));
    }
    
    
    
    
    
    
}
