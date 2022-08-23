package ru.dw.mvp.view.fragment.userDetails

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.dw.mvp.model.entity.GithubReposUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView: MvpView{
    fun show(githubReposUser:List<GithubReposUser>)
    fun showLoading()
    fun hideLoading()
}