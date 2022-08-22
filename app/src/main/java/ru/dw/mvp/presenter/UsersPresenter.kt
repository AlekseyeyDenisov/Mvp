package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.UserDetailsScreen
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.view.fragment.users.UsersView
import java.util.concurrent.TimeUnit

class UsersPresenter(
    private val repository: GitHupRepository,
    private val router: Router

) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        repository.getUser()
            .delay(2, TimeUnit.SECONDS)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.initList(it)
                    viewState.hideLoading()
                },
                { }
            )
        viewState.showLoading()
    }

    private fun <T>Single<T>.subscribeByDefault():Single<T>{
        return this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun showDetails(githubUser: GithubUser?) {
        val login = githubUser ?: GithubUser("empty")
        router.navigateTo(UserDetailsScreen(login))
    }

}