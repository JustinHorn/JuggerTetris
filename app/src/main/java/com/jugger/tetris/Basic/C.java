package com.jugger.tetris.Basic;

import android.graphics.Color;

public enum C {
    columns(8),rows(20),blockStartIndex(2),startSleepMilliSeconds(750),images(42);
    private final int value;
    public static final Brick emptyBrick_grey = new Brick(null, Color.GRAY,-1);
    public static final Brick emptyBrick_white = new Brick(null, Color.WHITE,-1);

    C(int v) {
        value = v;
    }

    public int getCount() {
        return value;
    }
}
