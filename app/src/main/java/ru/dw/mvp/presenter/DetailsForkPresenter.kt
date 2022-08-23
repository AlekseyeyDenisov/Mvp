package ru.dw.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.dw.mvp.view.fragment.userDetails.DetailsView

class DetailsForkPresenter(
    private val router: Router
) : MvpPresenter<DetailsView>() {

    private val bag = CompositeDisposable()


    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

}