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
public class ColumnBet extends Bet {

    private final int columnHead;

    public ColumnBet(BigInteger betAmount, BetType betType, int columnHead) {
        super(betAmount, betType);
        this.columnHead = columnHead;
    }

    @Override
    public BigInteger winningSum(Cell winnerCell, int numOfCellsInRoulette) {
        if (winnerCell.getValue() % columnHead == 0) {
            return getBetAmount().add(getBetAmount().multiply(BigInteger.valueOf(2)));
        }

        return BigInteger.ZERO;
    }

}
