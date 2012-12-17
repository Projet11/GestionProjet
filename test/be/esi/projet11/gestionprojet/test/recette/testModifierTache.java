/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import be.esi.projet11.gestionprojet.entity.Membre;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g33252
 */
public class testModifierTache extends TestDeBase {

    private String bouton_modifier_tache = "id=formAfficherTaches:resultat:0:btnTacheMod";
    private String lienProjet1 = "link=Projet 1";
    private String champ_pourcentage = "id=formModTache:pourcentage";
    private String champ_revision = "id=formModTache:revision";
    private String bouton_valider_modif = "id=formModTache:btnValider";
    private String bouton_retour = "id=btnAnnuler";
    private String bouton_archiver_modif = "id=formModTache:btnArchiver";
    private String bouton_ajout_membre = "id=butAjoutMembre";
    private String liste_membres = "id=listeMembre";
    private String combo_priorite = "id=formModTache:priorite";
    private String prioTresHaute = "Très important";
    private String prioNormale = "Normale";
    private String prioHaute = "Important";
    private Membre utilisateur1 = new Membre(0l, "Pluquet2", "Pluquet2", "pluquet2@gmail.com", "Pluquet2", "Frederic2");
    private Membre utilisateur2 = new Membre(0l, "Pluquet3", "Pluquet3", "pluquet3@gmail.com", "Pluquet3", "Frederic3");

    public testModifierTache() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        //creerUser();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //supprimerMembre();
    }

    @Test
    public void modifierEtAnnulerTache() {
        seConnecter();
        selenium.click(lienProjet1);
        attendre();
        selenium.click(bouton_modifier_tache);
        attendre();
        selenium.type(champ_pourcentage, "80");
        selenium.type(champ_revision, "");
        /*ajouterMembres(2);
        verifierMembre(2);*/
        selenium.click(bouton_retour);
        attendre();
        Assert.assertFalse(selenium.isTextPresent("80 %"));
    }

    @Test
    public void modifierTache() {
        seConnecter();
        modifierTacheEtValider(0, prioHaute, 80, 159);
        modifierTacheEtValider(1, prioNormale, 78, 160);
    }

    public void modifierTacheEtValider(int tacheId, String priorite, int pourcentage, int numSvn) {
        seConnecter();
        selenium.click(lienProjet1);
        attendre();
        selenium.click("id=formAfficherTaches:resultat:" + tacheId + ":btnTacheMod");
        attendre();
        selenium.select(combo_priorite, "label=" + priorite);
        selenium.type(champ_pourcentage, String.valueOf(pourcentage));
        selenium.type(champ_revision, "");
        /*ajouterMembres(nbMembres);
        verifierMembre(nbMembres);*/
        selenium.click(bouton_valider_modif);
        attendre();
        selenium.click(bouton_retour);
        attendre();
        Assert.assertTrue(selenium.isTextPresent(pourcentage + " %"));
    }

    /*private void ajouterMembres(int nbMembres) {
        selenium.click(bouton_ajout_membre);
        attendre();
        if (nbMembres > 0) {
            selenium.addSelection(liste_membres, "label=" + utilisateur1.getNom());
        }
        if (nbMembres > 1) {
            selenium.addSelection(liste_membres, "label=" + utilisateur2.getNom());
        }
    }

    private void verifierMembre(int nbMembres) {
        if (nbMembres > 0) {
            Assert.assertTrue(selenium.isTextPresent(utilisateur1.getNom()));
        }
        if (nbMembres > 1) {
            Assert.assertTrue(selenium.isTextPresent(utilisateur2.getNom()));
        }
    }*/
}
