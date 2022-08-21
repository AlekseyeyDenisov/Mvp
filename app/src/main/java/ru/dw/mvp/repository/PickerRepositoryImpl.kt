package ru.dw.mvp.repository

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import io.reactivex.rxjava3.core.Completable
import ru.dw.mvp.MyApp
import ru.dw.mvp.model.ImagePickerRepository
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class PickerRepositoryImpl : ImagePickerRepository {


    override fun saveCompletable(sourceFileName: String): Completable =
        Completable.create { emitter ->
            try {
                saveImages(sourceFileName)
                emitter.onComplete()
            } catch (e: Exception) {
                Log.d("@@@", "Save file error" + e.message, e)
                emitter.onError(e)
            }
        }

    private fun saveImages(sourceFileName: String) {
        Thread.sleep(1000)
        saveBitmapInStorage(sourceFileName)
    }

    private fun saveBitmapInStorage(sourceFileName: String) {
        val bitmap = convertBitmap(sourceFileName)
        val filename = "Save_" + "${System.currentTimeMillis()}.png"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MyApp.instance.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

    private fun convertBitmap(sourceFileName: String): Bitmap {
        val source = File(sourceFileName)
        val filePath = source.path
        val bitmap = BitmapFactory.decodeFile(filePath)
        return bitmap
    }

    companion object {
        const val DIR_IMAGES = "Images"
    }
}