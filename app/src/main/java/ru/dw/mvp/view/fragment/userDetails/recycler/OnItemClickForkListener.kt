package ru.dw.mvp.view.fragment.userDetails.recycler

import ru.dw.mvp.model.entity.GithubRepo

interface OnItemClickForkListener {
    fun onItemClick(githubForkUser: GithubRepo)
}