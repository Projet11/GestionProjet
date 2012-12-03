/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author g34840
 */
@Entity
@Table(name = "COMMENTAIRE")

@NamedQueries({
    @NamedQuery(name = "Commentaire.findByTache", query = "SELECT c FROM Commentaire c WHERE c.tache = :tache ORDER BY c.datePosted")})
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn(name = "TACHE", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
    @ManyToOne(optional = false)
    private Tache tache;
    @JoinColumn(name = "MEMBRE", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
    @OneToOne
    private Membre membre;
    @Column(nullable = false)
    private String corps;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePosted;

    public Commentaire() {
    }

    public Commentaire(Tache tache, Membre membre, String corps,Date datePosted) {
        this.setTache(tache);
        this.setCommentaire(corps);
        this.setMembre(membre);
        this.setDate(datePosted);
    }

    public Date getDate() {
        return datePosted;
    }

    public void setDate(Date datePosted) {
        if (tache == null) {
            throw new IllegalArgumentException("La datePosted ne peut pas être nulle");
        }
        this.datePosted = datePosted;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        if (tache == null) {
            throw new IllegalArgumentException("La tache ne peut pas être nulle");
        }
        if (this.tache != null) {
            throw new IllegalArgumentException("Une tache à déjà été assignée à ce corps");
        }
        this.tache = tache;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Le membre ne peut pas être nulle");
        }
        if (this.membre != null) {
            throw new IllegalArgumentException("Un membre à déjà été assigné à ce corps");
        }
        this.membre = membre;
    }

    public String getCommentaire() {
        return corps;
    }

    public void setCommentaire(String corps) {
        if (corps == null) {
            throw new IllegalArgumentException("Le corps ne peut pas être nulle");
        }
        this.corps = corps;
    }

    public Long getId() {
        return id;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id : " + this.id + " membre : " + this.membre + " Tache : " + this.tache + " Commentaire : " + this.corps;
    }
}
