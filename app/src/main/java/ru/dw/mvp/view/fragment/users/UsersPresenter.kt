package ru.dw.mvp.view.fragment.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.UserDetailsScreen
import ru.dw.mvp.core.utils.subscribeByDefault
import ru.dw.mvp.repository.GithubRepository
import ru.dw.mvp.model.entity.GithubUser
import javax.inject.Inject


class UsersPresenter @Inject constructor (
    private val repository: GithubRepository,
    private val router: Router

) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        repository.getUsers()
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.hideLoading()
                },
                {
                    Log.d("@@@", "USER_LIST: ${it.message} !!!!")
                }
            )
        viewState.showLoading()
    }


    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun showDetails(githubUser: GithubUser) {
        router.navigateTo(UserDetailsScreen(githubUser))
    }

}