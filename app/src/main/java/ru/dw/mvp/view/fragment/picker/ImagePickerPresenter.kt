package ru.dw.mvp.view.fragment.picker

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.dw.mvp.repository.ImagePickerRepository
import ru.dw.mvp.view.fragment.picker.ImagePickerView

class ImagePickerPresenter(
    private val repository: ImagePickerRepository
) : MvpPresenter<ImagePickerView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.hideLoading()
    }

    fun getUrlImages(imageUri: String) {
        repository.saveCompletable(imageUri)
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