package ru.dw.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.UserDetailsForkScreen
import ru.dw.mvp.core.utils.disposeBy
import ru.dw.mvp.core.utils.subscribeByDefault
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.view.fragment.userDetails.DetailsView

class DetailsPresenter(
    private val repository: GitHupRepository,
    private val router: Router
) : MvpPresenter<DetailsView>() {

    private val bag = CompositeDisposable()


    fun loadForks(login: String) {
        viewState.showLoading()
        repository.getForkByLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.hideLoading()
                    viewState.show(it)
                    Log.d("@@@", "loadForks: $it")
                }, {
                    Log.d("@@@", "USER_LIST: ${it.message} !!!!")
                }
            ).disposeBy(bag)

    }
    fun showDetailsFork(githubReposUser: GithubReposUser) {
        router.navigateTo(UserDetailsForkScreen(githubReposUser))
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

}