package ru.dw.mvp.model

import ru.dw.mvp.model.GithubUser

interface GitHupRepository {
    fun getUser():List<GithubUser>
}