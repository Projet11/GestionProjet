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
    NORMALE("Normale", "N"),
    IMPORTANT("Important", "I"),
    TRESIMPORTANT("Très important", "TI");
     private ImportanceEnum(String libelle, String acro) {
        this.libelle = libelle;
        this.acro = acro;
    }   
    private String libelle, acro;

    public String getLibelle() {
        return libelle;
    }

    public String getAcro() {
        return acro;
    }
     
}
