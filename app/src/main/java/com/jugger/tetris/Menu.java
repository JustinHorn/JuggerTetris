package com.jugger.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Menu extends AppCompatActivity {

    private static int highscore = 0;
    public static final String FILE_NAME= "score.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) { }
        rotateFont();
        load_score();
    }

    public void startGame(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void  openSettings(View view) {
        Intent i = new Intent(this,SettingsActivity.class);
        startActivity(i);
    }

    public void rotateFont() {
        TextView headline =findViewById(R.id.m_tV_headline);
        headline.setRotation(0);
        Thread t = getThread_that_rotates_View(headline);

        t.start();
    }

    private  void load_score() {
        try  {
            FileInputStream fos = openFileInput(FILE_NAME);
            StringBuffer s_b = new StringBuffer();
            int a;
            while((a = fos.read()) > -1) {
                s_b.append((char)a);
            }
            String integer = s_b.toString();
            highscore = Integer.parseInt(integer);
            TextView score = findViewById(R.id.m_tV_highScore);
            score.setText("Highscore "+integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Thread getThread_that_rotates_View(final View view) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable runnable = getRunnable_that_rotates_View(view);
                while(true) {
                    runOnUiThread(runnable);
                    try {
                        Thread.sleep(10);
                    } catch(InterruptedException e) {

                    }
                }
            }
        });
    }

    private Runnable getRunnable_that_rotates_View(final View view) {
        return  new Runnable() {

            private int x = 1;

            public void change_direction() {
                x = x*-1;
            }

            @Override
            public void run() {
                view.setRotation(view.getRotation() +x);
                if(view.getRotation() == -40 || view.getRotation() == 40 ) {
                    change_direction();
                }
            }
        };
    }

    public static int getHighscore() {
        return highscore;
    }


}

