package com.jugger.tetris.BorderTextview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.jugger.tetris.Basic.Brick;

public class Tetris_TextView extends BorderedTextView{

    private Brick brick;

    public Tetris_TextView(Context context, AttributeSet attrs, int defStyle, Brick brick) {
        super(context, attrs, defStyle);
        this.brick = brick;
    }

    public Tetris_TextView(Context context, AttributeSet attrs, Brick brick) {
        super(context, attrs);
        this.brick = brick;

    }

    public Tetris_TextView(Context context, Brick brick) {
        super(context);
        this.brick = brick;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(brick.getColor());
        if(brick.getImage() != -1) {
            setImageDrawable(Tetris_Images.pictures[brick.getImage()]);
        } else {
            setImageDrawable(null);
        }
    }

    public Brick getBrick() {
        return brick;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
    }

}
