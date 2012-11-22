package be.esi.projet11.gestionprojet.entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @Column(unique = true, nullable = false)
    private String nom;    
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ParticipeTache> listeMembres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    Collection<Tache> listeTaches;
    
    public Projet(){
        this(0l,"Projet sans nom","");
    }
    
    public Projet(Long id,String nom){
        this(id,nom,"");
    }
    
    public Projet(Long id,String nom, String description){
        if (nom == null || nom.isEmpty()){
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        this.id = id;
        this.nom = nom;
        this.description = description;
        listeTaches = new ArrayList<Tache>();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    

    public Collection<Tache> getListeTaches() {
        return listeTaches;
    }

    public void setListeTaches(Collection<Tache> listeTaches) {
        this.listeTaches = listeTaches;
    }

    public Collection<ParticipeTache> getListeMembres() {
        return listeMembres;
    }

    public void setListeMembres(Collection<ParticipeTache> membres) {
        this.listeMembres = membres;
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
