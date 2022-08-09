package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.UserDetailsScreen
import ru.dw.mvp.core.nav.UsersScreen
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.view.fragment.user.UserDetailsView

class UserDetailsPresenter(
    private val router: Router
) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

}