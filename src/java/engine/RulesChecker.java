/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Table.TableType;
import engine.bets.Bet;
import static engine.bets.Bet.BetType.CORNER;
import static engine.bets.Bet.BetType.SPLIT;
import static engine.bets.Bet.BetType.STRAIGHT;
import static engine.bets.Bet.BetType.STREET;
import java.util.Arrays;

/**
 *
 * @author yuvalsegall
 */
public class RulesChecker {

    private static final int NUM_OF_CELLS_IN_STRAIGHT = 1;
    private static final int NUM_OF_CELLS_IN_SPLIT = 2;
    private static final int NUM_OF_CELLS_IN_STREET = 3;
    private static final int NUM_OF_CELLS_IN_CORNER = 4;
    private static final int NUM_OF_CELLS_IN_SIX_LINE = 6;

    public static void checkLegalTrio(int[] numbers, TableType tableType) throws BadParamsException {
        if (numbers.length != Game.ConstValuesForBets.TRIO_V1.length) {
            throw new BadParamsException();
        }
        if (!Arrays.equals(numbers, Game.ConstValuesForBets.TRIO_V1) && !Arrays.equals(numbers, Game.ConstValuesForBets.TRIO_V2)) {
            throw new BadParamsException();
        }
        if (tableType != Table.TableType.FRENCH) {
            throw new BadParamsException();
        }
    }

    public static void checkLegalCorner(int[] numbers) throws BadParamsException{
        try{
            checkLegalSplit(Arrays.copyOfRange(numbers, 0, numbers.length / 2));
            checkLegalSplit(Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length));
        }
        catch (BadParamsException e){
            throw new BadParamsException("illegal Corner");
        }
    }
    
    public static void checkLegalSplit(int[] numbers) throws BadParamsException {
        if (numbers[1] - numbers[0] != 1)
            throw new BadParamsException("illegal split");
        if (numbers[1] % Game.ConstValuesForBets.NUM_OF_COLS - numbers[0] % Game.ConstValuesForBets.NUM_OF_COLS != -2)
            throw new BadParamsException("illegal split");
    }

    public static void checkLegalStreet(int[] numbers) throws BadParamsException {
        if ((numbers[2] - numbers[1]) - (numbers[1] - numbers[0]) != 0)
            throw new BadParamsException("illegal Street");
        if (numbers[2] % Game.ConstValuesForBets.NUM_OF_COLS != 0)
                throw new BadParamsException("illegal Street");
    }

    public static void checkNumericalBetLegal(int[] numbers, Bet.BetType type, TableType tableType) throws BadParamsException {
        switch (type) {
            case SPLIT:
                checkLegalSplit(numbers);
                break;
            case STREET:
                checkLegalStreet(numbers);
                break;
            case CORNER:
                checkLegalCorner(numbers);
                break;
            case SIX_LINE:
                checkLegalSixLane(numbers);
                break;
            case BASKET:
                checkLegalBasket(numbers, tableType);
                break;
            case TOP_LINE:
                checkLegalTopLine(numbers, tableType);
        }
    }

    public static void checkLegalSixLane(int[] numbers) throws BadParamsException{
        try{
            checkLegalStreet(Arrays.copyOfRange(numbers, 0, numbers.length / 2 + 1));
            checkLegalStreet(Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length));
        }
        catch (BadParamsException e){
            throw new BadParamsException("illegal SixLane");
        }
    }
    
    public static void checkLegalBasket(int[] numbers, TableType tableType) throws BadParamsException {
        Arrays.sort(numbers);
        if (tableType == Table.TableType.FRENCH) {
            if (!Arrays.equals(numbers, Game.ConstValuesForBets.BASKET_FR)) {
                throw new BadParamsException("illegal basket");
            }
        } else {
            if (!Arrays.equals(numbers, Game.ConstValuesForBets.BASKET_AM_V1)) {
                if (!Arrays.equals(numbers, Game.ConstValuesForBets.BASKET_AM_V2)) {
                    if (!Arrays.equals(numbers, Game.ConstValuesForBets.BASKET_AM_V3)) {
                        throw new BadParamsException("illegal basket");
                    }
                }
            }
        }
    }

    public static void checkLegalTopLine(int[] numbers, TableType tableType) throws BadParamsException {
        if (tableType == Table.TableType.FRENCH) {
            throw new BadParamsException("no top line in american table");
        }
        Arrays.sort(numbers);
        if (!Arrays.equals(numbers, Game.ConstValuesForBets.TOP_LINE)) {
            throw new BadParamsException("illegal top line");
        }
    }

    public static void checkNumberOfCellsLegal(int[] numbers, Bet.BetType type) throws BadParamsException {
        switch (type) {
            case STRAIGHT:
                if (numbers.length != NUM_OF_CELLS_IN_STRAIGHT) {
                    throw new BadParamsException("Straight can ony be placed on one cell");
                }
                break;
            case SPLIT:
                if (numbers.length != NUM_OF_CELLS_IN_SPLIT) {
                    throw new BadParamsException("Split must be places on two cells");
                }
                break;
            case STREET:
                if (numbers.length != NUM_OF_CELLS_IN_STREET) {
                    throw new BadParamsException("Street must be places on three cells");
                }
                break;
            case CORNER:
                if (numbers.length != NUM_OF_CELLS_IN_CORNER) {
                    throw new BadParamsException("Corner must be places on four cells");
                }
                break;
            case SIX_LINE:
                if (numbers.length != NUM_OF_CELLS_IN_SIX_LINE) {
                    throw new BadParamsException("Six line must be places on six cells");
                }
                break;
        }
    }
}
