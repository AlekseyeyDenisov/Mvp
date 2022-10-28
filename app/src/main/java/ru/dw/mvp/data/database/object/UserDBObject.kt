package ru.dw.mvp.data.database.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDBObject(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    val id: Long,
    val login: String,
    val avatarUrl: String?
) {

    companion object {
        const val PRIMARY_KEY = "id"
    }
}


