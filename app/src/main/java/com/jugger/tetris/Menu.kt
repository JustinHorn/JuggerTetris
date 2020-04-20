package com.jugger.tetris

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*


class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        this.supportActionBar?.hide()
        rotateFont()

        var h =HighScore.getInstance(applicationContext.externalCacheDir)
        h.onHighscoreChanged = { score: Int ->
            runOnUiThread {
                m_tV_highScore.text = "Highscore $score"
            }
        }
        h.onHighscoreChanged(h.highscore)

    }

    fun startGame(view: View?) {
        val i = Intent(this, GameActivity::class.java)
        startActivity(i)
    }

    fun openSettings(view: View?) {}
    fun rotateFont() {
        val headline = m_tV_headline
        headline.rotation = 0f
        rotateView(headline).start()
    }

    private fun rotateView(view: View): Thread {
        return Thread(Runnable {
            val runnable = rotate(view)
            while (true) {
                runOnUiThread(runnable)
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                }
            }
        })
    }

    private fun rotate(view: View): Runnable {
        return object : Runnable {
            private var x = 1
            fun change_direction() {
                x = x * -1
            }
            override fun run() {
                view.rotation = view.rotation + x
                if (view.rotation == -40f || view.rotation == 40f) {
                    x = x * -1
                }
            }
        }
    }
}

