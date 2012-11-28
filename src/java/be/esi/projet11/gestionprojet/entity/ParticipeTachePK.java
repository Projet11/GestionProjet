/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author g35364
 */
@Embeddable
public class ParticipeTachePK implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "MEMBRE")
    protected long membre;
    @Basic(optional = false)
    @Column(name = "TACHE")
    protected long tache;

    public ParticipeTachePK() {
    }
    
    public ParticipeTachePK(long tache, long membre) {
        this.membre = membre;
        this.tache = tache;
    }

    public long getMembre() {
        return membre;
    }

    public void setMembre(long membre) {
        this.membre = membre;
    }

    public long getTache() {
        return tache;
    }

    public void setTache(long tache) {
        this.tache = tache;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (int) (this.membre ^ (this.membre >>> 32));
        hash = 31 * hash + (int) (this.tache ^ (this.tache >>> 32));
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
        final ParticipeTachePK other = (ParticipeTachePK) obj;
        if (this.membre != other.membre) {
            return false;
        }
        if (this.tache != other.tache) {
            return false;
        }
        return true;
    }
    
}
