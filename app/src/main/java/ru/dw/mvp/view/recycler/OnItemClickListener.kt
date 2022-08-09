package ru.dw.mvp.view.recycler

import ru.dw.mvp.model.GithubUser

interface OnItemClickListener {
    fun onItemClick(githubUser: GithubUser)
}