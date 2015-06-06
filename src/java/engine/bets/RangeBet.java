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
public class RangeBet extends Bet {

    protected final int MIN;
    protected final int MAX;

    public RangeBet(BigInteger betAmount, BetType betType, int min, int max) {
        super(betAmount, betType);
        this.MIN = min;
        this.MAX = max;
    }

    @Override
    public BigInteger winningSum(Cell winnerCell, int numOfCellsInRoulette) {
        if (winnerCell.getValue() >= MIN && winnerCell.getValue() <= MAX) {
            return getBetAmount().add(getBetAmount().multiply(BigInteger.ONE.divide(BigInteger.valueOf(MAX).subtract(BigInteger.valueOf(MIN).add(BigInteger.ONE))).multiply(BigInteger.valueOf(numOfCellsInRoulette).subtract(BigInteger.valueOf(MAX).subtract(BigInteger.valueOf(MIN).add(BigInteger.ONE))))));
        }

        return BigInteger.ZERO;
    }

}
