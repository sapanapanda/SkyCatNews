package com.example.catnews.network.storylist

import com.example.catnews.network.response.NewsListResponse
import retrofit2.http.GET


interface NewsListAPI {
    @GET("/news-list")
    suspend fun getNewsList(): NewsListResponse
}
