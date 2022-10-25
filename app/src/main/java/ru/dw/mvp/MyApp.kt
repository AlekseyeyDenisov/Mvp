package ru.dw.mvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import ru.dw.mvp.core.utils.ConnectivityListener
import ru.dw.mvp.data.database.GithubAppDb
import ru.dw.mvp.data.network.NetworkProvider
import ru.dw.mvp.di.DaggerApplicationComponent
import ru.dw.mvp.repository.impl.GithubRepositoryImpl
import ru.dw.mvp.view.fragment.users.UsersPresenter

class MyApp:Application() {



    companion object {
        lateinit var instance :MyApp
    }

    private val cicerone: Cicerone<Router> by lazy {Cicerone.create()}

    //val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    val component by lazy {
        DaggerApplicationComponent.factory().create(
            this,
            cicerone.router,
            cicerone.getNavigatorHolder()
        )
    }

//    val githubRepositoryImpl: GithubRepositoryImpl by lazy {
//        GithubRepositoryImpl(
//            NetworkProvider.usersApi,
//            database.dataBaseDao(),
//            connectivityListener.isOnline(this)
//        )
//    }

//    val database: GithubAppDb by lazy { GithubAppDb.getInstance(this) }
//    private  val connectivityListener: ConnectivityListener by lazy {
//        ConnectivityListener(
//            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        )
//    }
//
//
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

//    fun getConnectObservable() = connectivityListener.status()
//    fun getConnectSingle() = connectivityListener.statusSingle()



}