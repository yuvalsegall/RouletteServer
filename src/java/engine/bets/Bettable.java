/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.bets;

import engine.Cell;
import java.math.BigInteger;

/**
 *
 * @author yuvalsegall
 */
public interface Bettable {
   public BigInteger winningSum(Cell winnerCell, int numOfCellsInRoulette); 
}