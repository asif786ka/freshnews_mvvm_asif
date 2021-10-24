package com.asif.freshnews.di

import android.app.Application
import androidx.room.Room
import com.asif.freshnews.db.ArticleDao
import com.asif.freshnews.db.FreshNewsDatabase
import com.asif.freshnews.db.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): FreshNewsDatabase {
        return Room
            .databaseBuilder(app, FreshNewsDatabase::class.java, "newsster_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideArticleDao(freshNewsDatabase: FreshNewsDatabase): ArticleDao {
        return freshNewsDatabase.articleDao()
    }

    @Provides
    fun provideRemoteKeysDao(freshNewsDatabase: FreshNewsDatabase): RemoteKeyDao {
        return freshNewsDatabase.remoteKeyDao()
    }
}