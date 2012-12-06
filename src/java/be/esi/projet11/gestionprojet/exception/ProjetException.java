/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.exception;

/**
 *
 * @author g32460
 */
public class ProjetException extends Exception {

    /**
     * Creates a new instance of
     * <code>ProjetException</code> without detail message.
     */
    public ProjetException() {
    }

    /**
     * Constructs an instance of
     * <code>ProjetException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ProjetException(String msg) {
        super(msg);
    }
}
