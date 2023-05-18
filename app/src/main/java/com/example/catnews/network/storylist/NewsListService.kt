package com.example.catnews.network.storylist

import com.example.catnews.network.response.NewsListResponse

interface NewsListService {
    suspend fun getNewsList(): GetApiResult

    sealed class GetApiResult{
        data class Success(
           val response: NewsListResponse
        ): GetApiResult()

        object  Error: GetApiResult()
    }
}