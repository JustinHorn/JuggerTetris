package com.jugger.tetris.Basic

/**Position */
class Point(var r: Int, var c: Int) {
    fun copy(): Point {
        return Point(r, c)
    }

    override fun toString(): String {
        return "row: $r col: $c"
    }

    companion object {
        fun create4NewPos(Ac: Int, Ar: Int, Bc: Int, Br: Int, Cc: Int, Cr: Int, Dc: Int, Dr: Int): Array<Point> {
            return arrayOf(Point(Ac, Ar), Point(Bc, Br), Point(Cc, Cr), Point(Dc, Dr))
        }

        fun create4NewPos(rowBeginningValue: Int, Ac: Int, Ar: Int, Bc: Int, Br: Int, Cc: Int, Cr: Int, Dc: Int, Dr: Int): Array<Point> {
            return arrayOf(Point(rowBeginningValue + Ac, Ar), Point(rowBeginningValue + Bc, Br), Point(rowBeginningValue + Cc, Cr), Point(rowBeginningValue + Dc, Dr))
        }
    }

}