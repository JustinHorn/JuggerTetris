package com.jugger.tetris.BorderTextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class BorderedTextView extends AppCompatImageView {
    private Paint paint = new Paint();
    public static final int BORDER_TOP = 0x00000001;
    public static final int BORDER_RIGHT = 0x00000002;
    public static final int BORDER_BOTTOM = 0x00000004;
    public static final int BORDER_LEFT = 0x00000008;

    private Border[] borders;

    public BorderedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BorderedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderedTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        borders = new Border[4];
        for (int i = 0; i < 4; i++) {
            borders[i] = new Border((int) (0x00000000 + Math.pow(2, i)));
            borders[i].setWidth(5);
            borders[i].setColor(Color.BLACK);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (borders == null) return;

        for (Border border : borders) {
            paint.setColor(border.getColor());
            paint.setStrokeWidth(border.getWidth());

            switch (border.getStyle()) {
                case BORDER_TOP:
                    canvas.drawLine(0, 0, getWidth(), 0, paint);
                    break;

                case BORDER_RIGHT:
                    canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), paint);
                    break;

                case BORDER_BOTTOM:
                    canvas.drawLine(0, getHeight(), getWidth(), getHeight(), paint);
                    break;

                case BORDER_LEFT:
                    canvas.drawLine(0, 0, 0, getHeight(), paint);
                    break;
                default:
            }

        }
    }

    public Border[] getBorders() {
        return borders;
    }

    public void setBorders(Border[] borders) {
        this.borders = borders;
    }
}
