package com.teach.eats.functions

//Class that has each fruit's attributes
data class Fruit (
    val name: String,
    val icon: Int,
    val textName: String,
    val colorName: String,
    val colorRes: Int,
    val changeTextColor: Boolean,
    val fruitAudio: Int,
    val colorAudio: Int
)