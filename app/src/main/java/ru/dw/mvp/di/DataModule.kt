package ru.dw.mvp.di


import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.dw.mvp.data.database.GithubAppDb
import ru.dw.mvp.repository.GithubRepository
import ru.dw.mvp.repository.impl.GithubRepositoryImpl


@Module
interface DataModule {

    @Binds
    fun bindRepositoryList(impl: GithubRepositoryImpl): GithubRepository



    companion object {
        @Provides
        fun provideGithubDao(
            application: Application
        ): GithubAppDb {
            return GithubAppDb.getInstance(application)
        }
    }


}