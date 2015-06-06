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
public class GameNameNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>OutOfRange</code> without detail message.
     */
    public GameNameNotFoundException() {
        super("Game not found");
    }

    /**
     * Constructs an instance of <code>OutOfRange</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GameNameNotFoundException(String msg) {
        super(msg);
    }
}
