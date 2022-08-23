package ru.dw.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.core.UserMapper.GithubMapper
import ru.dw.mvp.core.network.UsersApi
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.model.entity.GithubUser

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi
) : GitHupRepository {


    override fun getUser(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(GithubMapper::mapUserDtoToEntity) }
    }

    override fun getForkByLogin(login: String): Single<List<GithubReposUser>> {
        return usersApi.getRepos(login)
            .map { it.map(GithubMapper::mapReposDtoToEntity) }
        //.map(UserMapper::mapToEntity)
    }
}


