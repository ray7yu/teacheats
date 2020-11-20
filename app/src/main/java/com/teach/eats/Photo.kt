package com.teach.eats

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.teach.eats.databinding.FragmentResultBinding
import java.io.File


class Photo {
    companion object {
        fun getLabel(binding: FragmentResultBinding, arguments: Bundle?) {
            var label = arguments?.getString("label").toString()
            if(label == ""){
                label = "None"
            }
            val myResult: TextView = binding.labelView
            myResult.text = label
        }
        //Finds image
        fun getImage(binding: FragmentResultBinding, arguments: Bundle?) {
            val result = arguments?.getString("photoPath").toString()
            if (result == "") {
                return
            }
            //Get dimensions
            val targetW: Int = binding.foodPicture.width
            val targetH: Int = binding.foodPicture.height
            //Uses bitmap factory to decode and scale image, improving loading speed
            val bmOptions = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
                BitmapFactory.decodeFile(result, this)

                val photoW: Int = outWidth
                val photoH: Int = outHeight

                // Determine how much to scale down the image
                val scaleFactor: Int = Math.max(1, Math.min(photoW / targetW, photoH / targetH))

                // Decode the image file into a Bitmap sized to fill the View
                inJustDecodeBounds = false
                inSampleSize = scaleFactor
//                inPurgeable = true
            }
            val rotatedBitmap = loadRotatedBitmap(result, bmOptions)
            binding.foodPicture.setImageBitmap(rotatedBitmap)
        }

        //Delete image when choosing new one
        fun deleteImage(arguments: Bundle?) {
            val result = arguments?.getString("photoPath").toString()
            if (result == "") {
                return
            }
            //Deletes image
            val imageFile = File(result)
            val deleted = imageFile.delete()
            if (!deleted) {
                Log.d("Error", "Not deleted")
            } else {
                Log.d("Success", "Image deleted")
            }
        }

        //Rotates bitmap based on angle
        fun rotateImage(source: Bitmap, angle: Float): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(angle)
            return Bitmap.createBitmap(
                source, 0, 0, source.width, source.height,
                matrix, true
            )
        }
        //Loads a rotated bitmap from an image path
        fun loadRotatedBitmap(path: String, options: BitmapFactory.Options?): Bitmap {
            val bitmap = BitmapFactory.decodeFile(path, options)
            //Based on case, rotate picture using EXIF orientation data
            val ei = ExifInterface(path)
            val orientation: Int = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )
            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90F)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(
                    bitmap,
                    180F
                )
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(
                    bitmap,
                    270F
                )
                ExifInterface.ORIENTATION_NORMAL -> bitmap
                else -> bitmap
            }
        }
    }
}