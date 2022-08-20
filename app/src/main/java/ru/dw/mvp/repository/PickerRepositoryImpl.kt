package ru.dw.mvp.repository

import android.content.Context
import ru.dw.mvp.MyApp
import ru.dw.mvp.model.ImagePickerRepository
import java.io.File

class PickerRepositoryImpl: ImagePickerRepository {


    override fun saveImages(sourceFileName: String) {
        val dirImages = MyApp.instance.getDir(DIR_IMAGES, Context.MODE_PRIVATE)
        val source = File(sourceFileName)
        if (source.exists()) {
            val dest = File(dirImages, "file.jpg")
            source.copyTo(target = dest, overwrite = true)
        }
    }



    companion object {
        const val DIR_IMAGES = "Images"
    }
}