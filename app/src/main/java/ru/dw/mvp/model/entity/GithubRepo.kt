package ru.dw.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepo(
    val id: Long,
    val name: String,
    val forks: Int
): Parcelable
