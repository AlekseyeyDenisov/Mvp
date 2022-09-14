package ru.dw.mvp.data.database.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithReposDBObject(
    @Embedded
    val userDBObject: UserDBObject,
    @Relation(
        parentColumn = UserDBObject.PRIMARY_KEY,
        entityColumn = RepoDBObject.FOREIGN_USER_KEY
    )
    val repos: List<RepoDBObject>
)
