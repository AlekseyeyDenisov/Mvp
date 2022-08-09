package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.view.fragment.UserView

class UserPresenter  (
    private val repository: GitHupRepository,
    private val router: Router
): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUser())
    }

    fun onBackPressed():Boolean{
        router.exit()
        return true
    }

}