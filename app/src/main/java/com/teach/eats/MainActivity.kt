package com.teach.eats

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.teach.eats.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var alert: AlertDialog
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
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        alert = buildDialog(this)
        if(!isConnected(this)){
            Log.i("Internet", "No Internet")
            alert.show()
        }
    }
    //Checks if there is an internet connection
    private fun isConnected(context: Context): Boolean {
        val result: Boolean
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting
        }
        return result
    }
    //Builds alert dialog to inform user that there is no internet connection
    private fun buildDialog(c: Context): AlertDialog {
        val dialog: AlertDialog = AlertDialog.Builder(c).create()
        dialog.setTitle("No Internet Connection")
        dialog.setMessage("App requires Mobile Data or Wifi. Press OK to Exit")
        dialog.setCancelable(false)
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK") { _, _ ->
            finish()
        }
        return dialog
    }
    override fun onPause() {
        super.onPause()
        Log.i("dialog", "was dismissed")
        alert.dismiss()
    }
}
