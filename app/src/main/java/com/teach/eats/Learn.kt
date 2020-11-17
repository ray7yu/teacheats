package com.teach.eats

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.facebook.shimmer.ShimmerFrameLayout

class Learn {
    companion object {
        val fruitMap = mapOf(
            "Apple!" to Fruit("apple", R.drawable.ic_apple, "APPLE", "RED", R.color.red,false, R.raw.apple, R.raw.red),
            "Banana!" to Fruit("banana", R.drawable.ic_banana, "BANANA", "YELLOW", R.color.yellow, true, R.raw.banana, R.raw.yellow),
            "Grape!" to Fruit("grape", R.drawable.ic_grape, "GRAPE", "PURPLE", R.color.purple, false, R.raw.grape, R.raw.purple),
            "Lemon!" to Fruit("lemon", R.drawable.ic_lemon, "LEMON", "YELLOW", R.color.yellow, true, R.raw.lemon, R.raw.yellow),
            "Mango!" to Fruit("mango", R.drawable.ic_mango, "MANGO", "ORANGE", R.color.orange, false, R.raw.mango, R.raw.orange),
            "Orange!" to Fruit("orange", R.drawable.ic_orange, "ORANGE", "ORANGE", R.color.orange, false, R.raw.orange, R.raw.orange),
            "Peach!" to Fruit("peach", R.drawable.ic_peach, "PEACH", "PEACH", R.color.peach, false, R.raw.peach, R.raw.peach),
            "Pineapple!" to Fruit("pineapple", R.drawable.ic_pineapple, "PINEAPPLE", "YELLOW", R.color.yellow, true, R.raw.pineapple, R.raw.yellow),
            "Strawberry!" to Fruit("strawberry", R.drawable.ic_strawberry, "STRAWBERRY", "RED", R.color.red, false, R.raw.strawberry, R.raw.red),
            "Watermelon!" to Fruit("watermelon", R.drawable.ic_watermelon, "WATERMELON", "GREEN", R.color.green, false, R.raw.watermelon, R.raw.green),
            //Post Midterm
            "Avocado!" to Fruit("avocado", R.drawable.ic_avocado, "AVOCADO", "GREEN", R.color.green, false, R.raw.avocado, R.raw.green),
            "Blueberry!" to Fruit("blueberry", R.drawable.ic_blueberry, "BLUEBERRY", "BLUE", R.color.blue, false, R.raw.blueberry, R.raw.blue),
            "Cherry!" to Fruit("cherry", R.drawable.ic_cherry, "CHERRY", "RED", R.color.red, false, R.raw.cherry, R.raw.red),
            "Coconut!" to Fruit("coconut", R.drawable.ic_coconut, "COCONUT", "BROWN", R.color.brown, false, R.raw.coconut, R.raw.brown),
            "Dragonfruit!" to Fruit("dragonfruit", R.drawable.ic_dragonfruit, "DRAGONFRUIT", "PINK", R.color.pink, false, R.raw.dragonfruit, R.raw.pink),
            "Durian!" to Fruit("durian", R.drawable.ic_durian, "DURIAN", "GREEN", R.color.green, false, R.raw.durian, R.raw.green),
            "Grapefruit!" to Fruit("grapefruit", R.drawable.ic_grapefruit, "GRAPEFRUIT", "ORANGE", R.color.orange, false, R.raw.grapefruit, R.raw.orange),
            "Guava!" to Fruit("guava", R.drawable.ic_guava, "GUAVA", "GREEN", R.color.green, false, R.raw.guava, R.raw.green),
            "Kiwi!" to Fruit("kiwi", R.drawable.ic_kiwi, "KIWI", "BROWN", R.color.brown, false, R.raw.kiwi, R.raw.brown),
            "Lime!" to Fruit("lime", R.drawable.ic_lime, "LIME", "GREEN", R.color.green, false, R.raw.lime, R.raw.green),
            "Lychee!" to Fruit("lychee", R.drawable.ic_lychee, "LYCHEE", "RED", R.color.red, false, R.raw.lychee, R.raw.red),
            "Papaya!" to Fruit("papaya", R.drawable.ic_papaya, "PAPAYA", "GREEN", R.color.green, false, R.raw.papaya, R.raw.green),
            "Passionfruit!" to Fruit("passionfruit", R.drawable.ic_passionfruit, "PASSIONFRUIT", "PURPLE", R.color.purple, false, R.raw.passionfruit, R.raw.purple),
            "Pear!" to Fruit("pear", R.drawable.ic_pear, "PEAR", "GREEN", R.color.green, false, R.raw.pear, R.raw.green),
            "Persimmon!" to Fruit("persimmon", R.drawable.ic_persimmon, "PERSIMMON", "ORANGE", R.color.orange, false, R.raw.persimmon, R.raw.orange),
            "Pomegranate!" to Fruit("pomegranate", R.drawable.ic_pomegranate, "POMEGRANATE", "RED", R.color.red, false, R.raw.pomegranate, R.raw.red),
            "Raspberry!" to Fruit("raspberry", R.drawable.ic_raspberry, "RASPBERRY", "RED", R.color.red, false, R.raw.raspberry, R.raw.red)
        )
        //Sets an icon as an image resource.
        fun chooseIcon(imageView: ImageView, label: String) {
            fruitMap[label]?.icon?.let { imageView.setImageResource(it) }
        }
        //Builds an image URL, then makes an asynchronous call to load image into view.
        fun chooseOrigin(shimmerView:ShimmerFrameLayout, imageView: ImageView, label: String) {
            var fruitURL: String = "https://teach-eats-fruit-pics.s3-us-west-1.amazonaws.com/"
            fruitURL += (this.fruitMap[label] ?: error("")).name + "_origin.png"
            Origin(imageView, shimmerView).execute(fruitURL)
        }
        //Chooses fruit name or fruit color as text, depending on the option passed in.
        fun chooseText(context: Context, textView: TextView, label: String, option: Int) {
            //Fruit name
            textView.text = if(option == 0) fruitMap[label]?.textName else fruitMap[label]?.colorName
            //Fruit color
            fruitMap[label]?.colorRes?.let {
                ContextCompat.getColor(context,
                    it
                )
            }?.let { textView.setBackgroundColor(it) }
            //If clashing colors, text is turned black.
            if(fruitMap[label]?.changeTextColor!!){
                textView.setTextColor(Color.BLACK)
            }
        }
        //Sets sound to fruit or color audio, depending on the option passed in.
        fun setSound(context: Context, listenButton: ImageButton, label: String, option: Int) {
            val mp: MediaPlayer = MediaPlayer.create(
                context,
                if (option == 0) (fruitMap[label] ?: error("")).fruitAudio else (fruitMap[label] ?: error("")).colorAudio
            )
            listenButton.setOnClickListener {
                mp.start()
            }
        }
    }
}