package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String inputPassword;
    private boolean identificationEchouee;

    @PostConstruct
    public void init() {
        this.setIdentificationEchouee(false);
    }

    public String getInputNom() {
        return this.inputNom;
    }

    public void setInputNom(String inputNom) {
        this.inputNom = inputNom;
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

    public Membre getMembreCourant() {
        return membreCourant;
    }

    public void setMembreCourant(Membre membreCourant) {
        this.membreCourant = membreCourant;
    }

    public boolean isAuthenticated() {
        return membreCourant != null;
    }
    
    public void navigationIsAuthenticated(){
        if(!isAuthenticated()){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/connexion.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String identifier() {
        final String NAV_CASE_SUCCESS = "success";
        final String NAV_CASE_FAILURE = "failure";

        if (!this.isAuthenticated()) {
            try {
                this.membreCourant = this.authenticateUser(this.inputNom, this.inputPassword);
                this.setIdentificationEchouee(!this.isAuthenticated());
            } catch (BusinessException ex) {
                this.setIdentificationEchouee(true);
            }
        }

        return this.isAuthenticated() ? NAV_CASE_SUCCESS : NAV_CASE_FAILURE;
    }

    private Membre createUser(String login, String password, String mail,
            String nom, String prenom) throws BusinessException {
        try {
            return membreEJB.addUser(login, password, mail, nom, prenom);
        } catch (Exception e) {
            System.out.println("FacadeException : " + e);
            throw new BusinessException(e.getMessage());
        }
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
