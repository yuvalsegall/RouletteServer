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
public class DuplicateNameException extends Exception {

    /**
     * Creates a new instance of <code>DuplicateNameException</code> without
     * detail message.
     */
    public DuplicateNameException() {
        super("Name already exist");
    }

    /**
     * Constructs an instance of <code>DuplicateNameException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateNameException(String msg) {
        super(msg);
    }
}
