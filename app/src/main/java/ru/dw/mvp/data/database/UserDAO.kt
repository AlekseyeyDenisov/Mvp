package ru.dw.mvp.data.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.dw.mvp.data.database.`object`.UserDBObject
import ru.dw.mvp.data.database.`object`.UserWithReposDBObject

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDBObject: UserDBObject): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userDBObject: List<UserDBObject>): Completable

    @Query("SELECT * FROM users")
    fun queryForAllUsers(): Single<List<UserDBObject>>

    @Delete
    fun delete(userDBObject: UserDBObject): Completable

    @Transaction
    @Query("SELECT * FROM users WHERE login = :login")
    fun getUserWithRepos(login: String): Single<UserWithReposDBObject>




}