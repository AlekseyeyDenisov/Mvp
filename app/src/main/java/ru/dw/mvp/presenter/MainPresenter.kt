package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dw.mvp.core.vav.UsersScreen
import ru.dw.mvp.view.MainView


class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(UsersScreen)
    }

    fun onBackPressed():Boolean {
        router.exit()
        return true
    }

}
