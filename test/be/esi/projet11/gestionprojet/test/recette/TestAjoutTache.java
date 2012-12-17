/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g33252
 */
public class TestAjoutTache extends TestDeBase {

    private String bouton_ajout_tache = "id=formAfficherTaches:creerTacheBtn";
    private String lienProjet1 = "link=Projet 1";
    private String bouton_cree_tache = "id=formCreerTache:btnCreer";
    private String bouton_annuler_tache = "id=formCreerTache:btnAnnuler";
    private String champ_tache_nom = "id=formCreerTache:Nom";
    private String combo_priorite = "id=formCreerTache:priorite";
    private String champ_description = "id=formCreerTache:description";
    private String prioTresHaute = "Très important";
    private String prioNormale = "Normale";
    private String prioImportante = "Important";

    public TestAjoutTache() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void ajoutTachePrioEtNomDifferents() {
        seConnecter();
        ajouterUneTacheEtLaRetrouver("tache21", prioNormale);
        ajouterUneTacheEtLaRetrouver("tache22", prioImportante);
        ajouterUneTacheEtLaRetrouver("tache23", prioTresHaute);
    }

    @Test
    public void ajoutDeuxTachesMemeNoms() {
        seConnecter();
        ajouterUneTacheEtLaRetrouver("tache15", prioNormale);
        ajouterUneTacheEtLaRetrouver("tache15", prioImportante);
    }

    @Test
    public void ajoutTachePuisAnnuler() {
        seConnecter();
        selenium.click(lienProjet1);
        attendre();
        selenium.click(bouton_ajout_tache);
        attendre();
        selenium.type(champ_tache_nom, "tache annulee");
        selenium.select(combo_priorite, "label=" + prioNormale);
        selenium.click(bouton_annuler_tache);
        attendre();
        Assert.assertFalse(selenium.isTextPresent("tache annulee"));
    }

    private void ajouterUneTacheEtLaRetrouver(String nomTache, String priorite) {
        selenium.open("/GestionProjet/pages/accueil.xhtml");
        selenium.click(lienProjet1);
        attendre();
        selenium.click(bouton_ajout_tache);
        attendre();
        selenium.type(champ_tache_nom, nomTache);
        selenium.select(combo_priorite, "label=" + priorite);
        selenium.type(champ_description, "Commentaire");
        selenium.click(bouton_cree_tache);
        attendre();
        selenium.click(lienProjet1);
        attendre();
        Assert.assertTrue(selenium.isTextPresent(nomTache));
    }
}
