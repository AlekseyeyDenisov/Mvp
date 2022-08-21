package ru.dw.mvp.model

import io.reactivex.rxjava3.core.Completable
import java.io.File


interface ImagePickerRepository {

    fun saveCompletable(sourceFileName: String, callback: (File)->Unit): Completable


}