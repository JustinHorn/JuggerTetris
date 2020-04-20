package com.jugger.tetris

import com.jugger.tetris.Block.Block
import com.jugger.tetris.Block.BlockOperations
import com.jugger.tetris.HighScore.Companion.onScoreChanged

class Game(graphic: Game_Visual) : Runnable {
    var block: Block
        private set
    val field: TetrisField
    var nextBlock: Block
        private set
    val game_is_on
        get() = this.field.arePositionsFree(block.points)
    var score = 0
        private set
    private val graphic: Game_Visual

    val sleepTime: Long
        get() = (Constants.startSleepMilliSeconds.count - score).toLong()

    init {
        nextBlock = BlockOperations.generateNewRandomBlock_withImages()
        block = BlockOperations.generateNewRandomBlock_withImages()
        field = TetrisField()
        this.graphic = graphic
    }

    override fun run() {
        while (game_is_on) {
            Thread.sleep(sleepTime)
            while (canBlockBeMovedDown(block)) {
                block = BlockOperations.moveDown(block)
                graphic.update_visuals()
                Thread.sleep(sleepTime)
            }
            field.addBricksToField( block.bricks)
            setScore(score + field.number_of_FullRows)
            field.overwriteFullRows()
            block = nextBlock
            nextBlock = BlockOperations.generateNewRandomBlock_withImages()
        }
        graphic.update_visuals()
    }




    private fun canBlockBeMovedDown(block: Block): Boolean {
        val down_by_One = BlockOperations.moveDown(block.copy())
        return blockLegal(down_by_One, field)
    }

    private fun blockLegal(block: Block, field: TetrisField): Boolean {
        return field.arePositionsFree(block.points)
    }


    private fun setScore(x: Int) {
        score = x
        onScoreChanged(score)
    }

    private fun pause(time_in_millis: Int) {
        try {
            Thread.sleep(time_in_millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun setBlockIfPossible(new_block: Block) {
        if (blockLegal(new_block, field)) {
            block = new_block
            graphic.update_visuals()
        }
    }


}