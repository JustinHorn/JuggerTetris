package com.jugger.tetris.Field;

import com.jugger.tetris.Basic.Brick;
import com.jugger.tetris.Basic.C;
import com.jugger.tetris.Basic.Pos;

public class TetrisField {
    protected Brick[][] bricks = new Brick[C.rows.getCount()][C.columns.getCount()];

    public int getNumber_of_FullRows() {
        int fullRows = 0;
        nextRow: for (int r = 0; r < bricks.length; r++) {
            for (int c = 0; c < bricks[r].length; c++) {
                if (bricks[r][c] == null) {
                    continue nextRow;
                }
            }
            fullRows++;
        }
        return fullRows;
    }

    public boolean is_row_full(int row) {
        for (int c = 0; c < bricks[row].length; c++) {
            if (bricks[row][c] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean inBounds(Pos p) {
        return p.r < C.rows.getCount() && p.r >=0 && p.c < C.columns.getCount() && p.c >=0;
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public boolean canBricksBeAdded_atPositions(Pos[] positions) {
        for (Pos p : positions) {
            if (!inBounds(p) || bricks[p.r][p.c] != null) {
                return false;
            }
        }
        return true;
    }
}
