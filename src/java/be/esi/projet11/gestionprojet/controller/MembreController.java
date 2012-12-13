package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "membreCtrl")
@SessionScoped
public class MembreController {

    @EJB
    private MembreEJB membreEJB;
    private Membre membreCourant;
    private String inputNom;
    private String inputPrenom;
    private String inputLogin;
    private String inputPassword;
    private boolean identificationEchouee;
    private String inputMail;
    private boolean inscriptionEchouee;
    private String statusMessage;
    private Membre membreFromEmail;

    @PostConstruct
    public void init() {
        this.setIdentificationEchouee(false);
        this.setInscriptionEchouee(false);
        this.setStatusMessage(null);
    }

    public String getInputLogin() {
        return inputLogin;
    }

    public void setInputLogin(String inputLogin) {
        this.inputLogin = inputLogin;
    }

    public String getInputNom() {
        return this.inputNom;
    }

    public void setInputNom(String inputNom) {
        this.inputNom = inputNom;
    }

    public String getInputPrenom() {
        return this.inputPrenom;
    }

    public void setInputPrenom(String inputPrenom) {
        this.inputPrenom = inputPrenom;
    }

    public String getInputMail() {
        return inputMail;
    }

    public void setInputMail(String inputMail) {
        this.inputMail = inputMail;
    }

    public String getInputPassword() {
        return this.inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public boolean isIdentificationEchouee() {
        return identificationEchouee;
    }

    public void setIdentificationEchouee(boolean identificationEchouee) {
        this.identificationEchouee = identificationEchouee;
    }

    public boolean isInscriptionEchouee() {
        return inscriptionEchouee;
    }

    public void setInscriptionEchouee(boolean inscriptionEchouee) {
        this.inscriptionEchouee = inscriptionEchouee;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Membre getMembreCourant() {
        return membreCourant;
    }

    public void setMembreCourant(Membre membreCourant) {
        this.membreCourant = membreCourant;
    }

    public void setMembreFromEmail(Membre membreFromEmail) {
        this.membreFromEmail = membreFromEmail;
    }

    public boolean isAuthenticated() {
        return getMembreCourant() != null;
    }

    public String identifier() {
        final String NAV_CASE_SUCCESS = "success";
        final String NAV_CASE_FAILURE = "failure";
        if (!this.isAuthenticated()) {
            try {
                this.membreCourant = this.authenticateUser(this.inputLogin, this.inputPassword);
                this.setIdentificationEchouee(!this.isAuthenticated());
            } catch (BusinessException ex) {
                this.setIdentificationEchouee(true);
            }
        }

        return this.isAuthenticated() ? NAV_CASE_SUCCESS : NAV_CASE_FAILURE;
    }

    public String inscrire() {
        try {
            Membre mbr = null;
            if (membreFromEmail != null) {
                mbr = membreEJB.getMembreByEmail(membreFromEmail.getMail());
            }
            if (mbr == null) {
                this.membreCourant = membreEJB.addUser(inputLogin, inputPassword, inputMail, inputNom, inputPrenom);
            } else {
                this.membreCourant = membreEJB.updateUser(membreFromEmail.getId(), inputLogin, inputPassword, inputMail, inputNom, inputPrenom);
                membreFromEmail = null;
            }
            if (this.membreCourant == null) {
                throw new IllegalStateException("L'inscription a échoué");
            }
            this.setInscriptionEchouee(false);
            this.setStatusMessage("Merci. Votre compte a été créé.");
        } catch (Exception e) {
            this.setInscriptionEchouee(true);
            this.setStatusMessage(e.getMessage());
        }
        return null;
    }

    public String deconnexion() {
        this.membreCourant = null;
        return null;
    }

    private Membre authenticateUser(String login, String password) throws BusinessException {
        try {
            setMembreCourant(membreEJB.getUserByAuthentification(login, password));
            return getMembreCourant();
        } catch (DBException e) {
            setMembreCourant(null);
            throw new BusinessException(e.getMessage());
        }
    }

    public void navigationIsAuthenticated() throws BusinessException {
        if (!isAuthenticated()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/GestionProjet/pages/connexion.xhtml");
            } catch (IOException ex) {
                throw new BusinessException("Erreur: la redirection automatique a échouée");
            }
        }
    }
}
