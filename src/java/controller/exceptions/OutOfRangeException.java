/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.exceptions;

/**
 *
 * @author Yuval Segall
 */
public class OutOfRangeException extends Exception {

    /**
     * Creates a new instance of <code>OutOfRange</code> without detail message.
     */
    public OutOfRangeException() {
        super("Invalide properties");
    }

    /**
     * Constructs an instance of <code>OutOfRange</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public OutOfRangeException(String msg) {
        super(msg);
    }
}
