package ru.dw.mvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import ru.dw.mvp.core.utils.ConnectivityListener
import ru.dw.mvp.data.database.GithubAppDb

class MyApp:Application() {
    companion object {
        lateinit var instance :MyApp
        lateinit var connectivityListener: ConnectivityListener
    }

    private val cicerone: Cicerone<Router> by lazy {Cicerone.create()}

    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    val database: GithubAppDb by lazy { GithubAppDb.getInstance(this) }
   // private lateinit var connectivityListener: ConnectivityListener


    override fun onCreate() {
        super.onCreate()
        instance = this
        connectivityListener = ConnectivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
        RxJavaPlugins.setErrorHandler {
        }
    }

//    fun getConnectObservable() = connectivityListener.status()
//    fun getConnectSingle() = connectivityListener.statusSingle()



}