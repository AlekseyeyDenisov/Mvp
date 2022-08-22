package ru.dw.mvp.model

import io.reactivex.rxjava3.core.Completable



interface ImagePickerRepository {

    fun saveCompletable(sourceFileName: String): Completable


}