package com.teach.eats

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.security.AccessController.getContext

class Learn {

    companion object {
        fun chooseIcon(imageView: ImageView, label: String) {
            when (label) {
                "Apple!" -> imageView.setImageResource(R.drawable.ic_apple)
                "Banana!" -> imageView.setImageResource(R.drawable.ic_banana)
                "Grape!" -> imageView.setImageResource(R.drawable.ic_grapes)
                "Lemon!" -> imageView.setImageResource(R.drawable.ic_lemon)
                "Mango!" -> imageView.setImageResource(R.drawable.ic_mango)
                "Orange!" -> imageView.setImageResource(R.drawable.ic_orange)
                "Peach!" -> imageView.setImageResource(R.drawable.ic_peach)
                "Pineapple!" -> imageView.setImageResource(R.drawable.ic_pineapple)
                "Strawberry!" -> imageView.setImageResource(R.drawable.ic_strawberry)
                "Watermelon!" -> imageView.setImageResource(R.drawable.ic_watermelon)
                //Post Midterm
                "Avocado!" -> imageView.setImageResource(R.drawable.ic_avocado)
                "Blueberry!" -> imageView.setImageResource(R.drawable.ic_blueberry)
                "Cherry!" -> imageView.setImageResource(R.drawable.ic_cherry)
                "Coconut!" -> imageView.setImageResource(R.drawable.ic_coconut)
                "Dragonfruit!" -> imageView.setImageResource(R.drawable.ic_dragonfruit)
                "Durian!" -> imageView.setImageResource(R.drawable.ic_durian)
                "Grapefruit!" -> imageView.setImageResource(R.drawable.ic_grapefruit)
                "Guava!" -> imageView.setImageResource(R.drawable.ic_guava)
                "Kiwi!" -> imageView.setImageResource(R.drawable.ic_kiwi)
                "Lime!" -> imageView.setImageResource(R.drawable.ic_lime)
                "Lychee!" -> imageView.setImageResource(R.drawable.ic_lychee)
                "Papaya!" -> imageView.setImageResource(R.drawable.ic_papaya)
                "Passionfruit!" -> imageView.setImageResource(R.drawable.ic_passionfruit)
                "Pear!" -> imageView.setImageResource(R.drawable.ic_pear)
                "Persimmon!" -> imageView.setImageResource(R.drawable.ic_persimmon)
                "Pomegranate!" -> imageView.setImageResource(R.drawable.ic_pomegranate)
                "Raspberry!" -> imageView.setImageResource(R.drawable.ic_raspberry)
            }
        }
        fun chooseOrigin(imageView: ImageView, label: String){
            when (label) {
                "Apple!" -> imageView.setImageResource(R.drawable.apple_tree)
                "Banana!" -> imageView.setImageResource(R.drawable.banana_tree)
                "Grape!" -> imageView.setImageResource(R.drawable.grape_origin)
                "Lemon!" -> imageView.setImageResource(R.drawable.lemon_tree)
                "Mango!" -> imageView.setImageResource(R.drawable.mango_tree)
                "Orange!" -> imageView.setImageResource(R.drawable.orange_tree)
                "Peach!" -> imageView.setImageResource(R.drawable.peach_tree)
                "Pineapple!" -> imageView.setImageResource(R.drawable.pineapple_origin)
                "Strawberry!" -> imageView.setImageResource(R.drawable.strawberry_origin)
                "Watermelon!" -> imageView.setImageResource(R.drawable.watermelon_origin)
            }
        }
        fun chooseText(context: Context, textView: TextView, label: String, option: Int){
            when (label) {
                "Apple!" -> {
                    textView.text = if(option == 0) "APPLE" else "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Banana!" -> {
                    textView.text = if(option == 0) "BANANA" else "YELLOW"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Grape!" -> {
                    textView.text = if(option == 0) "GRAPE" else "PURPLE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple))
                }
                "Lemon!" -> {
                    textView.text = if(option == 0) "LEMON" else "YELLOW"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Mango!" -> {
                    textView.text = if(option == 0) "MANGO" else "ORANGE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                }
                "Orange!" -> {
                    textView.text = "ORANGE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                }
                "Peach!" -> {
                    textView.text = "PEACH"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.peach))
                }
                "Pineapple!" -> {
                    textView.text = if(option == 0) "PINEAPPLE" else "YELLOW"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Strawberry!" -> {
                    textView.text = if(option == 0) "STRAWBERRY" else "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Watermelon!" -> {
                    textView.text = if(option == 0) "WATERMELON" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                //Post Midterm
                "Avocado!" -> {
                    textView.text = if(option == 0) "AVOCADO" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                "Blueberry!" -> {
                    textView.text = if(option == 0) "BLUEBERRY" else "BLUE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                }
                "Cherry!" -> {
                    textView.text = if(option == 0) "CHERRY" else "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Coconut!" -> {
                    textView.text = if(option == 0) "COCONUT" else "BROWN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.brown))
                }
                "Dragonfruit!" -> {
                    textView.text = if(option == 0) "DRAGONFRUIT" else "PINK"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.pink))
                }
                "Durian!" -> {
                    textView.text = if(option == 0) "DURIAN" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                "Grapefruit!" -> {
                    textView.text = if(option == 0) "GRAPEFRUIT" else "ORANGE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                }
                "Guava!" -> {
                    textView.text = if(option == 0) "GUAVA" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                "Kiwi!" -> {
                    textView.text = if(option == 0) "KIWI" else "BROWN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.brown))
                }
                "Lime!" -> {
                    textView.text = if(option == 0) "LIME" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                "Lychee!" -> {
                    textView.text = if(option == 0) "LYCHEE" else "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Papaya!" -> {
                    textView.text = if(option == 0) "PAPAYA" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                "Passionfruit!" -> {
                    textView.text = if(option == 0) "PASSIONFRUIT" else "PURPLE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple))
                }
                "Pear!" -> {
                    textView.text = if(option == 0) "PEAR" else "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
                "Persimmon!" -> {
                    textView.text = if(option == 0) "PERSIMMON" else "ORANGE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.orange))
                }
                "Pomegranate!" -> {
                    textView.text = if(option == 0) "POMEGRANATE" else "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Raspberry!" -> {
                    textView.text = if(option == 0) "RASPBERRY" else "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
            }
        }
        fun setSound(context: Context, listenButton: ImageButton, label: String, option: Int){
            val mp : MediaPlayer = when (label) {
                "Apple!" -> MediaPlayer.create(context, if(option == 0) R.raw.apple else R.raw.red)
                "Banana!" -> MediaPlayer.create(context, if(option == 0) R.raw.banana else R.raw.yellow)
                "Grape!" -> MediaPlayer.create(context, if(option == 0) R.raw.grape else R.raw.purple)
                "Lemon!" -> MediaPlayer.create(context, if(option == 0) R.raw.lemon else R.raw.yellow)
                "Mango!" -> MediaPlayer.create(context, if(option == 0) R.raw.mango else R.raw.orange)
                "Orange!" -> MediaPlayer.create(context, R.raw.orange)
                "Peach!" -> MediaPlayer.create(context, R.raw.peach)
                "Pineapple!" -> MediaPlayer.create(context, if(option == 0) R.raw.pineapple else R.raw.yellow)
                "Strawberry!" -> MediaPlayer.create(context, if(option == 0) R.raw.strawberry else R.raw.red)
                "Watermelon!" -> MediaPlayer.create(context, if(option == 0) R.raw.watermelon else R.raw.green)
                //Post Midterm
                "Avocado!" -> MediaPlayer.create(context, if(option == 0) R.raw.avocado else R.raw.green)
                "Blueberry!" -> MediaPlayer.create(context, if(option == 0) R.raw.blueberry else R.raw.blue)
                "Cherry!" -> MediaPlayer.create(context, if(option == 0) R.raw.cherry else R.raw.red)
                "Coconut!" -> MediaPlayer.create(context, if(option == 0) R.raw.coconut else R.raw.brown)
                "Dragonfruit!" -> MediaPlayer.create(context, if(option == 0) R.raw.dragonfruit else R.raw.pink)
                "Durian!" -> MediaPlayer.create(context, if(option == 0) R.raw.durian else R.raw.green)
                "Grapefruit!" -> MediaPlayer.create(context, if(option == 0) R.raw.grapefruit else R.raw.orange)
                "Guava!" -> MediaPlayer.create(context, if(option == 0) R.raw.guava else R.raw.green)
                "Kiwi!" -> MediaPlayer.create(context, if(option == 0) R.raw.kiwi else R.raw.brown)
                "Lime!" -> MediaPlayer.create(context, if(option == 0) R.raw.lime else R.raw.green)
                "Lychee!" -> MediaPlayer.create(context, if(option == 0) R.raw.lychee else R.raw.red)
                "Papaya!" -> MediaPlayer.create(context, if(option == 0) R.raw.papaya else R.raw.green)
                "Passionfruit!" -> MediaPlayer.create(context, if(option == 0) R.raw.passionfruit else R.raw.purple)
                "Pear!" -> MediaPlayer.create(context, if(option == 0) R.raw.pear else R.raw.green)
                "Persimmon!" -> MediaPlayer.create(context, if(option == 0) R.raw.persimmon else R.raw.orange)
                "Pomegranate!" -> MediaPlayer.create(context, if(option == 0) R.raw.pomegranate else R.raw.red)
                "Raspberry!" -> MediaPlayer.create(context, if(option == 0) R.raw.raspberry else R.raw.red)
                else -> MediaPlayer.create(context, R.raw.apple)
            }
            listenButton.setOnClickListener{
                mp.start()
            }
        }
    }
}