package ru.dw.mvp.core.utils


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject

class ConnectivityListener (private val connectivityManager: ConnectivityManager) {

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }


    private fun subject(): PublishSubject<Boolean> {
        val subject = PublishSubject.create<Boolean>()
        val request = NetworkRequest.Builder().build()
        connectivityManager.requestNetwork(request, object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                subject.onNext(true)
            }

            override fun onUnavailable() {
                subject.onNext(false)
            }

            override fun onLost(network: Network) {
                subject.onNext(false)
            }
        })
        return subject
    }

    fun status(): Observable<Boolean> = subject()

    fun statusSingle(): Single<Boolean> = subject().first(false)
}