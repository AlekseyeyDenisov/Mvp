package ru.dw.mvp.view.fragment.userDetails

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.UserDetailsForkScreen
import ru.dw.mvp.core.utils.disposeBy
import ru.dw.mvp.core.utils.subscribeByDefault
import ru.dw.mvp.repository.GithubRepository
import ru.dw.mvp.model.entity.GithubRepo
import ru.dw.mvp.view.fragment.userDetails.DetailsView

class DetailsPresenter(
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<DetailsView>() {

    private val bag = CompositeDisposable()


    fun loadForks(login: String) {
        viewState.showLoading()
        repository.getReposByLogin(login)
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
    fun showDetailsFork(githubRepo: GithubRepo) {
        router.navigateTo(UserDetailsForkScreen(githubRepo))
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