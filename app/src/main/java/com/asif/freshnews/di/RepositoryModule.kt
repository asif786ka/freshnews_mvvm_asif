package com.asif.freshnews.di

import com.asif.freshnews.api.FreshNewsService
import com.asif.freshnews.db.FreshNewsDatabase
import com.asif.freshnews.repository.FreshNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewssterRepository(
        service: FreshNewsService,
        database: FreshNewsDatabase
    ): FreshNewsRepository {
        return FreshNewsRepository(service, database)
    }
}