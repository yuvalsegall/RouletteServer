/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.exceptions;

/**
 *
 * @author yuvalsegall
 */
public class EmptyNameException extends Exception {

    /**
     * Creates a new instance of <code>DuplicateNameException</code> without
     * detail message.
     */
    public EmptyNameException() {
        super("Name cannot be empty");
    }

    /**
     * Constructs an instance of <code>DuplicateNameException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyNameException(String msg) {
        super(msg);
    }
}
