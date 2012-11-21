package be.esi.projet11.gestionprojet.entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 *
 * @author g32460
 */
@Entity
public class Projet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Projet")
    @TableGenerator(name = "Projet", allocationSize = 1)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    Collection<Tache> listeTaches;

    public Long getId() {
        return id;
    }

    public Collection<Tache> getListeTaches() {
        return listeTaches;
    }

    public void setListeTaches(Collection<Tache> listeTaches) {
        this.listeTaches = listeTaches;
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
        if (!(object instanceof Projet)) {
            return false;
        }
        Projet other = (Projet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tache.Projet[ id=" + id + " ]";
    }
    
}
