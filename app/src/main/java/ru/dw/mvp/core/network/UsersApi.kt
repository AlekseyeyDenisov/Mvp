package ru.dw.mvp.core.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.dw.mvp.core.network.dto.ReposDto
import ru.dw.mvp.core.network.dto.UserDto

interface UsersApi {

    @GET("/users")
    fun getAllUsers(): Single<List<UserDto>>

//    @GET("/users/{login}")
//    fun getUser(@Path("login") login: String): Single<UserDto>

    @GET("/users/{login}/repos")
    fun getRepos(@Path("login") login: String): Single<List<ReposDto>>

}