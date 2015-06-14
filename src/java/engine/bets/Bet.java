/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.bets;

import engine.BadParamsException;
import engine.Game;
import engine.RulesChecker;
import engine.Table.TableType;
import java.awt.Color;
import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author yuvalsegall
 */
public abstract class Bet implements Bettable {

    private final BigInteger betAmount;
    private final BetType betType;

    public Bet(BigInteger betAmount, BetType betType) {
        this.betAmount = betAmount;
        this.betType = betType;
    }

    public BetType getBetType() {
        return betType;
    }

    public BigInteger getBetAmount() {
        return betAmount;
    }

    public static Bet makeBet(BetType type, BigInteger betMoney, int[] numbers, TableType tableType) throws BadParamsException {
        Bet bet = null;

        if(betMoney.intValue() < 1)
            throw new BadParamsException();
        switch (type) {
            case SNAKE: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new NumbersBet(Game.ConstValuesForBets.SNAKE_VALUES, type, betMoney);
                break;
            }
            case COLUMN_1: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new ColumnBet(betMoney, type, Game.ConstValuesForBets.FIRST_COLUMN_HEAD);
                break;
            }
            case COLUMN_2: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new ColumnBet(betMoney, type, Game.ConstValuesForBets.SECOND_COLUMN_HEAD);
                break;
            }
            case COLUMN_3: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new ColumnBet(betMoney, type, Game.ConstValuesForBets.THIRD_COLUMN_HEAD);
                break;
            }
            case STRAIGHT:
            case SPLIT:
            case STREET:
            case SIX_LINE:
            case TRIO:
            case CORNER:
            case BASKET:
            case TOP_LINE:
                Arrays.sort(numbers);
                RulesChecker.checkNumberOfCellsLegal(numbers, type);
                RulesChecker.checkNumericalBetLegal(numbers, type, tableType);
                bet = new NumbersBet(numbers, type, betMoney);
                break;
            case MANQUE:
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new RangeBet(betMoney, type, Game.ConstValuesForBets.MIN_MANQUE, Game.ConstValuesForBets.MAX_MANQUE);
                break;
            case PASSE:
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new RangeBet(betMoney, type, Game.ConstValuesForBets.MIN_PASSE, Game.ConstValuesForBets.MAX_PASSE);
                break;
            case PREMIERE_DOUZAINE:
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new RangeBet(betMoney, type, Game.ConstValuesForBets.MIN_PREMIERE_DOUZAINE, Game.ConstValuesForBets.MAX_PREMIERE_DOUZAINE);
                break;
            case DERNIERE_DOUZAINE:
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new RangeBet(betMoney, type, Game.ConstValuesForBets.MIN_DERNIERE_DOUZAINE, Game.ConstValuesForBets.MAX_DERNIERE_DOUZAINE);
                break;
            case MOYENNE_DOUZAINE:
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new RangeBet(betMoney, type, Game.ConstValuesForBets.MIN_MOYENNE_DOUZAINE, Game.ConstValuesForBets.MAX_MOYENNE_DOUZAINE);
                break;
            case NOIR:
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new ColorBet(betMoney, type, Color.BLACK);
                break;
            case ROUGE: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new ColorBet(betMoney, type, Color.RED);
                break;
            }
            case PAIR: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new OddEvenBet(betMoney, type, Game.ConstValuesForBets.EVEN_DEVIDER);
                break;
            }
            case IMPAIR: {
                if (numbers != null) {
                    throw new BadParamsException();
                }
                bet = new OddEvenBet(betMoney, type, Game.ConstValuesForBets.ODD_DEVIDER);
                break;
            }
        }
        return bet;
    }

    public static enum BetType {

        STREET, BASKET, COLUMN_1, COLUMN_2, COLUMN_3, CORNER, DERNIERE_DOUZAINE, PAIR, MANQUE, MOYENNE_DOUZAINE, NOIR, IMPAIR, PASSE, PREMIERE_DOUZAINE, ROUGE, SIX_LINE, SNAKE, SPLIT, STRAIGHT, TOP_LINE, TRIO;

        public Boolean isAskUserForNumbers() {
            return !(this == ROUGE || this == NOIR || this == PAIR || this == IMPAIR || this == MANQUE || this == PASSE || this == PREMIERE_DOUZAINE || this == MOYENNE_DOUZAINE || this == DERNIERE_DOUZAINE || this == SNAKE || this == COLUMN_1 || this == COLUMN_2 || this == COLUMN_3);
        }

        public int getNumberOfCellsForBetBet(TableType tableType) {
            switch (this) {
                case STRAIGHT:
                    return 1;
                case SPLIT:
                    return 2;
                case STREET:
                    return 3;
                case CORNER:
                    return 4;
                case SIX_LINE:
                    return 6;
                case BASKET:
                    return tableType == TableType.AMERICAN ? 3 : 4;
                default:
                    return 0;
            }
        }
    }
}
