package ru.dw.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    var repos: List<GithubRepo>? = null


) : Parcelable
