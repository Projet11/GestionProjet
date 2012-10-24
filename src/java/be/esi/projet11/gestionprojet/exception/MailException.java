/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.exception;

/**
 *
 * @author g34771
 */
public class MailException extends Exception {

    /**
     * Creates a new instance of
     * <code>mailException</code> without detail message.
     */
    public MailException() {
    }

    /**
     * Constructs an instance of
     * <code>mailException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MailException(String msg) {
        super(msg);
    }
}
