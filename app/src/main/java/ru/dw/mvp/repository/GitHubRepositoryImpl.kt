package ru.dw.mvp.repository

import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.core.UserMapper.UserMapper
import ru.dw.mvp.core.network.UsersApi
import ru.dw.mvp.model.GitHupRepository
import ru.dw.mvp.model.GithubUser

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi
) : GitHupRepository {


    override fun getUser(): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity) }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }
}


