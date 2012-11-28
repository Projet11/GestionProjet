/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author g35364
 */
@Entity
@Table(name = "PARTICIPETACHE")
@XmlRootElement
public class ParticipeTache implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipeTachePK pk;
    @JoinColumn(name = "MEMBRE", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Membre membre;
    @JoinColumn(name = "TACHE", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tache tache;
    @Basic(optional = false)
    @Column(name = "ACCEPTE")
    private char accepte;
    
    public ParticipeTache() {
        
    }
    
    public ParticipeTache(Tache tache, Membre membre) {
        this.tache = tache;
        this.membre = membre;
        this.pk = new ParticipeTachePK(tache.getId(), membre.getId());
        setAccepte(false);
    }

    public ParticipeTachePK getId() {
        return pk;
    }

    public void setId(ParticipeTachePK pk) {
        this.pk = pk;
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
        return "ParticipeTache - Tache: " + tache + " - Membre: " + membre;
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
    
    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    /**
     * @return the tache
     */
    public Tache getTache() {
        return tache;
    }
    
    public void setTache(Tache tache) {
        this.tache = tache;
    }
    
}
