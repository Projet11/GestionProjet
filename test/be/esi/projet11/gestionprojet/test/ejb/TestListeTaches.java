/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.DBException;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g32460
 */
public class TestListeTaches {

    static EJBContainer container;
    static TacheEJB instanceTacheEJB;
    static Long id;
    private static HashMap<Object, Object> properties;
    static Collection<Tache> collection;
    private static ProjetEJB instanceProjetEJB;
    private static Projet p;

    public TestListeTaches() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instanceTacheEJB = (TacheEJB) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
        instanceProjetEJB = (ProjetEJB) container.getContext().lookup("java:global/GestionProjet/classes/ProjetEJB");
        collection = new ArrayList<Tache>();
        p = instanceProjetEJB.creerProjet("Projet 21", "description");
        collection.add(instanceTacheEJB.creerTache("4", "", ImportanceEnum.IMPORTANT, p));
        collection.add(instanceTacheEJB.creerTache("5", "", ImportanceEnum.IMPORTANT, p));
        collection.add(instanceTacheEJB.creerTache("6", "", ImportanceEnum.IMPORTANT, p));
        collection.add(instanceTacheEJB.creerTache("7", "", ImportanceEnum.IMPORTANT, p));
        collection.add(instanceTacheEJB.creerTache("8", "", ImportanceEnum.IMPORTANT, p));
        id = instanceTacheEJB.getTache("4").getId();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
    }

    @Test
    public void testGetListToutesLesTaches() throws TacheException {
        assertArrayEquals(instanceTacheEJB.getTaches(null, p).toArray(), collection.toArray());
    }

    @Test
    public void testGetTachesArchivees() throws DBException {
        instanceTacheEJB.archiverTache(instanceTacheEJB.getTache("4"));
        assertEquals(instanceTacheEJB.getTaches(true, p).size(), 1);
    }

    @Test
    public void testGetTachesNonArchivees() throws DBException {

        instanceTacheEJB.desarchiverTache(instanceTacheEJB.getTache("5"));
        instanceTacheEJB.desarchiverTache(instanceTacheEJB.getTache("6"));
        instanceTacheEJB.desarchiverTache(instanceTacheEJB.getTache("7"));
        instanceTacheEJB.desarchiverTache(instanceTacheEJB.getTache("8"));
        assertEquals(instanceTacheEJB.getTaches(false, p).size(), 4);
    }

    @Test
    public void testGetTachesNonArchiveesListeVide() throws DBException {

        instanceTacheEJB.archiverTache(instanceTacheEJB.getTache("5"));
        instanceTacheEJB.archiverTache(instanceTacheEJB.getTache("6"));
        instanceTacheEJB.archiverTache(instanceTacheEJB.getTache("7"));
        instanceTacheEJB.archiverTache(instanceTacheEJB.getTache("8"));
        assertEquals(instanceTacheEJB.getTaches(false, p).size(), 0);
    }
}
