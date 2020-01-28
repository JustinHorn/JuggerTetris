package com.jugger.tetris.Basic;

/**Position*/
public class Pos {

    public int r;
    public int c;

    public Pos(int row,int col) {
        r = row;
        c = col;
    }

    public static Pos[] create4NewPos(int Ac,int Ar,int Bc, int Br, int Cc, int Cr,int Dc, int Dr ) {
        return new Pos[] {new Pos(Ac,Ar), new Pos(Bc,Br), new Pos(Cc,Cr), new Pos(Dc,Dr)};
    }

    public static Pos[] create4NewPos(int rowBeginningValue, int Ac,int Ar,int Bc, int Br, int Cc, int Cr,int Dc, int Dr ) {
        int rbv = rowBeginningValue;
        return new Pos[] {new Pos(rbv+Ac,Ar), new Pos(rbv+Bc,Br), new Pos(rbv+Cc,Cr), new Pos(rbv+Dc,Dr)};
    }

    public Pos copy() {
        return new Pos(r,c);
    }

    public String toString() {
        return "row: "+r+" col: "+c;
    }
}
