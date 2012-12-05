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
 * @author g34771
 */
    
@Embeddable
public class ParticipeProjetPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "MEMBRE")
    private long membre;
    @Basic(optional = false)
    @Column(name = "PROJET")
    private long projet;

    public ParticipeProjetPK() {
    }
    
    public ParticipeProjetPK(long membre, long projet) {
        this.membre = membre;
        this.projet = projet;
    }

    public long getMembre() {
        return membre;
    }

    public void setMembre(long membre) {
        this.membre = membre;
    }

    public long getProjet() {
        return projet;
    }

    public void setProjet(long projet) {
        this.projet = projet;
    }
    
}