package ru.dw.mvp.core.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDbEntity: UserDbEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userDbEntityList: List<UserDbEntity>):Completable

    @Query("SELECT * FROM user")
    fun getAllUser():Single<List<UserDbEntity>>

    @Delete
    fun deleteUser(userDbEntity: UserDbEntity):Completable


}