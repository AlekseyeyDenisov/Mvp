package ru.dw.mvp.repository.impl


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.core.mapper.ReposMapper
import ru.dw.mvp.core.mapper.UserMapper
import ru.dw.mvp.core.utils.ConnectivityListener
import ru.dw.mvp.core.utils.doCompletableIf
import ru.dw.mvp.data.database.GithubAppDb
import ru.dw.mvp.data.network.NetworkProvider
import ru.dw.mvp.model.entity.GithubRepo
import ru.dw.mvp.model.entity.GithubUser
import ru.dw.mvp.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val networkProvider: NetworkProvider,
    private val githubAppDb: GithubAppDb,
    private val userMapper: UserMapper,
    private val reposMapper: ReposMapper,
    private val application: Application

) : GithubRepository {




    override fun getUsers(): Single<List<GithubUser>> {
        val networkStatus = ConnectivityListener(application. applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        Log.d("@@@", "getUsers networkStatus: ${networkStatus.isOnline(application)}")
        return if (networkStatus.isOnline(application)) {
            fetchFromApi(true)
        } else {
            getFromDb()
        }
    }


    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
        return networkProvider.usersApi.getAllUsers()
            .doCompletableIf(shouldPersist) {
                githubAppDb.dataBaseDao().insertAll(
                    it.map(userMapper::mapToDBObject)
                )
            }
            .map {
                it.map(userMapper::mapToEntity)
            }

    }

    private fun getFromDb(): Single<List<GithubUser>> {
        return githubAppDb.dataBaseDao().queryForAllUsers().map { it.map(userMapper::mapToEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GithubUser> {
        return githubAppDb.dataBaseDao().getUserWithRepos(login)
            .map { userWithRepos ->
                val user = userMapper.mapToEntity(userWithRepos.userDBObject)
                user.repos = userWithRepos.repos.map { reposMapper.map(it) }
                user
            }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return networkProvider.usersApi.getUser(login)
            .map(userMapper::mapToEntity)
    }

    override fun getReposByLogin(login: String): Single<List<GithubRepo>> {
        return networkProvider.usersApi.getRepos(login)
            .map { it.map(reposMapper::map) }
    }
}
