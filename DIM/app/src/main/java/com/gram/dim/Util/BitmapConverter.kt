package com.gram.dim.Util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.lang.Exception
import java.net.URL

fun getBitmapFromUrl(url : String) : Bitmap? {
    try {
        val url = URL(url)
        val connection = url.openConnection()
        connection.doInput = true
        connection.connect()
        val input = connection.getInputStream()
        val bitmap = BitmapFactory.decodeStream(input)
        return bitmap
    } catch (e: Exception) {
        return null
    }
}

