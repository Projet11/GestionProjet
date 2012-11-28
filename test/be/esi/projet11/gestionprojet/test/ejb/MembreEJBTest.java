/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.DBException;
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
public class MembreEJBTest {

    private static EJBContainer container;
    private static HashMap<Object, Object> properties;
    private static MembreEJB instance;

    @Test(expected = DBException.class)
    public void TestAjoutIdExistant() throws DBException {
        instance.addUser("CED", "CED", "abcdef2@gmail.com", "abc", "def");
    }

    @Test(expected = DBException.class)
    public void TestAjoutMailExistant() throws DBException {
        instance.addUser("CED", "CED2", "abcdef@gmail.com", "abc", "def");
    }

    @Test(expected = DBException.class)
    public void TestAjoutMailIncorrect() throws DBException {
        instance.addUser("a", "a", "abcde", "abc", "def");
    }

    @Test(expected = DBException.class)
    public void TestIdentificationMembreInexistant() throws DBException {
        Membre user = instance.getUserByAuthentification("ABCDE", "ABCDE");
        Assert.assertNull(user);
    }

    @Test
    public void TestIdentificationMembreExistant() throws DBException {
        Membre user = instance.getUserByAuthentification("CED", "CED");
        Assert.assertNotNull(user);
    }

    @Test
    public void TestUserExistant() {
        Assert.assertTrue(instance.userExists("CED"));
    }

    @Test
    public void TestUserInexistant() {
        Assert.assertFalse(instance.userExists("CED1"));
    }

    @Test
    public void TestMailExistant() {
        Assert.assertTrue(instance.mailExists("abcdef@gmail.com"));
    }

    @Test
    public void TestMailInexistant() {
        Assert.assertFalse(instance.mailExists("abcdefgh@gmail.com"));
    }

    @Test
    public void TestGetAllUsers() throws DBException {
        Assert.assertEquals(instance.getAllUsers().size(), 1);
    }
    
    @Test
    public void TestGetMembreByEmailAndGetMembreById() throws DBException {
        Membre mbr = instance.getAllUsers().iterator().next();
        Membre mbr1 = instance.getMembreByEmail(mbr.getMail());
        Membre mbr2 = instance.getMembreById(mbr.getId());
        Assert.assertEquals(mbr1, mbr2);
    }
    
    public MembreEJBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException, DBException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instance = (MembreEJB) container.getContext().lookup("java:global/GestionProjet/classes/MembreEJB");
        instance.addUser("CED", "CED", "abcdef@gmail.com", "abc", "def");
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
