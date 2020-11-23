package com.teach.eats

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.teach.eats.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")

        //Delete cached images
        Log.i("Create", "Destroy image called")
        val storageDir: File? = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val files = storageDir?.listFiles()
        if(files != null){
            for(i in files.indices){
                Log.i("Filepath", files[i].absolutePath)
                Log.i("Delete file status", files[i].delete().toString())
            }
        }

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    override fun onDestroy() {
        super.onDestroy()

    }
}
