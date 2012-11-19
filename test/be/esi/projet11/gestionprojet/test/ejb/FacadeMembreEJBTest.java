/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.FacadeMembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ced
 */
public class FacadeMembreEJBTest {

    private static EJBContainer container;
    private static HashMap<Object, Object> properties;
    private static FacadeMembreEJB instance;

    @Test(expected = BusinessException.class)
    public void TestAjoutIdExistant() throws BusinessException {
        instance.createUser("CED", "CED", "abcdef2@gmail.com", "abc", "def");
    }

    @Test(expected = BusinessException.class)
    public void TestAjoutMailExistant() throws BusinessException {
        instance.createUser("CED", "CED2", "abcdef@gmail.com", "abc", "def");
    }

    @Test(expected = BusinessException.class)
    public void TestAjoutMailIncorrect() throws BusinessException {
        instance.createUser("a", "a", "abcde", "abc", "def");
    }

    @Test(expected = BusinessException.class)
    public void TestIdentificationMembreInexistant() throws BusinessException {
        Membre user = instance.authenticateUser("ABCDE", "ABCDE");
        Assert.assertNull(user);
    }

    @Test
    public void TestIdentificationMembreExistant() throws BusinessException {
        Membre user = instance.authenticateUser("CED", "CED");
        Assert.assertNotNull(user);
    }

    public FacadeMembreEJBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException, BusinessException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (FacadeMembreEJB) container.getContext().lookup("java:global/GestionProjet/classes/FacadeMembreEJB");
        instance.createUser("CED", "CED", "abcdef@gmail.com", "abc", "def");
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
}
