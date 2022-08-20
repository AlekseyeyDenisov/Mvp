package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dw.mvp.core.nav.ImagePickerScreen
import ru.dw.mvp.view.MainView


class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(ImagePickerScreen)
    }

    fun onBackPressed():Boolean {
        router.exit()
        return true
    }

}
