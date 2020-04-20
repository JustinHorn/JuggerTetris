package com.jugger.tetris.Block

import android.graphics.Color
import com.jugger.tetris.Basic.Point
import com.jugger.tetris.Constants

enum class BlockTypes(val spawnPos: Array<Point>, val color: Int) {
    I(Point.Companion.create4NewPos(Constants.rows.count, -1, 2, -1, 3, -1, 4, -1, 5), Color.rgb(0, 0, 255)), L(Point.Companion.create4NewPos(Constants.rows.count, -1, 2, -1, 3, -1, 4, -2, 2), Color.rgb(0, 255, 0)), J(Point.Companion.create4NewPos(Constants.rows.count, -1, 2, -2, 2, -2, 3, -2, 4), Color.rgb(255, 0, 0)), O(Point.Companion.create4NewPos(Constants.rows.count, -1, 3, -1, 4, -2, 3, -2, 4), Color.rgb(0, 0, 0)), S(Point.Companion.create4NewPos(Constants.rows.count, -1, 4, -1, 5, -2, 3, -2, 4), Color.rgb(128, 0, 0)), T(Point.Companion.create4NewPos(Constants.rows.count, -1, 3, -2, 2, -2, 3, -2, 4), Color.rgb(128, 128, 128)), Z(Point.Companion.create4NewPos(Constants.rows.count, -1, 2, -1, 3, -2, 3, -2, 4), Color.rgb(128, 128, 0));

    companion object {
        private const val number = 7
        var array = arrayOf(I, J, L, O, S, T, Z)
        val randomBlockType: BlockTypes
            get() {
                val random = (Math.random() * number).toInt()
                return when (random) {
                    0 -> I
                    1 -> J
                    2 -> L
                    3 -> O
                    4 -> S
                    5 -> T
                    6 -> Z
                    else -> I
                }
            }
    }

}