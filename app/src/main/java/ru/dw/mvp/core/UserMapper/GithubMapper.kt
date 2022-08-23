package ru.dw.mvp.core.UserMapper

import ru.dw.mvp.core.network.dto.ReposDto
import ru.dw.mvp.core.network.dto.UserDto
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.model.entity.GithubUser

object GithubMapper {
    fun mapUserDtoToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }

    fun mapReposDtoToEntity(dto: ReposDto): GithubReposUser {
        return GithubReposUser(
            id = dto.id,
            name = dto.name,
            forksCount = dto.forksCount

        )
    }

}