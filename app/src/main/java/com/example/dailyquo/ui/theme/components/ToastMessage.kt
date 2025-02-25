package com.example.dailyquo.ui.theme.components

import android.content.Context
import android.widget.Toast

fun showToastMessage(context: Context, toastMessage: String) {
    Toast.makeText(
        context,
        toastMessage,
        Toast.LENGTH_SHORT
    ).show()
}