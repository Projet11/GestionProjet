/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;

/**
 *
 * @author g35364
 */

public class ParticipeTachePK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected long membre;
    protected long tache;

    public ParticipeTachePK() {
    }
    
    public ParticipeTachePK(Tache tache, Membre membre) {
//        this.membre = membre;
//        this.tache = tache;
    }

    public Membre getMembre() {
//        return membre;
        return null;
    }

    public void setMembre(Membre membre) {
//        this.membre = membre;
    }

    public Tache getTache() {
//        return tache;
        return null;
    }

    public void setTache(Tache tache) {
//        this.tache = tache;
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
