package ru.dw.mvp.view.fragment.user

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.dw.mvp.model.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView: MvpView{
    fun show(githubUser: GithubUser)
    fun showLoading()
    fun hideLoading()
}