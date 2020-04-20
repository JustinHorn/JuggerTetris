package com.jugger.tetris.BorderImageview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.widget.AppCompatImageView
import com.jugger.tetris.Basic.Brick

class TetrisImageView(context: Context, var brick: Brick) : AppCompatImageView(context) {

    private val paint = Paint()
    private val color = Color.BLACK
    private val strokeWidth = 5

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintBorder(canvas)
        setBackgroundColor(brick.color)
        if (brick.image != -1) {
            setImageDrawable(JuggerTeamImages.get()!!.getPicture(brick.image))
        } else {
            setImageDrawable(null)
        }
    }

    fun paintBorder(canvas:Canvas) {
        paint.color = color
        paint.strokeWidth = strokeWidth.toFloat()
        canvas.drawLine(0f, 0f, width.toFloat(), 0f, paint)
        canvas.drawLine(width.toFloat(), 0f, width.toFloat(), height.toFloat(), paint)
        canvas.drawLine(0f, height.toFloat(), width.toFloat(), height.toFloat(), paint)
        canvas.drawLine(0f, 0f, 0f, height.toFloat(), paint)
    }

}