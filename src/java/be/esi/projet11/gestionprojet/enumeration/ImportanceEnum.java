/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tache;

/**
 *
 * @author j4un3
 */
public enum ImportanceEnum {

    NORMALE("Normale", 3,"info"),
    IMPORTANT("Important", 2,"warning"),
    TRESIMPORTANT("Très important", 1,"error");
    
    private String libelle;
    private int imp;
    private String balise;

    private ImportanceEnum(String libelle, int imp, String balise) {
        this.libelle = libelle;
        this.imp = imp;
        this.balise = balise;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getImp() {
        return imp;
    }
    
    public String getBalise(){
        return balise;
    }
}
