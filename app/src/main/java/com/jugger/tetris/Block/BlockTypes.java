package com.jugger.tetris.Block;


import android.graphics.Color;

import com.jugger.tetris.Basic.C;
import com.jugger.tetris.Basic.Pos;

public enum BlockTypes {

    I(Pos.create4NewPos(C.rows.getCount(),-1,2 , -1,3 , -1,4 , -1,5), Color.rgb(0,0,255)),
    L(Pos.create4NewPos(C.rows.getCount(),-1,2 , -1,3 , -1,4 , -2,2),Color.rgb(0,255,0)),
    J(Pos.create4NewPos(C.rows.getCount(),-1,2 , -2,2 , -2,3 , -2,4),Color.rgb(255,0,0)),
    O(Pos.create4NewPos(C.rows.getCount(),-1,3 , -1,4 , -2,3 , -2,4),Color.rgb(0,0,0)),
    S(Pos.create4NewPos(C.rows.getCount(),-1,4 , -1,5 , -2,3 , -2,4),Color.rgb(128,0,0)),
    T(Pos.create4NewPos(C.rows.getCount(),-1,3 , -2,2 , -2,3 , -2,4),Color.rgb(128,128,128)),
    Z(Pos.create4NewPos(C.rows.getCount(),-1,2 , -1,3 , -2,3 , -2,4),Color.rgb(128,128,0));

    private final Pos[] spawn;
    private final int c;
    private static int number = 7;
    public static BlockTypes[] array = new BlockTypes[]{I,J,L,O,S,T,Z};

    BlockTypes(Pos[] spawn, int c) {
        this.spawn = spawn;
        this.c = c;
    }

    public static BlockTypes getRandomBlockType() {
        int random = (int) (Math.random()*number);
        switch(random) {
            case 0:return I;
            case 1:return J;
            case 2:return L;
            case 3:return O;
            case 4:return S;
            case 5:return T;
            case 6:return Z;
            default: return I;
        }
    }


    public Pos[] getSpawnPos( ) {
        return spawn;
    }

    public int getColor( ) {
        return c;
    }
}

