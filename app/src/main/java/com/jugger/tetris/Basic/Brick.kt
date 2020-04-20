package com.jugger.tetris.Basic

import android.graphics.Color

open class Brick {
    var point: Point = Point(-1,-1)
        private set
    var color = Color.GRAY
        private set
    var image = -1

    companion object {
        val emptyBrick_grey = EmptyBrick(  Color.GRAY)
        val emptyBrick_white = EmptyBrick( Color.WHITE)
    }

    class EmptyBrick: Brick {
        constructor( color:Int) : super( color) {
        }
    }


    constructor(p: Point, c: Int, image: Int) {
        point = p
        color = c
        this.image = image
    }

    constructor( color:Int) {
        this.color = color
    }

    fun copy(): Brick {
        return Brick(point.copy(), color, image)
    }

    fun changePos(r: Int, c: Int) {
        point = Point(point.r + r, point.c + c)
    }

}