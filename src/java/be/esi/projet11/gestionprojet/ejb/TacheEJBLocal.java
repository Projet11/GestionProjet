/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author user0
 */
@Local
public interface TacheEJBLocal {
    public Tache creerTache(String nom,String description);
    public Tache creerTache(String nom,String description,ImportanceEnum imp);
    public Tache getTache(String nom);
    public Tache getTache(Long id);
    public Collection<Tache> getAllTache();
    public void modificationTache(Tache tache);
}
