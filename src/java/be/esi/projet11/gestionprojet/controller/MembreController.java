package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "membreCtrl")
@SessionScoped
public class MembreController {

    @EJB
    private MembreEJB membreEJB;
    private Membre membreCourant;
    private String inputLogin;
    private String inputPassword;
    private String inputNom;
    private String inputPrenom;
    private String inputMail;
    private boolean identificationEchouee;
    private boolean inscriptionEchouee;
    private String statusMessage;

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

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public String getInputNom() {
        return inputNom;
    }

    public void setInputNom(String inputNom) {
        this.inputNom = inputNom;
    }

    public String getInputPrenom() {
        return inputPrenom;
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

    public boolean isAuthenticated() {
        return membreCourant != null;
    }

    public String identifier() {
        if (!this.isAuthenticated()) {
            try {
                this.membreCourant = this.authenticateUser(this.inputLogin, this.inputPassword);
                this.setIdentificationEchouee(!this.isAuthenticated());
            } catch (BusinessException ex) {
                this.setIdentificationEchouee(true);
            }
        }

        return this.isAuthenticated() ? "success" : "failure";
    }

    public String inscrire() {
        try {
            this.membreCourant = this.membreEJB.addUser(inputLogin, inputPassword, inputMail, inputNom, inputPrenom);
            this.setInscriptionEchouee(false);
            this.setStatusMessage("Merci. Votre compte a été créé.");
        } catch (Exception e) {
            this.setInscriptionEchouee(true);
            this.setStatusMessage(e.getMessage());
        }

        return null;
    }

    private Membre authenticateUser(String login, String password) throws BusinessException {
        try {
            membreCourant = membreEJB.getUserByAuthentification(login, password);
            return membreCourant;
        } catch (DBException e) {
            membreCourant = null;
            throw new BusinessException(e.getMessage());
        }
    }
}
