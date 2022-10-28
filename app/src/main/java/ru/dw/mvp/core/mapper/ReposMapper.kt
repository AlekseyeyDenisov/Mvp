package ru.dw.mvp.core.mapper

import ru.dw.mvp.data.database.`object`.RepoDBObject
import ru.dw.mvp.data.network.dto.RepoDto
import ru.dw.mvp.model.entity.GithubRepo
import javax.inject.Inject


class ReposMapper @Inject constructor() {

    fun map(repoDto: RepoDto): GithubRepo {
        return GithubRepo(
            id = repoDto.id,
            forks = repoDto.forks,
            name = repoDto.name
        )
    }

    fun map(repoDto: RepoDBObject): GithubRepo {
        return GithubRepo(
            id = repoDto.id,
            forks = repoDto.forks,
            name = repoDto.name
        )
    }
}
