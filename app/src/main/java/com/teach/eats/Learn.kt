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
        fun chooseWord(context: Context, textView: TextView, label: String){
            when (label) {
                "Apple!" -> {
                    textView.text = "APPLE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Banana!" -> {
                    textView.text = "BANANA"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Grape!" -> {
                    textView.text = "GRAPE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple))
                }
                "Lemon!" -> {
                    textView.text = "LEMON"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Mango!" -> {
                    textView.text = "MANGO"
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
                    textView.text = "PINEAPPLE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Strawberry!" -> {
                    textView.text = "STRAWBERRY"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Watermelon!" -> {
                    textView.text = "WATERMELON"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
            }
        }
        fun setFoodSound(context: Context, listenButton: ImageButton, label: String){
            val mp : MediaPlayer = when (label) {
                "Apple!" -> MediaPlayer.create(context, R.raw.apple)
                "Banana!" -> MediaPlayer.create(context, R.raw.banana)
                "Grape!" -> MediaPlayer.create(context, R.raw.grape)
                "Lemon!" -> MediaPlayer.create(context, R.raw.lemon)
                "Mango!" -> MediaPlayer.create(context, R.raw.mango)
                "Orange!" -> MediaPlayer.create(context, R.raw.orange)
                "Peach!" -> MediaPlayer.create(context, R.raw.peach)
                "Pineapple!" -> MediaPlayer.create(context, R.raw.pineapple)
                "Strawberry!" -> MediaPlayer.create(context, R.raw.strawberry)
                "Watermelon!" -> MediaPlayer.create(context, R.raw.watermelon)
                else -> MediaPlayer.create(context, R.raw.apple)
            }
            listenButton.setOnClickListener{
                mp.start()
            }
        }
        fun chooseColor(context: Context, textView: TextView, label: String){
            when (label) {
                "Apple!" -> {
                    textView.text = "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Banana!" -> {
                    textView.text = "YELLOW"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Grape!" -> {
                    textView.text = "PURPLE"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple))
                }
                "Lemon!" -> {
                    textView.text = "YELLOW"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Mango!" -> {
                    textView.text = "ORANGE"
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
                    textView.text = "YELLOW"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                    textView.setTextColor(Color.GRAY)
                }
                "Strawberry!" -> {
                    textView.text = "RED"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                }
                "Watermelon!" -> {
                    textView.text = "GREEN"
                    textView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                }
            }
        }
        fun setColorSound(context: Context, listenButton: ImageButton, label: String){
            val mp : MediaPlayer = when (label) {
                "Apple!" -> MediaPlayer.create(context, R.raw.red)
                "Banana!" -> MediaPlayer.create(context, R.raw.yellow)
                "Grape!" -> MediaPlayer.create(context, R.raw.purple)
                "Lemon!" -> MediaPlayer.create(context, R.raw.yellow)
                "Mango!" -> MediaPlayer.create(context, R.raw.orange)
                "Orange!" -> MediaPlayer.create(context, R.raw.orange)
                "Peach!" -> MediaPlayer.create(context, R.raw.peach)
                "Pineapple!" -> MediaPlayer.create(context, R.raw.yellow)
                "Strawberry!" -> MediaPlayer.create(context, R.raw.red)
                "Watermelon!" -> MediaPlayer.create(context, R.raw.green)
                else -> MediaPlayer.create(context, R.raw.red)
            }
            listenButton.setOnClickListener{
                mp.start()
            }
        }
    }
}