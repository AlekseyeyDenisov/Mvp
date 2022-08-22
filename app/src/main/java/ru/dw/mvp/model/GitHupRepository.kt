package ru.dw.mvp.model

import io.reactivex.rxjava3.core.Single

interface GitHupRepository {

    fun getUser(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>
}