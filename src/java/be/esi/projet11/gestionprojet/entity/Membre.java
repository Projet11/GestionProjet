/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author g35001
 */
@Entity
@Table(name = "Membre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT g FROM Membre g"),
    @NamedQuery(name = "Membre.findById", query = "SELECT g FROM Membre g WHERE g.id = :id"),
    @NamedQuery(name = "Membre.findByLogin", query = "SELECT g FROM Membre g WHERE g.login = :login"),
    @NamedQuery(name = "Membre.findByAuthentification", query = "SELECT g FROM Membre g WHERE g.login = :login AND g.password = :password"),
    @NamedQuery(name = "Membre.findByMail", query = "SELECT g FROM Membre g WHERE g.mail = :mail"),
    @NamedQuery(name = "Membre.findByNom", query = "SELECT g FROM Membre g WHERE g.nom = :nom"),
    @NamedQuery(name = "Membre.findByPrenom", query = "SELECT g FROM Membre g WHERE g.prenom = :prenom")})
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Membre")
    @TableGenerator(name = "Membre", allocationSize = 1)
    private Long id;
    @Basic(optional = true)
    @Size(min = 1, max = 40)
    @Column(name = "LOGIN", unique = true)
    private String login;
    @Basic(optional = true)
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "MAIL", unique = true)
    @Size(min = 1, max = 255)
    private String mail;
    @Basic(optional = true)
    @Column(name = "NOM")
    @Size(min = 1, max = 100)
    private String nom;
    @Basic(optional = true)
    @Column(name = "PRENOM")
    @Size(min = 1, max = 100)
    private String prenom;
    @OneToMany(cascade= CascadeType.ALL, mappedBy="membre1")
    private Collection<ParticipeProjet> projets;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<ParticipeTache> taches;

    public Membre() {
    }

    public Membre(Long id) {
        this.id = id;
    }

    public Membre(Long id, String login, String password, String mail, String nom, String prenom) {
        this.id = id;
        setLogin(login);
        setPassword(password);
        setMail(mail);
        setNom(nom);
        setPrenom(prenom);
    }

    public Membre(String mail) {
        this.mail = mail;
    }
    
    // Ne sert que pour les tests
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login manquant !\n");
        } else {
            this.login = login;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Mot de passe manquant !\n");
        } else {
            this.password = password;
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) throws IllegalArgumentException {
        if (mail == null || mail.isEmpty()) {
            throw new IllegalArgumentException("E-Mail manquant !");
        } else {
            String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]*";
            if (mail.matches(regex)) {
                this.mail = mail;
            } else {
                throw new IllegalArgumentException("L'e-mail n'est pas une adresse mail valide !\n");
            }
        }

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Nom manquant !\n");
        } else {
            this.nom = nom;
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Prénom manquant !\n");
        } else {
            this.prenom = prenom;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return login;
    }
}
