/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author yuvalsegall
 */
public class Table {

    public static final int[] american = new int[]{0, 28, 9, 26, 30, 11, 7, 20, 32, 17, 5, 22, 34, 15, 3, 24, 36, 13, 1, 37, 27, 10, 25, 29, 12, 8, 19, 31, 18, 6, 21, 33, 16, 4, 23, 35, 14, 2};
    public static final int[] french = new int[]{0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
    private final TableType tableType;
    private final Cell[] cells;
    private Cell currentBallPosition = null;

    public Table(TableType tableType) {
        this.tableType = tableType;
        cells = tableType == TableType.AMERICAN ? new Cell[american.length] : new Cell[french.length];
        setCells(tableType == TableType.AMERICAN ? american : french);
    }

    private void setCells(int[] values) {
        for (int i = 0; i < cells.length; i++) {
            if (values[i] == 0 || values[i] == 37) {
                cells[i] = new Cell(values[i], Color.GREEN);
            } else {
                cells[i] = new Cell(values[i], i % 2 == 0 ? Color.RED : Color.BLACK);
            }
        }
    }

    public TableType getTableType() {
        return tableType;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Cell spinRoulette() {
        currentBallPosition = cells[new Random().nextInt(cells.length - 0 + 1) + 0];
        return currentBallPosition;
    }

    public Cell getCurrentBallPosition() {
        return currentBallPosition;
    }

    public static enum TableType {

        AMERICAN, FRENCH;
    }

}
