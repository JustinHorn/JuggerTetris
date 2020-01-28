package com.jugger.tetris.Block;


import com.jugger.tetris.Basic.Brick;
import com.jugger.tetris.Basic.C;

public class BlockOperations {

    public static Block generateNewRandomBlock() {
        return new Block(BlockTypes.getRandomBlockType(),-1);
    }

    public static Block generateNewRandomBlock_withImages() {
        int x = (int) (Math.random()* C.images.getCount());
        return new Block(BlockTypes.getRandomBlockType(),x);
    }



    public static Block getBlockRotation(Block b) {
        Brick[][] oldC = b.getComposition();
        Brick[][] newC = new Brick[4][4];
        Brick[] singleBricks = new Brick[4];
        int i =0;
        for(int r = 0; r < 4;r++) {
            for(int c = 0; c < 4;c++) {
                if(oldC[r][c] != null) {
                    newC[c][3-r] = oldC[r][c].copy();
                    newC[c][3-r].changePos(-c+r, -c+3-r);
                    singleBricks[i] = newC[c][3-r];
                    i++;
                }
            }
        }

        return new Block(singleBricks,newC,b.getBlockType());
    }

    public static void printBlockToConsole(Block b) {
        Brick[][] bricks = b.getComposition();
        System.out.println("Blockperspective!");
        for(int r = 0; r < 4;r++) {
            for(int c = 0; c < 4;c++) {
                if(bricks[r][c] != null) {
                    System.out.print("X");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println("____________");
    }

    public static Block moveBlock_downByOne(Block b) {
        Brick[] bricks = b.getBricks();
        for(int i = 0; i < bricks.length;i++) {
            bricks[i].changePos(-1, 0);
        }
        return b;
    }

    public static Block moveBlock_rightByOne(Block b) {
        Brick[] bricks = b.getBricks();
        for(int i = 0; i < bricks.length;i++) {
            bricks[i].changePos(0, +1);
        }
        return b;
    }

    public static Block moveBlock_leftByOne(Block b) {
        Brick[] bricks = b.getBricks();
        for(int i = 0; i < bricks.length;i++) {
            bricks[i].changePos(0, -1);
        }
        return b;
    }


}
