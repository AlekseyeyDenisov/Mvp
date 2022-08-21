package ru.dw.mvp.presenter

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.dw.mvp.model.ImagePickerRepository
import ru.dw.mvp.view.fragment.picker.ImagePickerView
import java.io.File

class ImagePickerPresenter(
    private val repository: ImagePickerRepository
) : MvpPresenter<ImagePickerView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.hideLoading()
    }

    fun getUrlImages(imageUri: String, callback: (File) -> Unit) {
        repository.saveCompletable(imageUri, callback)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.hideLoading()
                Log.d("@@@", "getUrlImages: onComplete")

            }, {
                Log.d("@@@", "onError: ${it.message}")

            })

    }
}