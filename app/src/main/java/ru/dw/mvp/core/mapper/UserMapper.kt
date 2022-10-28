package ru.dw.mvp.core.mapper

import ru.dw.mvp.data.database.`object`.UserDBObject
import ru.dw.mvp.data.network.dto.UserDto
import ru.dw.mvp.model.entity.GithubUser
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun mapToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }

    fun mapToEntity(dbObject: UserDBObject): GithubUser {
        return GithubUser(
            id = dbObject.id,
            login = dbObject.login,
            avatarUrl = dbObject.avatarUrl
        )
    }

    fun mapToDBObject(dto: UserDto): UserDBObject {
        return UserDBObject(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }
}
