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
public class NumbersBet extends Bet {

    private final int[] numbers;

    public NumbersBet(int[] numbers, BetType betType, BigInteger betAmount) {
        super(betAmount, betType);
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public BigInteger winningSum(Cell winnerCell, int numOfCellsInRoulette) {
        for (int number : numbers) {
            if (winnerCell.getValue() == number) {
                return getBetAmount().add(getBetAmount().multiply(BigInteger.ONE.divide(BigInteger.valueOf(numbers.length)).multiply(BigInteger.valueOf(numOfCellsInRoulette).subtract(BigInteger.valueOf(numbers.length)))));
            }
        }

        return BigInteger.ZERO;
    }

}
