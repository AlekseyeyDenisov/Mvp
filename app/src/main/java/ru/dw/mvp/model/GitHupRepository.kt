package ru.dw.mvp.model

import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.model.entity.GithubUser

interface GitHupRepository {

    fun getUser(): Single<List<GithubUser>>

    fun getForkByLogin(login: String): Single<List<GithubReposUser>>
}