package com.jugger.tetris

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.jugger.tetris.databinding.ActivityMenuBinding


class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        rotateFont()

        var h =HighScore.getInstance(applicationContext.filesDir)
        h.onHighscoreChanged = { score: Int ->
            runOnUiThread {
                binding.mTVHighScore.text = "Highscore $score"
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
        rotateView(binding.mTVHeadline).start()
    }

    /** swings the view between -40 and 40 degrees, starting in the middle */
    private fun rotateView(view: View): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, View.ROTATION, -40f, 40f).apply {
            duration = 800
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            currentPlayTime = 400
        }
    }
}
