package ru.dw.mvp.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.mvp.BuildConfig
import javax.inject.Inject

class  NetworkProvider @Inject constructor() {

    val usersApi: UsersApi by lazy { createRetrofit().create(UsersApi::class.java) }

    private fun createGson() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private fun createRetrofit() = Retrofit.Builder()
        .client(createClient())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(createGson()))
        .build()

    private fun createClient() = OkHttpClient
        .Builder()
        .build()


}