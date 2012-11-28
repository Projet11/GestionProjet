/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author g34771
 */
@Entity
public class ParticipeProjet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ParticipeProjetPK pk;
    private char accepter;
    @JoinColumn(name="PROJET", referencedColumnName="ID", insertable=false, updatable=false)
    @ManyToOne(optional=false)
    private Projet projet1;
    @JoinColumn(name="MEMBRE", referencedColumnName="ID", insertable=false, updatable=false)
    @ManyToOne(optional=false)
    private Membre membre1;

    public ParticipeProjet() {
    }

    
    public ParticipeProjet(Membre mbr, Projet prj,boolean accepter) {
        pk= new ParticipeProjetPK(mbr.getId(), prj.getId());
        membre1= mbr;
        projet1= prj;
        setAccepter(accepter);
    }
    
    public ParticipeProjetPK getId() {
        return pk;
    }
    
    public Membre getMembre(){
        return membre1;
    }

    public void setId(ParticipeProjetPK id) {
        this.pk = id;
    }

    public boolean isAccepter() {
        return (accepter=='1');
    }

    public void setAccepter(boolean accepter) {
        this.accepter = (accepter? '1':'0');
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipeProjet)) {
            return false;
        }
        ParticipeProjet other = (ParticipeProjet) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.esi.projet11.gestionprojet.entity.ParticipeProjetEjb[ id=" + pk + " ]";
    }
    
}
