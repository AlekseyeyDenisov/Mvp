package ru.dw.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.dw.mvp.core.utils.disposeBy
import ru.dw.mvp.core.utils.subscribeByDefault
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.view.fragment.user.DetailsView

class DetailsPresenter(
    private val repository: GitHupRepository,
    private val router: Router
) : MvpPresenter<DetailsView>() {

    private val bag = CompositeDisposable()


    fun loadUser(login: String) {
        viewState.showLoading()
        repository.getUserByLogin(login)
            .subscribeByDefault()
            .subscribe(
                {
                    viewState.hideLoading()
                    viewState.show(it)
                }, {
                    Log.d("@@@", "USER_LIST: ${it.message} !!!!")
                }
            ).disposeBy(bag)

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