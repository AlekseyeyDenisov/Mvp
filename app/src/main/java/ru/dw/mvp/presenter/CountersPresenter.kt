package ru.dw.mvp.presenter

import ru.dw.mvp.view.ButtonCounter
import ru.dw.mvp.model.CountersModel
import ru.dw.mvp.view.MainView

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(enum: ButtonCounter) {
        val newValue = model.next(enum.position)
        view.setText(newValue.toString(), enum.position)
    }

}
