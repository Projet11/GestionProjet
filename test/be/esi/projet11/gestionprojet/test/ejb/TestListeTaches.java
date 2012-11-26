/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
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
        p = instanceProjetEJB.creerProjet("Projet 1","description");
        collection.add(instanceTacheEJB.creerTache("4", "",p));
        collection.add(instanceTacheEJB.creerTache("5", "",p));
        collection.add(instanceTacheEJB.creerTache("6", "",p));
        collection.add(instanceTacheEJB.creerTache("7", "",p));
        collection.add(instanceTacheEJB.creerTache("8", "",p));
        id = instanceTacheEJB.getTache("4").getId();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
    }  
    
    @Test
    public void testGetListToutesLesTaches() throws TacheException {
        assertArrayEquals(instanceTacheEJB.getTaches(null,p).toArray(), collection.toArray());
    }
    @Test
    public void testGetTachesArchivees(){
        instanceTacheEJB.archiverTache(id);
        assertEquals(instanceTacheEJB.getTaches(true,p).size(), 1);
    }
    
    @Test
    public void testGetTachesNonArchivees(){
        assertEquals(instanceTacheEJB.getTaches(false,p).size(), 4);
    }
    
    @Test
    public void testGetTachesNonArchiveesListeVide(){
        id = instanceTacheEJB.getTache("5").getId();
        instanceTacheEJB.archiverTache(id);
        id = instanceTacheEJB.getTache("6").getId();
        instanceTacheEJB.archiverTache(id);
        id = instanceTacheEJB.getTache("7").getId();
        instanceTacheEJB.archiverTache(id);
        id = instanceTacheEJB.getTache("8").getId();
        instanceTacheEJB.archiverTache(id);
        assertEquals(instanceTacheEJB.getTaches(false,p).size(), 0);
    }
}
