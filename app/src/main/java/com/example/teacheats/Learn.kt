package com.example.teacheats

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

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
        fun chooseWord(textView: TextView, label: String){
            when (label) {
                "Apple!" -> textView.text = "APPLE"
                "Banana!" -> textView.text = "BANANA"
                "Grape!" -> textView.text = "GRAPE"
                "Lemon!" -> textView.text = "LEMON"
                "Mango!" -> textView.text = "MANGO"
                "Orange!" -> textView.text = "ORANGE"
                "Peach!" -> textView.text = "PEACH"
                "Pineapple!" -> textView.text = "PINEAPPLE"
                "Strawberry!" -> textView.text = "STRAWBERRY"
                "Watermelon!" -> textView.text = "WATERMELON"
            }
        }
        fun setSound(context: Context, listenButton: Button, label: String){
            val mp : MediaPlayer = when (label) {
                "Apple!" -> MediaPlayer.create(context, R.raw.apple)
                "Banana!" -> MediaPlayer.create(context, R.raw.banana)
                "Grape!" -> MediaPlayer.create(context, R.raw.grape)
                "Lemon!" -> MediaPlayer.create(context, R.raw.lemon)
                "Mango!" -> MediaPlayer.create(context, R.raw.mango)
                "Orange!" -> MediaPlayer.create(context, R.raw.banana)
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
    }
}