package com.jugger.tetris.BorderTextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.jugger.tetris.C;
import com.jugger.tetris.R;

/** Singelton that loads and stores images for the bicks)**/
public class Tetris_Images {

    public BitmapDrawable getPicture(int i) {
        return pictures[i];
        // why no commit
    }

    public static Tetris_Images get() {
        return instance;
    }

    public static Tetris_Images get(View v,Context c) {
        if(instance == null) {
            instance = new Tetris_Images(v,c);
        }
        return instance;
    }

    private BitmapDrawable[] pictures;

    private static Tetris_Images instance;

    private Tetris_Images(View v, Context context) {
        setUp_pictures(v,context);
    }



    private void setUp_pictures(View view,Context context){
        Bitmap[] pic = load_pictures(context);
        pic = resize_pictures(pic,view);
        pictures = turn_pictures_drawable(pic,context);
    }

    private Bitmap[] load_pictures(Context context) {
        Bitmap[] pictures = new Bitmap[C.images.getCount()];
        pictures[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.chimaere);
        pictures[1] =BitmapFactory.decodeResource(context.getResources(),R.drawable.zonenkinder);
        pictures[2] =BitmapFactory.decodeResource(context.getResources(),R.drawable.rigor);
        pictures[3] =BitmapFactory.decodeResource(context.getResources(),R.drawable.peterpawns);
        pictures[4] =BitmapFactory.decodeResource(context.getResources(),R.drawable.bamberg);
        pictures[5] =BitmapFactory.decodeResource(context.getResources(),R.drawable.banane);
        pictures[6] =BitmapFactory.decodeResource(context.getResources(),R.drawable.blutgraetsche);
        pictures[7] =BitmapFactory.decodeResource(context.getResources(),R.drawable.bobjugger);
        pictures[8] =BitmapFactory.decodeResource(context.getResources(),R.drawable.bonn);
        pictures[9] =BitmapFactory.decodeResource(context.getResources(),R.drawable.falco);
        pictures[10] =BitmapFactory.decodeResource(context.getResources(),R.drawable.fkk);
        pictures[11] =BitmapFactory.decodeResource(context.getResources(),R.drawable.flying_juggmen);
        pictures[12] =BitmapFactory.decodeResource(context.getResources(),R.drawable.gag);
        pictures[13] =BitmapFactory.decodeResource(context.getResources(),R.drawable.greek);
        pictures[14] =BitmapFactory.decodeResource(context.getResources(),R.drawable.hagenwuppertal);
        pictures[15] =BitmapFactory.decodeResource(context.getResources(),R.drawable.hlu);
        pictures[16] =BitmapFactory.decodeResource(context.getResources(),R.drawable.hobbiz);
        pictures[17] =BitmapFactory.decodeResource(context.getResources(),R.drawable.indianer);
        pictures[18] =BitmapFactory.decodeResource(context.getResources(),R.drawable.jugg_sth);
        pictures[19] =BitmapFactory.decodeResource(context.getResources(),R.drawable.juggernauts);
        pictures[20] =BitmapFactory.decodeResource(context.getResources(),R.drawable.jugglersjugg);
        pictures[21] =BitmapFactory.decodeResource(context.getResources(),R.drawable.keiler);
        pictures[22] =BitmapFactory.decodeResource(context.getResources(),R.drawable.keuleneulen);
        pictures[23] =BitmapFactory.decodeResource(context.getResources(),R.drawable.krake);
        pictures[24] =BitmapFactory.decodeResource(context.getResources(),R.drawable.kuhdorf);
        pictures[25] =BitmapFactory.decodeResource(context.getResources(),R.drawable.leere_menge);
        pictures[26] =BitmapFactory.decodeResource(context.getResources(),R.drawable.leipziger_nachtwache);
        pictures[27] =BitmapFactory.decodeResource(context.getResources(),R.drawable.mainz);
        pictures[28] =BitmapFactory.decodeResource(context.getResources(),R.drawable.nsa);
        pictures[29] =BitmapFactory.decodeResource(context.getResources(),R.drawable.over9000);
        pictures[30] =BitmapFactory.decodeResource(context.getResources(),R.drawable.paderbears);
        pictures[31] =BitmapFactory.decodeResource(context.getResources(),R.drawable.pferd);
        pictures[32] =BitmapFactory.decodeResource(context.getResources(),R.drawable.pink_pain);
        pictures[33] =BitmapFactory.decodeResource(context.getResources(),R.drawable.s);
        pictures[34] =BitmapFactory.decodeResource(context.getResources(),R.drawable.schild);
        pictures[35] =BitmapFactory.decodeResource(context.getResources(),R.drawable.schild_blau);
        pictures[36] =BitmapFactory.decodeResource(context.getResources(),R.drawable.schloss);
        pictures[37] =BitmapFactory.decodeResource(context.getResources(),R.drawable.skull);
        pictures[38] =BitmapFactory.decodeResource(context.getResources(),R.drawable.victim);
        pictures[39] =BitmapFactory.decodeResource(context.getResources(),R.drawable.tackletigers);
        pictures[40] =BitmapFactory.decodeResource(context.getResources(),R.drawable.munich_monks);
        pictures[41] =BitmapFactory.decodeResource(context.getResources(),R.drawable.gossenhauer);


        return pictures;
    }

    private Bitmap[] resize_pictures(Bitmap[] pictures, View x) {
        x.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        int width = x.getMeasuredWidth();
        int height = x.getMeasuredHeight();
        for (int i = 0; i < pictures.length; i++) {
            pictures[i] =Bitmap.createScaledBitmap(pictures[i],width,height,true);
        }
        return pictures;
    }

    public BitmapDrawable[] turn_pictures_drawable(Bitmap[] pic, Context context) {
        BitmapDrawable[] pictures = new BitmapDrawable[pic.length];
        for (int i = 0; i < pic.length; i++) {
            pictures[i] = new BitmapDrawable(context.getResources(),pic[i]);
        }
        return pictures;
    }
}


