package com.jugger.tetris

import android.util.Log
import android.widget.TextView
import java.io.*


class HighScore private constructor(val PLACE: File)  {

    var onHighscoreChanged = { _:Int ->}

    var highscore = 0
        set(x) {
            field = x
            onHighscoreChanged(field)
        }

    private val FILE_NAME = "score.txt"

    init {
        loadScore()
    }

    companion object {
        private const val LOG_TAG = "Highscore"
        @Volatile
        private var INSTANCE: HighScore? = null
        fun getInstance(place: File): HighScore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: HighScore(place).also { INSTANCE = it }
            }
        }
        fun onScoreChanged(x:Int) {
            INSTANCE?.onScoreChanged(x)
        }
    }

    fun onScoreChanged(x: Int) {
        if (x > highscore) {
            highscore = x
            scoreToFile()
        }
    }

    fun loadScore() {
        val f = File(PLACE, FILE_NAME)
        if (!f.exists()) {
            f.createNewFile()
        } else {
            var text= f.readText()
            highscore=if (text == "")  0 else text.toInt()
            Log.v(LOG_TAG,"Score loaded from file: $highscore")
        }
    }


    fun scoreToFile() {
        val f = File(PLACE, FILE_NAME).writeText(highscore.toString())
        Log.v(LOG_TAG,"Score written to file")
    }
}