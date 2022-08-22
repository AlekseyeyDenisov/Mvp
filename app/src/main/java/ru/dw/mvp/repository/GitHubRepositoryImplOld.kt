package ru.dw.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.GithubUser

class GitHubRepositoryImplOld : GitHupRepository {

    private val repositories = listOf(
        GithubUser(1,"MrFox",""),
        GithubUser(2,"Victor",""),
        GithubUser(3,"Denis",""),
        GithubUser(4,"Mihail","")
    )


    override fun getUser(): Single<List<GithubUser>> {
        return Single.create {
            it.onSuccess(repositories)
        }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        TODO("Not yet implemented")
    }

}