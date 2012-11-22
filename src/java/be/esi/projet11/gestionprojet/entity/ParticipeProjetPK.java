/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;

/**
 *
 * @author g32460
 */
public class ParticipeProjetPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected long membre;
    protected long projet;

    public ParticipeProjetPK() {
    }

    ParticipeProjetPK(Projet projet, Membre membre) {
        
    }

   
    @Override
    public int hashCode() {
         int hash = 3;
        hash = 31 * hash + (int) (this.membre ^ (this.membre >>> 32));
        hash = 31 * hash + (int) (this.projet ^ (this.projet >>> 32));
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
        final ParticipeProjetPK other = (ParticipeProjetPK) object;
        if (this.membre != other.membre) {
            return false;
        }
        if (this.projet != other.projet) {
            return false;
        }
        return true;
    }

    Projet getProjet() {
        return null;
    }

    Membre getMembre() {
        return null;
    }

   
    
}
