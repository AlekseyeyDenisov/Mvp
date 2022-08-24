package ru.dw.mvp.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDbEntity(
    @PrimaryKey
    val id: Long,
    val login: String,
    val avatarUrl: String?
)
