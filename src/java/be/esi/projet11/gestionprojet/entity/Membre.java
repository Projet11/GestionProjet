/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author g35364
 */
@Entity
public class Membre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;

    public Membre() {
    }

    public Membre(Long id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Membre))
            return false;
        
        Membre other = (Membre) object;
        return id == other.id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
