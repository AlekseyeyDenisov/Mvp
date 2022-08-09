package ru.dw.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView:MvpView {

    fun serCounterOneText(counter: String, value: Int)

    fun serCounterTwoText(counter: String, value: Int)

    fun serCounterThreeText(counter: String, value: Int)
}