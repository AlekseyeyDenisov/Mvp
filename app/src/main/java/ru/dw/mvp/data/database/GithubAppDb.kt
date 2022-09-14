package ru.dw.mvp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.dw.mvp.data.database.`object`.RepoDBObject
import ru.dw.mvp.data.database.`object`.UserDBObject

@Database(
    entities = [UserDBObject::class,RepoDBObject::class],
    version = 1,
    exportSchema = false)
abstract class GithubAppDb:RoomDatabase()  {

    abstract fun dataBaseDao() :UserDAO

    companion object {
        private var db: GithubAppDb? = null
        private const val DB_MAME = "git_db"
        private val LOCK = Any()

        fun getInstance(context: Context): GithubAppDb {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        GithubAppDb::class.java,
                        DB_MAME
                    )
                        .fallbackToDestructiveMigration()//не сохранять данные при миграции
                        .build()

                db = instance
                return instance
            }
        }
    }


}