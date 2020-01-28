package com.jugger.tetris.Field;

import com.jugger.tetris.Basic.Brick;
import com.jugger.tetris.Basic.Pos;


public class FieldOperations {

    public static void addBricksToField(TetrisField f, Brick[] block) {
        for (int i = 0; i < block.length; i++) {
            Pos p = block[i].getPosition();
            if (f.bricks[p.r][p.c] == null) {
                f.bricks[p.r][p.c] = block[i];
            } else {
                throw new IllegalArgumentException("Brick would be overwritten: " + p);
            }
        }
    }

    public static void overwriteRow(TetrisField f, int rowIndex) {
        Brick[][] bricks = f.bricks;
        for (int r = rowIndex; r < bricks.length - 1; r++) {
            for (int c = 0; c < bricks[r].length; c++) {
                bricks[r][c] = bricks[r + 1][c];
            }
        }
        if (rowIndex + 1 == bricks.length) {
            for (int c = 0; c < bricks[rowIndex].length; c++) {
                bricks[rowIndex][c] = null;
            }
        }
    }

    public static void overwriteFullRows(TetrisField f) {
        Brick[][] bricks = f.bricks;
        for (int r = 0; r < bricks.length; r++) {
            if (f.is_row_full(r)) {
                overwriteRow(f, r);
                r--;
            }
        }
    }

}
