package ru.dw.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.dw.mvp.model.CountersModel
import ru.dw.mvp.view.ButtonCounter
import ru.dw.mvp.view.MainView


class CountersPresenter(
    private val model: CountersModel
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    fun onCounterClick(enum: ButtonCounter) {
        val newValue = model.next(enum.position)
        when(enum){
            ButtonCounter.BtnOne->{viewState.serCounterOneText(newValue.toString(), enum.position)}
            ButtonCounter.BtnTwo->{viewState.serCounterTwoText(newValue.toString(), enum.position)}
            ButtonCounter.BtnThree->{viewState.serCounterThreeText(newValue.toString(), enum.position)}

        }

    }

}
