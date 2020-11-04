package com.up.helloup.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.up.helloup.R

fun AppCompatActivity.toast(s: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, s, length).show()
}

fun AppCompatActivity.alert(msg: String, callback: () -> Unit = {}) {
    val dialog = AlertDialog.Builder(this).create()
    dialog.setTitle(R.string.alert_title)
    dialog.setMessage(msg)
    dialog.setButton(
        AlertDialog.BUTTON_NEUTRAL, "OK"
    ) { _, which ->
        dialog.dismiss()
        callback()
    }
    dialog.show()
}