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
public class NumOfPlayersException extends Exception {

    /**
     * Creates a new instance of <code>OutOfRange</code> without detail message.
     */
    public NumOfPlayersException(int min, int max) {
        super(String.format("Total number of players should be between %d to %d", min, max));
    }

    /**
     * Constructs an instance of <code>OutOfRange</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NumOfPlayersException(String msg) {
        super(msg);
    }
}
