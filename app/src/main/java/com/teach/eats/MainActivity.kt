package com.teach.eats

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.teach.eats.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dialog: AlertDialog
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

    override fun onStart() {
        super.onStart()
        dialog = buildDialog(this)
        if(!isConnected(this)){
            dialog.show()
        }
    }
    //Checks if there is an internet connection
    private fun isConnected(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw =
                cm.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
        return result
    }
    //Builds alert dialog to inform user that there is no internet connection
    private fun buildDialog(c: Context): AlertDialog {
        val dialog: AlertDialog = AlertDialog.Builder(c).create()
        dialog.setTitle("No Internet Connection")
        dialog.setMessage("App requires Mobile Data or Wifi. Press ok to Exit")
        dialog.setCancelable(false)
        dialog.setButton(Dialog.BUTTON_POSITIVE,"Ok") { dialog, which ->
            dialog.dismiss()
            finish()
        }
        return dialog
    }
    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }
}
