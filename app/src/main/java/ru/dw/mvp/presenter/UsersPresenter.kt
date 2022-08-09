package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.UserDetailsScreen
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.view.fragment.users.UsersView

class UsersPresenter  (
    private val repository: GitHupRepository,
    private val router: Router

): MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getUser())

    }

    fun onBackPressed():Boolean{
        router.exit()
        return true
    }

    fun showDetails(githubUser: GithubUser?){
        val login = githubUser ?: GithubUser("empty")
        router.navigateTo(UserDetailsScreen(login))
    }

}