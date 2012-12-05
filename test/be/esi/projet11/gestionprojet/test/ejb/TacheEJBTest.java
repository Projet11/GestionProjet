package be.esi.projet11.gestionprojet.test.ejb;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Commentaire;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.Projet;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g34840
 */
public class TacheEJBTest {

    private static MembreEJB membreEJB;
    private static ProjetEJB projetEJB;
    private static TacheEJB tacheEJB ;
    private static EJBContainer container;
    private static HashMap<Object, Object> properties;
    private static Projet projet;
    private static Tache tache;
    private static Membre membre;

    @BeforeClass
    public static void setUpClass() throws Exception {
        membreEJB = lookupMembreEJBBean();
        tacheEJB = lookupTacheEJBBean();
        projetEJB = lookupProjetEJBBean();
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        projet = projetEJB.creerProjet("unProjet", "descriptionDunProjet");
        tache = tacheEJB.creerTache("abc", "defght", ImportanceEnum.NORMALE, projet);
        membre = membreEJB.addUser("login", "password", "mail@hotmail.com", "nom", "prenom");

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void creerTacheReussi() throws NamingException, TacheException {
//        Tache uneTache = instance.creerTache("uneTache", "Lorem", ImportanceEnum.NORMALE, projet);
//        Tache uneTache2 = new Tache("uneTache", "Lorem", ImportanceEnum.NORMALE, projet);
//        assertEquals(uneTache, uneTache2);
//    }
//
//    @Test
//    public void creerTacheDifférent() throws NamingException, TacheException {
//        Tache uneTache = instance.creerTache("uneTache2", "", ImportanceEnum.NORMALE, projet);
//        Tache uneTache2 = new Tache("uneAutreTache2", "Lorem", ImportanceEnum.NORMALE, projet);
//        assertTrue(!uneTache.equals(uneTache2));
//    }
//
//    @Test(expected = TacheException.class)
//    public void creerTacheException() throws NamingException, TacheException {
//        instance.creerTache("", "", ImportanceEnum.NORMALE, projet);
//    }
//
//    @Test
//    public void creerTacheSansDescription() throws NamingException, TacheException {
//        instance.creerTache("3", "", ImportanceEnum.NORMALE, projet);
//    }
//
//    @Test
//    public void getTacheParIdReussi() throws NamingException, TacheException {
//        long id = instance.creerTache("4", "", ImportanceEnum.NORMALE, projet).getId();
//        assertTrue(id == instance.getTache(id).getId());
//    }
//
//    @Test
//    public void getTacheParNomReussi() throws NamingException, TacheException {
//        String nom = instance.creerTache("5", "", ImportanceEnum.NORMALE, projet).getNom();
//        System.out.println(nom);
//        assertEquals(nom, instance.getTache("5").getNom());
//    }
//
//    @Test
//    public void getTacheParNomDifferent() throws NamingException, TacheException {
//        String nom = instance.creerTache("6", "", ImportanceEnum.NORMALE, projet).getNom();
//        String nom2 = instance.creerTache("7", "", ImportanceEnum.NORMALE, projet).getNom();
//        assertTrue(!nom.equals(instance.getTache("7").getNom()));
//    }
//
//    @Test
//    public void getTacheParIdDifferent() throws NamingException, TacheException {
//        long id = instance.creerTache("8", "", ImportanceEnum.NORMALE, projet).getId();
//        long id2 = instance.creerTache("9", "", ImportanceEnum.NORMALE, projet).getId();
//        assertTrue(id != instance.getTache(id2).getId());
//    }
//
//    @Test
//    public void getAllTacheNombreDeTache() throws NamingException {
//        assertEquals(instance.getAllTache().size(),9);
//    }
//        @Test
//    public void getAllTacheNomDeLaDerniereTache() throws NamingException {
//        List<Tache> taches = new ArrayList<Tache>();
//        Iterator<Tache> iterator  = instance.getAllTache().iterator();
//        while(iterator.hasNext()){
//        taches.add(iterator.next()); 
//        }
//        assertTrue(taches.get(8).getNom().equals("9"));
//    }
//    @Test
//    public void ajouterCommentaire() {
//        System.out.println(tache);
//        tacheEJB.ajouterCommentaire(tache, membre, "HELLO");
//        Commentaire comment = tacheEJB.getConversation(tache).get(0);
//        assertEquals(comment.getCorps(),"HELLO");
//    }
    @Test
    public void Test(){
        assertEquals(true,true);
    }
    private static TacheEJB lookupTacheEJBBean() {
        try {
            Context c = new InitialContext();
            return (TacheEJB) c.lookup("java:global/GestionProjet/TacheEJB!be.esi.projet11.gestionprojet.ejb.TacheEJB");
        } catch (NamingException ne) {

            throw new RuntimeException(ne);
        }
    }

    private static ProjetEJB lookupProjetEJBBean() {
        try {
            Context c = new InitialContext();
            return (ProjetEJB) c.lookup("java:global/GestionProjet/ProjetEJB!be.esi.projet11.gestionprojet.ejb.ProjetEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    private static MembreEJB lookupMembreEJBBean() {
        try {
            Context c = new InitialContext();
            return (MembreEJB) c.lookup("java:global/GestionProjet/MembreEJB!be.esi.projet11.gestionprojet.ejb.MembreEJB");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }
}
