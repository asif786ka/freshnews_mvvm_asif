package com.asif.freshnews.ui.article

import androidx.lifecycle.*
import com.asif.freshnews.model.Article
import com.asif.freshnews.repository.FreshNewsRepository
import com.asif.freshnews.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: FreshNewsRepository
) : ViewModel() {

    private val _articleId = MutableLiveData<String>()
    private val _shareArticleEvent = MutableLiveData<SingleEvent<String>>()
    private val _openWebsiteEvent = MutableLiveData<SingleEvent<String>>()

    val articleLiveData: LiveData<Article> = _articleId.switchMap { id->
        repository.getArticle(id).asLiveData()
    }

    val shareArticleEvent: LiveData<SingleEvent<String>>
        get() = _shareArticleEvent

    val openWebsiteEvent: LiveData<SingleEvent<String>>
        get() = _openWebsiteEvent


    fun fetchArticle(id: String) {
        _articleId.value = id
    }

    fun shareArticle(articleUrl: String) {
        _shareArticleEvent.value = SingleEvent(articleUrl)
    }

    fun openWebsite(articleUrl: String) {
        _openWebsiteEvent.value = SingleEvent(articleUrl)
    }
}