package com.jugger.tetris.Block

import com.jugger.tetris.Basic.Brick
import com.jugger.tetris.Constants

object BlockOperations {
    fun generateNewRandomBlock(): Block {
        return Block(BlockTypes.randomBlockType, -1)
    }

    fun generateNewRandomBlock_withImages(): Block {
        val x = (Math.random() * Constants.images.count).toInt()
        return Block(BlockTypes.randomBlockType, x)
    }

    fun getBlockRotation(b: Block): Block {
        val oldC = b.composition
        val newC = Array(4) { arrayOfNulls<Brick>(4) }
        val singleBricks = mutableListOf<Brick>()
        for (r in 0..3) {
            for (c in 0..3) {
                if (oldC[r][c] != null) {
                    newC[c][3 - r] = oldC[r][c]!!.copy()
                    newC[c][3 - r]!!.changePos(-c + r, -c + 3 - r)
                    singleBricks.add(newC[c][3 - r]!!)
                }
            }
        }
        return Block(singleBricks, newC, b.blockType)
    }


    fun moveDown(block: Block): Block {
        val b =block.copy()
        val bricks = b.bricks
        for (i in bricks.indices) {
            bricks[i].changePos(-1, 0)
        }
        return b
    }

    fun moveRight(block: Block): Block {
        val b =block.copy()
        val bricks = b.bricks
        for (i in bricks.indices) {
            bricks[i].changePos(0, +1)
        }
        return b
    }

    fun moveLeft(block: Block): Block {
        val b =block.copy()
        val bricks = b.bricks
        for (i in bricks.indices) {
            bricks[i].changePos(0, -1)
        }
        return b
    }
}