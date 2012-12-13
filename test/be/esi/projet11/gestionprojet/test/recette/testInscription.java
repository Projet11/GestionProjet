package be.esi.projet11.gestionprojet.test.recette;

import junit.framework.Assert;
import org.junit.Test;

public class testInscription extends TestDeBase {

    String nom = "test", prenom = "testprenom", login = "test", email = "test@gamil.be", mdp = "test";
    String messageInscriptionReussie = "Merci. Votre compte a été créé.";
    String messageChampVide = "Un champ a été laissé vide";
    String messageMotDePasseInvalide = "Les deux mots de passe ne correspondent pas";
    String messageEmailInvalide = "L'email n'est pas valide";

    @Test
    public void testInscriptionReussie() {
        remplirEtTesterFormulaire("plu", "plu", "plu", "plu@gamil.com", "plu", "plu", "testInsciption reussi", messageInscriptionReussie);
    }
    
    @Test
    public void testInscriptionAvecChampVide() {
        String messageTest = "L'inscription n'a pas eu lieu";
        remplirEtTesterFormulaire(nom, prenom, "", email, mdp, mdp, messageTest, messageChampVide);
        remplirEtTesterFormulaire(nom, prenom, login, "", mdp, mdp, messageTest, messageChampVide);
        remplirEtTesterFormulaire(nom, prenom, login, email, "", mdp, messageTest, messageChampVide);
        remplirEtTesterFormulaire(nom, prenom, login, email, mdp, "", messageTest, messageChampVide);
    }

    @Test
    public void testInscriptionMotDePasseDifferent() {
        remplirEtTesterFormulaire(nom, prenom, login, email, mdp, mdp + "aaa", "L'inscription n'a pas eu lieu", messageMotDePasseInvalide);
    }

    @Test
    public void testInscriptionAvecChampVideEtMotDePasseDifferent() {
        remplirEtTesterFormulaire(nom, prenom, "", email, mdp, mdp + "aaa", "L'inscription n'a pas eu lieu", messageChampVide);
    }

    @Test
    public void testInscriptionEmailInvalide() {
        remplirEtTesterFormulaire(nom, prenom, login, "aaaa", mdp, mdp, "L'inscription n'a pas eu lieu", messageEmailInvalide);
        remplirEtTesterFormulaire(nom, prenom, login, "aaaa@aa", mdp, mdp, "L'inscription n'a pas eu lieu", messageEmailInvalide);
    }

    @Test
    public void testInscriptionEmailValide() {
        remplirEtTesterFormulaire(nom, prenom, login, "aa.aa@aa.com", mdp, mdp, "L'inscription a réussi", messageInscriptionReussie);

    }

    private void remplirEtTesterFormulaire(String nom, String prenom, String login, String email, String mdp, String mdpconfirm, String messageTest, String messageAttendu) {
        remplirFormulaire(nom, prenom, login, email, mdp, mdpconfirm);
        boolean ok = cliquerEtTesterMessage(messageAttendu);
        Assert.assertTrue(messageTest, ok);
    }

    private boolean cliquerEtTesterMessage(String messageAttendu) {
        selenium.click("id=formInscription:inscription");
        attendre();
        boolean ok = selenium.isTextPresent(messageAttendu);
        return ok;
    }

    private void remplirFormulaire(String nom, String prenom, String login, String email, String mdp, String mdpconfirm) {
        selenium.open("/GestionProjet/pages/inscription.xhtml");
        selenium.type("id=formInscription:nom", nom);
        selenium.type("id=formInscription:prenom", prenom);
        selenium.type("id=formInscription:email", email);
        selenium.type("id=formInscription:login", login);
        selenium.type("id=formInscription:mdp", mdp);
        selenium.type("id=formInscription:mdpconfirm", mdpconfirm);
    }
}
