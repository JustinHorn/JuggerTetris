package com.jugger.tetris

import com.jugger.tetris.Basic.Brick
import com.jugger.tetris.Basic.Point

class TetrisField {
    var bricks:Array<Array<Brick?>> = Array(Constants.rows.count) { arrayOfNulls<Brick>(com.jugger.tetris.Constants.columns.count) }

    val number_of_FullRows: Int
        get() {
            var fullRows = 0
            nextRow@ for (r in bricks.indices) {
                for (c in bricks[r].indices) {
                    if (bricks[r][c] == null) {
                        continue@nextRow
                    }
                }
                fullRows++
            }
            return fullRows
        }

    fun isRowFull(row: Int): Boolean {
        for (c in bricks[row].indices) {
            if (bricks[row][c] == null) {
                return false
            }
        }
        return true
    }

    fun addBricksToField( block: List<Brick>) {
        for (i in block.indices) {
            val p = block[i].point
            if (bricks[p.r][p.c] == null) {
                bricks[p.r][p.c] = block[i]
            } else {
                throw IllegalArgumentException("Brick would be overwritten: $p")
            }
        }
    }

    fun overwriteRow( rowIndex: Int) {
        for (r in rowIndex until bricks.size - 1) {
            for (c in bricks[r].indices) {
                bricks[r][c] = bricks[r + 1][c]
            }
        }
        if (rowIndex + 1 == bricks.size) {
            for (c in bricks[rowIndex].indices) {
                bricks[rowIndex][c] = null
            }
        }
    }

    fun overwriteFullRows() {
        var r = 0
        while (r < bricks.size) {
            if (isRowFull(r)) {
                overwriteRow(r)
                r--
            }
            r++
        }
    }

    fun inBounds(p: Point): Boolean {
        return p.r < Constants.rows.count && p.r >= 0 && p.c < Constants.columns.count && p.c >= 0
    }

    fun arePositionsFree(positions: List<Point>): Boolean {
        for (p in positions) {
            if (!inBounds(p) || bricks[p.r][p.c] != null) {
                return false
            }
        }
        return true
    }

}