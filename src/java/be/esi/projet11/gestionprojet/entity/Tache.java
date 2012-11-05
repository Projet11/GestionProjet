/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author g34840
 */
@Entity
@Table(name = "TACHE")
@NamedQueries({
    @NamedQuery(name = "Tache.findByNom", query = "SELECT t FROM Tache t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tache.findAll", query = "SELECT t FROM Tache t")})
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Tache")
    @TableGenerator(name = "Tache", allocationSize = 1)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nom;
    @Column(nullable = false)
    private String importance;

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
    private String description;

    public Tache() {
    }

    public Tache(String nom, String description) throws TacheException {
        if (nom == null || nom.equals("")) {
            throw new TacheException();
        }
        this.nom = nom;
        this.description = description;
        this.importance = "Normale";
    }

    public Tache(String nom, String description, String importance) throws TacheException {
        this(nom, description);
        if (!importance.equals(ImportanceEnum.IMPORTANT.getLibelle())
                && !importance.equals(ImportanceEnum.NORMALE.getLibelle())
                && !importance.equals(ImportanceEnum.TRESIMPORTANT.getLibelle())) {
            throw new TacheException();
        }
        this.importance = importance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tache)) {
            return false;
        }
        Tache other = (Tache) object;
        if (this.nom.equals(other.nom) || this.id == other.id) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tache n°" + id + " Nom : " + nom + " Importance : " + importance + "\n Description : " + this.description;
    }
}
