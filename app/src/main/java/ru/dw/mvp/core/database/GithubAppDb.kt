package ru.dw.mvp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataBaseDao::class], version = 1, exportSchema = false)
abstract class GithubAppDb:RoomDatabase()  {

    abstract fun dataBaseDao() :DataBaseDao

    companion object {
        private var db: GithubAppDb? = null
        private const val DB_MAME = "track_db"
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