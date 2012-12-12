/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.exception;

/**
 *
 * @author g35001
 */
public class BusinessException extends Exception {

    /**
     * Creates a new instance of
     * <code>BusinessExceptino</code> without detail message.
     */
    public BusinessException() {
    }

    /**
     * Constructs an instance of
     * <code>BusinessExceptino</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
