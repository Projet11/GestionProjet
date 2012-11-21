/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.sql.Time;
import java.util.Collection;
import javax.ejb.Local;
import tache.ImportanceEnum;

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
    
    public void startTimer(long id) ;
    
    public void stopTimer(long id) ;

    public Time getTimer(long id) ;

    public boolean isTimerLaunched(long id);
    
    //public String inscrireMembresATache(HttpServletRequest request);
}
