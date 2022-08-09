package ru.dw.mvp.presenter

import moxy.MvpPresenter
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.view.MainView


class GitHubPresenter(
    private val repository: GitHupRepository
):MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUser())
    }

}
