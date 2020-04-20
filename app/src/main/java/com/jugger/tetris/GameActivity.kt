package com.jugger.tetris

import android.graphics.Point
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.jugger.tetris.Basic.Brick
import com.jugger.tetris.Block.BlockOperations
import com.jugger.tetris.BorderImageview.JuggerTeamImages
import com.jugger.tetris.BorderImageview.TetrisImageView
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity(), Game_Visual {
    private lateinit var main_field : Array<Array<TetrisImageView>>
    private lateinit var nextBlockGrid: Array<Array<TetrisImageView>>
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpGraphics()
        startGame()
        btnDown.setOnTouchListener (RepeatListener(100,100, View.OnClickListener { v:View -> runOnUiThread {
            moveBlockDown(v)
        }  }))
    }

    private fun setUpGraphics() {
        hide_supportActionbar()
        fillGrids()
        JuggerTeamImages.init(main_field[0][0], this)
    }

    private fun hide_supportActionbar() {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }

    private fun startGame() {
        game = Game(this)
        updatePanels(game)
        val g = Thread(game)
        g.start()
    }

    private fun fillGrids() {
        val size = displaySize
        fillNextBlockGrid()
        fillMainGrid(size.x, size.y)
    }

    private val displaySize: Point
        private get() {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size
        }

    private fun fillMainGrid(screen_width: Int, screenHeight: Int) {
        val grid_layout = findViewById<View>(R.id.gLField) as GridLayout
        main_field = fillGrid(grid_layout, 0.79 * screenHeight, 1 * screen_width.toDouble(), Constants.rows.count, Constants.columns.count)
    }

    private fun fillNextBlockGrid() {
        val grid_layout = findViewById<View>(R.id.grid_nextBlock) as GridLayout
        nextBlockGrid = fillGrid(grid_layout, tVScore.lineHeight.toDouble(), tVScore.lineHeight.toDouble(), 4, 4)
    }

    private fun fillGrid(grid_layout: GridLayout, height: Double, width: Double, r: Int, c: Int): Array<Array<TetrisImageView>> {
        val objects_in_grid = Array(r) { arrayOfNulls<TetrisImageView>(c) }
        val cell_height = (height / r).toInt()
        val cell_width = (width / c).toInt()
        for (i in r - 1 downTo 0) {
            for (j in 0 until c) {
                objects_in_grid[i][j] = TetrisImageView(this, Brick.emptyBrick_grey)
                objects_in_grid[i][j]!!.minimumHeight = cell_height
                objects_in_grid[i][j]!!.minimumWidth = cell_width
                objects_in_grid[i][j]!!.maxHeight = cell_height
                objects_in_grid[i][j]!!.maxWidth = cell_width
                // objects_in_grid[i][j].setText(i + ";" + j);
                val params = android.widget.GridLayout.LayoutParams(android.widget.GridLayout.spec(0, 1), android.widget.GridLayout.spec(0, 1))
                params.setGravity(1)
                objects_in_grid[i][j]!!.layoutParams = params
                grid_layout.addView(objects_in_grid[i][j])
            }
        }
        return objects_in_grid.map { it.filterNotNull().toTypedArray() }.toTypedArray()
    }

    override fun update_visuals() {
        runOnUiThread { updatePanels(game) }
    }

    private fun updatePanels(game: Game) {
        updatePanelsOfMainField(game)
        updateHeaderPanel(game)
    }


    private fun updatePanelsOfMainField(game: Game) {
        val bricks_of_block = game.block.bricks
        val bricks_of_field = game.field.bricks
        for (r in bricks_of_field.indices) {
            for (c in bricks_of_field[r].indices) {
                if (bricks_of_field[r][c] != null) {
                    main_field[r][c].brick = bricks_of_field[r][c]!!
                } else {
                    main_field[r][c].brick = Brick.emptyBrick_grey
                }
                main_field[r][c].invalidate()
            }
        }
        for (r in bricks_of_block.indices) {
            val p = bricks_of_block[r].point
            main_field[p.r][p.c].brick = bricks_of_block[r]
        }
    }

    private fun updateHeaderPanel(game: Game) {
        tVScore.text = "score: " + game.score
        tVnextBlock.text = "nextBlock: " + game.nextBlock.blockType
        updateNextBlockGrid(game)
    }

    private fun updateNextBlockGrid(game: Game) {
        val nextBlockComposition: Array<Array<Brick?>> = game.nextBlock.composition
        for (r in 0..3) {
            for (c in 0..3) {
                if (nextBlockComposition[3 - r][c] != null) {
                    nextBlockGrid[r][c].brick = (Brick(nextBlockComposition[3 - r][c]!!.color))
                } else {
                    nextBlockGrid[r][c].brick = (Brick.emptyBrick_white)
                }
                nextBlockGrid[r][c].invalidate()
            }
        }
    }

    fun moveBlockDown(view: View?) {
        val down = BlockOperations.moveDown(game.block)
        game.setBlockIfPossible(down)
    }

    fun moveBlockRight(view: View?) {
        val right = BlockOperations.moveRight(game.block)
        game.setBlockIfPossible(right)
    }

    fun moveBlockLeft(view: View?) {
        val left = BlockOperations.moveLeft(game.block)
        game.setBlockIfPossible(left)

    }

    fun rotateBlock(view: View?) {
        val rotation = BlockOperations.getBlockRotation(game.block)
        game.setBlockIfPossible(rotation)

    }

}