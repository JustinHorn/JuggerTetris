package com.jugger.tetris.BorderImageview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.View
import com.jugger.tetris.R

/** Singelton that loads and stores images for the bicks) */
class JuggerTeamImages private constructor(measure: View , context: Context) {
    private lateinit var pictures: Array<BitmapDrawable>
    fun getPicture(i: Int): BitmapDrawable? {
        return pictures[i]
    }

    fun setUp_pictures(view: View, context: Context) {
        var pic = load_pictures(context)
        pic = resizePictures(pic, view)
        pictures = pic.map { it -> BitmapDrawable(context.resources,it) }.toTypedArray()
    }

    private fun load_pictures(context: Context): Array<Bitmap> {
        val pictures = arrayOfNulls<Bitmap>(42)
        pictures[0] = BitmapFactory.decodeResource(context.resources, R.drawable.chimaere)
        pictures[1] = BitmapFactory.decodeResource(context.resources, R.drawable.zonenkinder)
        pictures[2] = BitmapFactory.decodeResource(context.resources, R.drawable.rigor)
        pictures[3] = BitmapFactory.decodeResource(context.resources, R.drawable.peterpawns)
        pictures[4] = BitmapFactory.decodeResource(context.resources, R.drawable.bamberg)
        pictures[5] = BitmapFactory.decodeResource(context.resources, R.drawable.banane)
        pictures[6] = BitmapFactory.decodeResource(context.resources, R.drawable.blutgraetsche)
        pictures[7] = BitmapFactory.decodeResource(context.resources, R.drawable.bobjugger)
        pictures[8] = BitmapFactory.decodeResource(context.resources, R.drawable.bonn)
        pictures[9] = BitmapFactory.decodeResource(context.resources, R.drawable.falco)
        pictures[10] = BitmapFactory.decodeResource(context.resources, R.drawable.fkk)
        pictures[11] = BitmapFactory.decodeResource(context.resources, R.drawable.flying_juggmen)
        pictures[12] = BitmapFactory.decodeResource(context.resources, R.drawable.gag)
        pictures[13] = BitmapFactory.decodeResource(context.resources, R.drawable.greek)
        pictures[14] = BitmapFactory.decodeResource(context.resources, R.drawable.hagenwuppertal)
        pictures[15] = BitmapFactory.decodeResource(context.resources, R.drawable.hlu)
        pictures[16] = BitmapFactory.decodeResource(context.resources, R.drawable.hobbiz)
        pictures[17] = BitmapFactory.decodeResource(context.resources, R.drawable.indianer)
        pictures[18] = BitmapFactory.decodeResource(context.resources, R.drawable.jugg_sth)
        pictures[19] = BitmapFactory.decodeResource(context.resources, R.drawable.juggernauts)
        pictures[20] = BitmapFactory.decodeResource(context.resources, R.drawable.jugglersjugg)
        pictures[21] = BitmapFactory.decodeResource(context.resources, R.drawable.keiler)
        pictures[22] = BitmapFactory.decodeResource(context.resources, R.drawable.keuleneulen)
        pictures[23] = BitmapFactory.decodeResource(context.resources, R.drawable.krake)
        pictures[24] = BitmapFactory.decodeResource(context.resources, R.drawable.kuhdorf)
        pictures[25] = BitmapFactory.decodeResource(context.resources, R.drawable.leere_menge)
        pictures[26] = BitmapFactory.decodeResource(context.resources, R.drawable.leipziger_nachtwache)
        pictures[27] = BitmapFactory.decodeResource(context.resources, R.drawable.mainz)
        pictures[28] = BitmapFactory.decodeResource(context.resources, R.drawable.nsa)
        pictures[29] = BitmapFactory.decodeResource(context.resources, R.drawable.over9000)
        pictures[30] = BitmapFactory.decodeResource(context.resources, R.drawable.paderbears)
        pictures[31] = BitmapFactory.decodeResource(context.resources, R.drawable.pferd)
        pictures[32] = BitmapFactory.decodeResource(context.resources, R.drawable.pink_pain)
        pictures[33] = BitmapFactory.decodeResource(context.resources, R.drawable.s)
        pictures[34] = BitmapFactory.decodeResource(context.resources, R.drawable.schild)
        pictures[35] = BitmapFactory.decodeResource(context.resources, R.drawable.schild_blau)
        pictures[36] = BitmapFactory.decodeResource(context.resources, R.drawable.schloss)
        pictures[37] = BitmapFactory.decodeResource(context.resources, R.drawable.skull)
        pictures[38] = BitmapFactory.decodeResource(context.resources, R.drawable.victim)
        pictures[39] = BitmapFactory.decodeResource(context.resources, R.drawable.tackletigers)
        pictures[40] = BitmapFactory.decodeResource(context.resources, R.drawable.munich_monks)
        pictures[41] = BitmapFactory.decodeResource(context.resources, R.drawable.gossenhauer)

        val picture:Array<Bitmap> = pictures.filterNotNull().toTypedArray()
        return picture
    }

    private fun resizePictures(pictures: Array<Bitmap>, x: View): Array<Bitmap> {
        x.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        return pictures.map{ Bitmap.createScaledBitmap(it, x.measuredWidth, x.measuredHeight, true) }.toTypedArray()
    }

    companion object {
        private lateinit var INSTANCE: JuggerTeamImages
        fun get(): JuggerTeamImages? {
            if (!this::INSTANCE.isInitialized) {
                throw RuntimeException("wanted to get instance of " + JuggerTeamImages::class.java.name + " without being initalized")
            }
            return INSTANCE
        }
        fun init(v: View, c: Context): JuggerTeamImages? {
            if (!this::INSTANCE.isInitialized) {
                INSTANCE = JuggerTeamImages(v, c)
            }
            return INSTANCE
        }
    }

    init {
        setUp_pictures(measure, context)
    }
}