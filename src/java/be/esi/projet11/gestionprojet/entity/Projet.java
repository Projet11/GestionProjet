/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author g35364
 */
@Entity
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    public Set<Membre> getMembres() {
        Set<Membre> membres = new HashSet<Membre>();
        
        membres.add(new Membre(1l, "Membre 1", "membre1@gmail.com"));
        membres.add(new Membre(2l, "Membre 2", "membre2@yahoo.com"));
        membres.add(new Membre(3l, "Membre 3", "membre3@gmail.com"));
        return membres;
    }
}
