package com.asif.freshnews.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.asif.freshnews.MainCoroutinesRule
import com.asif.freshnews.api.FreshNewsService
import com.asif.freshnews.db.ArticleDao
import com.asif.freshnews.db.FreshNewsDatabase
import com.asif.freshnews.utils.TestUtil

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Unit tests for the implementation of [FreshNewsRepository]
 */
@ExperimentalCoroutinesApi
class FreshNewsRepositoryTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FreshNewsRepository
    private lateinit var database : FreshNewsDatabase
    private lateinit var service: FreshNewsService

    @Before
    fun setUp() {
        database = mock(FreshNewsDatabase::class.java)
        service = mock(FreshNewsService::class.java)
        repository = FreshNewsRepository(service, database)
    }

    @Test
    fun getArticle_flowEmitsArticle() = runBlocking {
        //Stub out database to return a mock dao.
        val dao = mock(ArticleDao::class.java)
        `when`(database.articleDao()).thenReturn(dao)

        //Stub out dao to return an Article.
        val article = TestUtil.createArticle()
        `when`(dao.getNewsById("TEST_ID")).thenReturn(article)

        //Method under test.
        val flow = repository.getArticle("TEST_ID")

        //Verify data in the result.
        flow.collect { result ->
            assertThat(result, `is`(article))
        }
    }
}