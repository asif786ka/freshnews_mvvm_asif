package com.asif.freshnews.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class LocalDatabase {

    lateinit var database: FreshNewsDatabase

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            FreshNewsDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }
}