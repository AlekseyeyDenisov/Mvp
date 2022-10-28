package ru.dw.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.model.entity.GithubRepo
import ru.dw.mvp.model.entity.GithubUser

interface GithubRepository {

    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>

    fun getReposByLogin(login: String) : Single<List<GithubRepo>>
    fun getUserWithRepos(login: String): Single<GithubUser>

}