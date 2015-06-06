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
public class OddEvenBet extends Bet {

    private static final int NUM_OF_SAME_ODDEVEN_CELLS = 18;
    private final int dividend;

    public OddEvenBet(BigInteger betAmount, BetType betType, int dividend) {
        super(betAmount, betType);
        this.dividend = dividend;
    }

    @Override
    public BigInteger winningSum(Cell winnerCell, int numOfCellsInRoulette) {
        if (winnerCell.getColor() != Color.GREEN) {
            if (winnerCell.getValue() % 2 == dividend) {
                return getBetAmount().add(getBetAmount().multiply(BigInteger.ONE.divide(BigInteger.valueOf(NUM_OF_SAME_ODDEVEN_CELLS).multiply(BigInteger.valueOf(numOfCellsInRoulette).subtract(BigInteger.valueOf(NUM_OF_SAME_ODDEVEN_CELLS))))));
            }
        }

        return BigInteger.ZERO;
    }
}
