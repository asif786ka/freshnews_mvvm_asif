package com.asif.freshnews.api

import com.asif.freshnews.util.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points. Will fetch a list of news.
 */
interface FreshNewsService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "",
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("category") category: String =  "",
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 0
    ): NewssterResponse
}