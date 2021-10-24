package com.asif.freshnews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asif.freshnews.model.Article
import com.asif.freshnews.model.SourceConverter

@Database(entities = [Article::class, RemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(value = [SourceConverter::class])
abstract class FreshNewsDatabase : RoomDatabase(){
    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}