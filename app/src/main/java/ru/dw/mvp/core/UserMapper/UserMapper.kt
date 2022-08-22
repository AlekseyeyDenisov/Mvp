package ru.dw.mvp.core.UserMapper

import ru.dw.mvp.core.network.UserDto
import ru.dw.mvp.model.GithubUser

object UserMapper {
    fun mapToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }

}