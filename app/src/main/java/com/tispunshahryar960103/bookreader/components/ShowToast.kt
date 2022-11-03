package com.tispunshahryar960103.bookreader.components

import android.content.Context
import android.widget.Toast


fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG)
        .show()
}