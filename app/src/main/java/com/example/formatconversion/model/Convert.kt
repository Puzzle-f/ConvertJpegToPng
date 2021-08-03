package com.example.formatconversion.model

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.formatconversion.R
import java.io.*

fun readFileToStreemNoClass(resources: Resources): Bitmap {
    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.jo_my_jpg)
//    Log.d("", "Получаем из ресурсов bitmap $bitmap")
    return bitmap
}

fun readFileToStream(fileName: String): InputStream {
    return BufferedInputStream(FileInputStream(fileName))
}

fun streamToBitmap(inputStream: InputStream): Bitmap? {
    return try {
        BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun Bitmap.compress(
    format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100
): ByteArrayOutputStream {

    val stream = ByteArrayOutputStream()
    this.compress(
        format,
        quality,
        stream
    )
    return stream
}

fun Bitmap.toPng(): ByteArrayOutputStream {
    Log.d("", "Симуляция обработки")
//    Thread.sleep(3000)
    return this.compress()
}

fun saveToFile(stream: ByteArrayOutputStream, path: String) {
    File(path).writeBytes(stream.toByteArray())
    Log.d("", "Создаём файл $path")
}



