package com.jugger.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jugger.tetris.Basic.Brick;
import com.jugger.tetris.Basic.C;
import com.jugger.tetris.Basic.Pos;
import com.jugger.tetris.BorderTextview.Tetris_Images;
import com.jugger.tetris.BorderTextview.Tetris_TextView;

public class MainActivity extends AppCompatActivity implements Game_Visual {

    private Tetris_TextView[][] main_field = new Tetris_TextView[C.rows.getCount()][C.columns.getCount()];
    private Tetris_TextView[][] nextBlock_field = new Tetris_TextView[4][4];
    private Game game;
    private TextView tV_score;
    private TextView tV_nextBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpGraphics();

        startGame();
    }

    private void setUpGraphics() {
        hide_supportActionbar();

        tV_score = findViewById(R.id.tVScore);
        tV_nextBlock = findViewById(R.id.tVnextBlock);

        fill_grids();

        Tetris_Images.setUp_pictures(main_field[0][0],this);
    }

    private void hide_supportActionbar() {
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) { }
    }

    private void startGame() {
        game = new Game(this);
        updatePanels(game);
        Thread g = new Thread(game);
        g.start();
    }

    private void fill_grids() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int display_width = size.x;
        int display_height = size.y;
        fill_nextBlock_grid();
        fill_main_grid(display_height ,display_width );
    }

    private void fill_main_grid(int screenHeight, int screen_width) {
        androidx.gridlayout.widget.GridLayout grid_layout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gLField);
        main_field = fill_grid(grid_layout,0.79*screenHeight,1*screen_width,C.rows.getCount(),C.columns.getCount());
    }

    private void fill_nextBlock_grid() {
        androidx.gridlayout.widget.GridLayout grid_layout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.grid_nextBlock);
        nextBlock_field = fill_grid(grid_layout,tV_score.getLineHeight(),tV_score.getLineHeight(),4,4);

    }

    private Tetris_TextView[][] fill_grid(androidx.gridlayout.widget.GridLayout grid_layout, double height, double width, int r, int c) {
        Tetris_TextView[][] objects_in_grid = new Tetris_TextView[r][c];

        int cell_height = (int)(height/r);
        int cell_width = (int)(width/c);
        for (int i = r-1; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                objects_in_grid[i][j] = new Tetris_TextView(this, C.emptyBrick_grey);
                objects_in_grid[i][j].setMinimumHeight(cell_height);
                objects_in_grid[i][j].setMinimumWidth(cell_width);
                objects_in_grid[i][j].setMaxHeight(cell_height);
                objects_in_grid[i][j].setMaxWidth(cell_width);
                // objects_in_grid[i][j].setText(i + ";" + j);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(0, 1), GridLayout.spec(0, 1));
                params.setGravity(1);
                objects_in_grid[i][j].setLayoutParams(params);
                grid_layout.addView(objects_in_grid[i][j]);
            }
        }
        return objects_in_grid;
    }

    public void update_visuals() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updatePanels(game);
            }
        });
    }

    private void updatePanels(Game game) {
        update_main_field_panels(game);
        update_top_panel(game);
    }

    public void move_block_down(View view) {
        game.moveBlock_down_ifPossible();
        update_main_field_panels(game);
    }

    public void move_block_right(View view) {
        game.moveBlock_right_ifPossible();
        update_main_field_panels(game);
    }

    public void move_block_left(View view) {
        game.moveBlock_left_ifPossible();
        update_main_field_panels(game);
    }

    public void rotate_block(View view) {
        game.rotateBlock_ifPossible();
        update_main_field_panels(game);
    }

    private void update_main_field_panels(Game game) {
        Brick[] bricks_of_block = game.getBlock().getBricks();
        Brick[][] bricks_of_field = game.getFieldBricks();

        for (int r = 0; r < bricks_of_field.length; r++) {
            for (int c = 0; c < bricks_of_field[r].length; c++) {
                if (bricks_of_field[r][c] != null) {
                    main_field[r][c].setBrick(bricks_of_field[r][c]);
                } else {
                    main_field[r][c].setBrick(C.emptyBrick_grey);
                }
                main_field[r][c].invalidate();
            }
        }
        for (int r = 0; r < bricks_of_block.length; r++) {
            Pos p = bricks_of_block[r].getPosition();
            main_field[p.r][p.c].setBrick(bricks_of_block[r]);
        }
    }

    private void update_top_panel(Game game) {
        tV_score.setText("score: " + game.getScore());
        tV_nextBlock.setText("nextBlock: " + game.getNextBlock().getBlockType());
        update_nextBlock_grid(game);
    }

    private void update_nextBlock_grid(Game game) {
        Brick[][] bricks_of_nextBlock = game.getNextBlock().getComposition();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if(bricks_of_nextBlock[3-r][c] != null) {
                    nextBlock_field[r][c].setBrick(new Brick(null,bricks_of_nextBlock[3-r][c].getColor(),-1));
                } else {
                    nextBlock_field[r][c].setBrick(C.emptyBrick_white);
                }
                nextBlock_field[r][c].invalidate();
            }
        }
    }
}
