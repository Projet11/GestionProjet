/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author g32460
 */
@Entity
@IdClass(ParticipeTachePK.class)
public class ParticipeProjet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@JoinColumn(name = "MEMBRE")
    @OneToOne(cascade = CascadeType.ALL)
    protected Membre membre;
    @Id
    @JoinColumn(name = "TACHE")
    @OneToOne // A vérifier
    private Projet projet;
    @Basic(optional = false)
    @Column(name = "ACCEPTE")
    protected char accepte;

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public boolean isAccepte() {
        return (accepte == '1' ? false : true);
    }

    public void setAccepte(boolean accepte) {
        this.accepte = (accepte ? '0' : '1');
        
    }

    public ParticipeProjetPK getId() {
        return new ParticipeProjetPK(getProjet(), membre);
    }

    public void setId(ParticipeProjetPK pk) {
        this.projet = pk.getProjet();
        this.membre = pk.getMembre();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.membre != null ? this.membre.hashCode() : 0);
        hash = 37 * hash + (this.getProjet() != null ? this.getProjet().hashCode() : 0);
        hash = 37 * hash + this.accepte;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final ParticipeProjet other = (ParticipeProjet) object;
        if (this.membre != other.membre && (this.membre == null || !this.membre.equals(other.membre))) {
            return false;
        }
        if (this.getProjet() != other.getProjet() && (this.getProjet() == null || !this.projet.equals(other.projet))) {
            return false;
        }
        if (this.accepte != other.accepte) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParticipeProjet: Membre=" + membre + " - Projet=" + getProjet();
    }
    
}
