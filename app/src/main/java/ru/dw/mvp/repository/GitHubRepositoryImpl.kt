package ru.dw.mvp.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.GithubUser
import java.util.concurrent.TimeUnit

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