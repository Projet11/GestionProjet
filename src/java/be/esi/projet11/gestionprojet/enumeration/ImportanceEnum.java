/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.enumeration;

/**
 *
 * @author j4un3
 */
public enum ImportanceEnum {

    NORMALE("Normale", 3),
    IMPORTANT("Important", 2),
    TRESIMPORTANT("Très important", 1);
    
    private String libelle;
    private int imp;

    private ImportanceEnum(String libelle, int imp) {
        this.libelle = libelle;
        this.imp = imp;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getImp() {
        return imp;
    }
}
