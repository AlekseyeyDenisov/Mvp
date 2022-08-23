package ru.dw.mvp.core.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReposDto (
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("forks_count")
    val forksCount: Int
)
