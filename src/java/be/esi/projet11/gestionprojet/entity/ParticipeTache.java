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
 * @author g35364
 */
@Entity
@IdClass(ParticipeTachePK.class)
public class ParticipeTache implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@JoinColumn(name = "MEMBRE")
    @OneToOne(cascade = CascadeType.ALL)
    protected Membre membre;
    @Id
    @JoinColumn(name = "TACHE")
    private Tache tache;
    @Basic(optional = false)
    @Column(name = "ACCEPTE")
    protected char accepte;
    
    public ParticipeTache() {
        
    }
    
    public ParticipeTache(Tache tache, Membre membre) {
        this.tache = tache;
        this.membre = membre;
        setAccepte(false);
    }

    public ParticipeTachePK getId() {
        return new ParticipeTachePK(getTache(), membre);
    }

    public void setId(ParticipeTachePK pk) {
        this.tache = pk.getTache();
        this.membre = pk.getMembre();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.membre != null ? this.membre.hashCode() : 0);
        hash = 37 * hash + (this.getTache() != null ? this.getTache().hashCode() : 0);
        hash = 37 * hash + this.accepte;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParticipeTache other = (ParticipeTache) obj;
        if (this.membre != other.membre && (this.membre == null || !this.membre.equals(other.membre))) {
            return false;
        }
        if (this.getTache() != other.getTache() && (this.getTache() == null || !this.tache.equals(other.tache))) {
            return false;
        }
        if (this.accepte != other.accepte) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "ParticipeTache: Membre=" + membre + " - Tâche=" + getTache();
    }

    /**
     * @return the accepte
     */
    public char getAccepte() {
        return accepte;
    }
    
    public boolean isAccepte() {
        return (accepte == '1' ? true : false);
    }

    /**
     * @param accepte the accepte to set
     */
    public void setAccepte(char accepte) {
        this.accepte = accepte;
    }
    
    public void setAccepte(boolean accepte) {
        this.accepte = (accepte ? '1' : '0');
    }
    
    public Membre getMembre() {
        return membre;
    }

    /**
     * @return the tache
     */
    public Tache getTache() {
        return tache;
    }
    
}
