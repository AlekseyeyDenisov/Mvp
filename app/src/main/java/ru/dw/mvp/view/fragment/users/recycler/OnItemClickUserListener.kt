package ru.dw.mvp.view.fragment.users.recycler

import ru.dw.mvp.model.entity.GithubUser

interface OnItemClickUserListener {
    fun onItemClick(githubUser: GithubUser)
}