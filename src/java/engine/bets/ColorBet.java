/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.bets;

import engine.Cell;
import java.awt.Color;
import java.math.BigInteger;

/**
 *
 * @author yuvalsegall
 */
public class ColorBet extends Bet{
    private static final int NUM_OF_SAME_COLOR_CELLS = 18;
    private final Color color;
    
    public ColorBet(BigInteger betAmount, BetType betType, Color color) {
        super(betAmount, betType);
        this.color = color;
    }

    @Override
    public BigInteger winningSum(Cell winnerCell, int numOfCellsInRoulette) {
        if(winnerCell.getColor() == color){
            return getBetAmount().add(getBetAmount().multiply(BigInteger.ONE.divide(BigInteger.valueOf(NUM_OF_SAME_COLOR_CELLS))).multiply(BigInteger.valueOf(numOfCellsInRoulette).subtract(BigInteger.valueOf(NUM_OF_SAME_COLOR_CELLS))));
        }
        
        return BigInteger.ZERO;
    }
}
