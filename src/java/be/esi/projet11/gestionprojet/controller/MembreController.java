/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author g34754
 */
@ManagedBean(name = "MembreCtrl")
@SessionScoped
public class MembreController {

    @EJB
    private MembreEJB membreEJB;
    private Membre authenticatedUser;

    /*
     * Creates a new instance of MembreManage
     */
    public MembreController() {
    }
    
    public Membre createUser(String login, String password, String mail,
            String nom, String prenom) throws BusinessException {
        try {
            return membreEJB.addUser(login, password, mail, nom, prenom);
        } catch (Exception e) {
            System.out.println("FacadeException : " + e);
            throw new BusinessException(e.getMessage());
        }
    }

    public Membre authenticateUser(String login, String password) throws BusinessException {
        try {
            authenticatedUser = membreEJB.getUserByAuthentification(login, password);
            return authenticatedUser;
        } catch (DBException e) {
            authenticatedUser = null;
            throw new BusinessException(e.getMessage());
        }
    }
    
    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }

//    public Collection<Membre> getAllMembres() {
//    }
}
