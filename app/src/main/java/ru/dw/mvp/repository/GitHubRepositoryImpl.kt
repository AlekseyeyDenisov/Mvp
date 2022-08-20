package ru.dw.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.GithubUser

class GitHubRepositoryImpl : GitHupRepository {

    private val repositories = listOf(
        GithubUser("MrFox"),
        GithubUser("Victor"),
        GithubUser("Denis"),
        GithubUser("Mihail")
    )


    override fun getUser(): Single<List<GithubUser>> {
        return Single.create {
            it.onSuccess(repositories)
        }
    }
}