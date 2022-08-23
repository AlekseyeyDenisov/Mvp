package ru.dw.mvp.view.fragment.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.dw.mvp.model.entity.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView: MvpView {
    fun initList(list:List<GithubUser>)
    fun showLoading()
    fun hideLoading()
}