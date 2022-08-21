package ru.dw.mvp.repository

import android.content.Context
import android.util.Log
import io.reactivex.rxjava3.core.Completable
import ru.dw.mvp.MyApp
import ru.dw.mvp.model.ImagePickerRepository
import java.io.File

class PickerRepositoryImpl : ImagePickerRepository {


    override fun saveCompletable(sourceFileName: String, callback: (File)->Unit): Completable = Completable.create { emitter ->
        try {
            saveImages(sourceFileName,callback)
            emitter.onComplete()
        } catch (e: Exception) {
            Log.d("@@@", "Save file error" + e.message, e)
            emitter.onError(e)
        }

    }

    private fun saveImages(sourceFileName: String, callback: (File)->Unit) {
        Thread.sleep(1000)
        val dirImages = MyApp.instance.getDir(DIR_IMAGES, Context.MODE_PRIVATE)
        val source = File(sourceFileName)
        if (source.exists()) {
            val dest = File(dirImages, "file.jpg")
            callback( source.copyTo(target = dest, overwrite = true))

        }
    }

    companion object {
        const val DIR_IMAGES = "Images"
    }
}