package ru.dw.mvp.repository.impl


import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.mapper.ReposMapper
import ru.dw.mvp.core.mapper.UserMapper
import ru.dw.mvp.core.utils.doCompletableIf
import ru.dw.mvp.data.database.UserDAO
import ru.dw.mvp.data.network.UsersApi
import ru.dw.mvp.model.entity.GithubRepo
import ru.dw.mvp.model.entity.GithubUser
import ru.dw.mvp.repository.GithubRepository

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDAO

) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        // MediaStore.Images.Media.insertImage() /
        return MyApp.connectivityListener.statusSingle().flatMap { hasConnection ->
            if (hasConnection) {
                fetchFromApi(true)
            } else {
                getFromDb()
            }
        }

    }


    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .doCompletableIf(shouldPersist) {
                userDao.insertAll(
                    it.map(UserMapper::mapToDBObject)
                )
            }
            .map {
                it.map(UserMapper::mapToEntity)
            }

    }

    private fun getFromDb(): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(UserMapper::mapToEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GithubUser> {
        return userDao.getUserWithRepos(login)
            .map { userWithRepos ->
                val user = UserMapper.mapToEntity(userWithRepos.userDBObject)
                user.repos = userWithRepos.repos.map { ReposMapper.map(it) }
                user
            }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToEntity)
    }

    override fun getReposByLogin(login: String): Single<List<GithubRepo>> {
        return usersApi.getRepos(login)
            .map { it.map(ReposMapper::map) }
    }
}
