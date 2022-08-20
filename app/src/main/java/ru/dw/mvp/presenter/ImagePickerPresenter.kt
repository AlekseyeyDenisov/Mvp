package ru.dw.mvp.presenter

import moxy.MvpPresenter
import ru.dw.mvp.model.ImagePickerRepository
import ru.dw.mvp.view.fragment.picker.ImagePickerView

class ImagePickerPresenter(
    private val repository: ImagePickerRepository
) : MvpPresenter<ImagePickerView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.hideLoading()
    }

    fun getUrlImages(imageUri: String) {
        repository.saveImages(imageUri)
    }
}