package ru.dw.mvp.repository

import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.model.GitHupRepository

class GitHubRepositoryImpl: GitHupRepository {

    private val repositories = listOf(
        GithubUser("MrFox"),
        GithubUser("Victor"),
        GithubUser("Denis"),
        GithubUser("Mihail")
    )


    override fun getUser(): List<GithubUser> {
        return repositories
    }
}