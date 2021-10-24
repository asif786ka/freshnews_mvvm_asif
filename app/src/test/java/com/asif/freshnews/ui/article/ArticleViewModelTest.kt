package com.asif.freshnews.ui.article

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.asif.freshnews.MainCoroutinesRule
import com.asif.freshnews.api.FreshNewsService
import com.asif.freshnews.db.FreshNewsDatabase
import com.asif.freshnews.repository.FreshNewsRepository
import com.asif.freshnews.utils.TestUtil
import com.asif.freshnews.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ArticleViewModelTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FreshNewsRepository
    private lateinit var viewModel: ArticleViewModel
    private val article = TestUtil.createArticle()
    private val database = mock(FreshNewsDatabase::class.java, RETURNS_DEEP_STUBS)
    private val service = mock(FreshNewsService::class.java)

    @Before
    fun setup() {
        repository = FreshNewsRepository(service, database)
        viewModel = ArticleViewModel(repository)
    }

    @Test
    fun fetchArticleTest() = runBlockingTest {
        `when`(database.articleDao().getNewsById("_testId")).thenReturn(article)
        viewModel.fetchArticle(id = "_testId")

        val value = viewModel.articleLiveData.getOrAwaitValue()
        assertThat(value.id, `is`(article.id))
        assertThat(value.title, `is`(article.title))
        assertThat(value.description, `is`(article.description))
        assertThat(value.author, `is`(article.author))
        assertThat(value.url, `is`(article.url))
        assertThat(value.source.name, `is`(article.source.name))
        assertThat(value.imgUrl, `is`(article.imgUrl))
        assertThat(value.category, `is`(article.category))
        assertThat(value.language, `is`(article.language))
        assertThat(value.date, `is`(article.date))
    }

    @Test
    fun shareArticleTest() {
        viewModel.shareArticle(article.url)
        val event = viewModel.shareArticleEvent.getOrAwaitValue()
        assertThat(event.getContentIfNotHandled(), `is`(article.url))
    }

    @Test
    fun openWebsiteTest() {
        viewModel.openWebsite(article.url)
        val event = viewModel.openWebsiteEvent.getOrAwaitValue()
        assertThat(event.getContentIfNotHandled(), `is`(article.url))
    }
}