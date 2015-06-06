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
public class GameNameExistException extends Exception {

    /**
     * Creates a new instance of <code>OutOfRange</code> without detail message.
     */
    public GameNameExistException() {
        super("Game name is taken");
    }

    /**
     * Constructs an instance of <code>OutOfRange</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GameNameExistException(String msg) {
        super(msg);
    }
}
