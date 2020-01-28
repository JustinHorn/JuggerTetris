package com.jugger.tetris.Block;


import com.jugger.tetris.Basic.Brick;
import com.jugger.tetris.Basic.C;
import com.jugger.tetris.Basic.Pos;


public class Block {

    private Brick[] singleBricks;
    private Brick[][] composition = new Brick[4][4];
    private BlockTypes blockType;


    public Block(BlockTypes t, int image) {
        constructor(t,image);
    }

    private void constructor(BlockTypes t, int image) {
        blockType = t;
        Pos[] p = t.getSpawnPos();
        singleBricks = new Brick[p.length];
        for(int i = 0; i < p.length;i++ ) {
            singleBricks[i] = new Brick(p[i],t.getColor(),image);
            composition[C.rows.getCount()-p[i].r][p[i].c-C.blockStartIndex.getCount()] = singleBricks[i];
        }
    }

    public Block(Brick[] b, Brick[][] comp, BlockTypes t) {
        singleBricks = b;
        composition = comp;
        this.blockType = t;
    }

    public Brick[] getBricks() {
        return singleBricks;
    }

    public Brick[][] getComposition() {
        return composition;
    }

    public BlockTypes getBlockType() {
        return blockType;
    }


    public Block copy() {
        Brick[] brick = new Brick[singleBricks.length];
        Brick[][] compositionN = new Brick[4][4];
        int i = 0;
        for(int r = 0; r < 4;r++) {
            for(int c = 0; c < 4;c++) {
                if(composition[r][c] != null) {
                    brick[i] = composition[r][c].copy();
                    compositionN[r][c] = brick[i];
                    i++;
                }
            }
        }
        return new Block(brick,compositionN, blockType);
    }

    public Pos[] getBrickPositions() {
        int l = singleBricks.length;
        Pos[] p = new Pos[l];
        for(int i = 0; i < l; i++) {
            p[i] = singleBricks[i].getPosition().copy();
        }
        return p;
    }

}
