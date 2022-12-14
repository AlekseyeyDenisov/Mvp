package ru.dw.mvp.view.fragment.picker

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImagePickerView : MvpView {
    fun showLoading()
    fun hideLoading()

}