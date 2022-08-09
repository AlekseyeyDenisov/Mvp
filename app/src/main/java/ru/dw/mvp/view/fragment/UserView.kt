package ru.dw.mvp.view.fragment

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.dw.mvp.model.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun initList(list:List<GithubUser>)
}