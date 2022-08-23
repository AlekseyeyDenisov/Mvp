package ru.dw.mvp.view.fragment.userDetails.recycler

import ru.dw.mvp.model.entity.GithubReposUser

interface OnItemClickForkListener {
    fun onItemClick(githubForkUser: GithubReposUser)
}