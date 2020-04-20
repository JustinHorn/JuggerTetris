package com.jugger.tetris.Block

import com.jugger.tetris.Basic.Brick
import com.jugger.tetris.Basic.Point
import com.jugger.tetris.Constants

class Block {
    lateinit var bricks: List<Brick>
        private set
    var composition = Array(4) { arrayOfNulls<Brick>(4) }
        private set
    var blockType: BlockTypes? = null
        private set
    val points
        get() = bricks.map { it.point  }

    constructor(t: BlockTypes, image: Int) {
        constructor(t, image)
    }

    private fun constructor(t: BlockTypes, image: Int) {
        blockType = t
        val p = t.spawnPos
        bricks = p.map { Brick(it, t.color, image) }
        for (i in p.indices) {
            composition[Constants.rows.count - p[i].r][p[i].c - Constants.blockStartIndex.count] = bricks[i]
        }
    }

    constructor(b: List<Brick>, comp: Array<Array<Brick?>>, t: BlockTypes?) {
        bricks = b
        composition = comp
        blockType = t
    }

    fun copy(): Block {
        var compositionN = composition.map { it.map { if(it!=null) it.copy() else null }.toTypedArray() }.toTypedArray()
        var bricksN= mutableListOf<Brick>()
        compositionN.forEach { it.filterNotNull().forEach{ it2 -> bricksN.add(it2)} }
        return Block(bricksN, compositionN, blockType)
    }

}