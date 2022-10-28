package ru.dw.mvp.data.UserMapper

import ru.dw.mvp.data.network.dto.RepoDto
import ru.dw.mvp.data.network.dto.UserDto
import ru.dw.mvp.model.entity.GithubRepo
import ru.dw.mvp.model.entity.GithubUser

object GithubMapper {
    fun mapUserDtoToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }

    fun mapReposDtoToEntity(dto: RepoDto): GithubRepo {
        return GithubRepo(
            id = dto.id,
            name = dto.name,
            forks = dto.forks

        )
    }

}