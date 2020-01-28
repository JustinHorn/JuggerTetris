package com.jugger.tetris.Basic;

public class Brick {

    private Pos p;

    private int c;

    private int image;

    public Brick(Pos p, int c) {
        this.p = p;
        this.c = c;
    }

    public Brick(Pos p, int c, int image) {
        this.p = p;
        this.c = c;
        this.image = image;
    }

    public Brick copy() {
        return new Brick(p.copy(), c, image);
    }

    public void changePos(int r, int c) {
        p = new Pos(p.r + r, p.c + c);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getColor() {
        return c;
    }

    public Pos getPosition() {
        return p;
    }
}


