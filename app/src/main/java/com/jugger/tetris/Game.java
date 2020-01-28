package com.jugger.tetris;
import com.jugger.tetris.Basic.*;
import com.jugger.tetris.Block.Block;
import com.jugger.tetris.Block.BlockOperations;
import com.jugger.tetris.Field.FieldOperations;
import com.jugger.tetris.Field.TetrisField;


public class Game implements Runnable {

    private Block block;
    private TetrisField field;
    private Block nextBlock;
    private boolean game_is_on = true;
    private int score = 0;
    private Game_Visual graphic;


    public Game(Game_Visual graphic) {
        nextBlock = BlockOperations. generateNewRandomBlock_withImages();
        block = BlockOperations.generateNewRandomBlock_withImages();
        field = new TetrisField();
        this.graphic = graphic;
    }

    @Override
    public void run() {
        while (game_is_on) {
            pause(getSleepTime());
            while (can_block_be_Moved_down(block)) {
                block = BlockOperations.moveBlock_downByOne(block);
                graphic.update_visuals();
                pause(getSleepTime());
            }

            update_field_and_score();

            block = nextBlock;
            nextBlock = BlockOperations.generateNewRandomBlock_withImages();

            game_is_on = can_block_Spawn(block);
        }
        graphic.update_visuals();
    }


    public int getSleepTime() {
        return   (C.startSleepMilliSeconds.getCount() - score);
    }

    private boolean can_block_Spawn(Block block) {
        return field.canBricksBeAdded_atPositions(block.getBrickPositions());
    }

    private boolean can_block_be_Moved_down(Block block) {
        Block down_by_One = BlockOperations.moveBlock_downByOne(block.copy());
        return block_legal(down_by_One,field);
    }
    private boolean block_legal(Block block, TetrisField field) {
        return field.canBricksBeAdded_atPositions(block.getBrickPositions());
    }

    private void update_field_and_score() {
        FieldOperations.addBricksToField(field,block.getBricks());
        score += field.getNumber_of_FullRows();
        FieldOperations.overwriteFullRows(field);
    }

    private void pause(int time_in_millis) {
        try {
            Thread.sleep(time_in_millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean gameRunning() {
        return game_is_on;
    }

    public int getScore() {
        return score;
    }

    public Block getBlock() {
        return block;
    }

    public Brick[][] getFieldBricks() {
        return field.getBricks();
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public void moveBlock_right_ifPossible() {
        Block right = BlockOperations.moveBlock_rightByOne(block.copy());
        set_new_block_if_Possible(right);

    }

    public void moveBlock_left_ifPossible() {
        Block left = BlockOperations.moveBlock_leftByOne(block.copy());
        set_new_block_if_Possible(left);
    }


    public void rotateBlock_ifPossible() {
        Block rotation = BlockOperations.getBlockRotation(block.copy());
        set_new_block_if_Possible(rotation);
    }

    public void moveBlock_down_ifPossible() {
        Block down = BlockOperations.moveBlock_downByOne(block.copy());
        set_new_block_if_Possible(down);
    }


    private void set_new_block_if_Possible(Block new_block) {
        if (block_legal(new_block,field)) {
            block = new_block;
        }
    }
}
