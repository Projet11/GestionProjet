/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author g34840
 */
@Local
public interface TacheEJBLocal {

    Tache creerTache(String nom, String description) throws TacheException;

    Tache creerTache(String nom, String description, ImportanceEnum importance) throws TacheException;

    Collection<Tache> getAllTache();

    Tache getTache(String nom);

    Tache getTache(long id);
}
