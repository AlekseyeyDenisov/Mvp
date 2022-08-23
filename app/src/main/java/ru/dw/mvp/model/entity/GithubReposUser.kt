package ru.dw.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubReposUser(
    val id: Long,
    val name: String,
    val forksCount: Int
): Parcelable
