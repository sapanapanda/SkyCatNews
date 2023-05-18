package com.example.catnews.network.storylist

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListServiceImpl @Inject constructor(
    private val apiService: NewsListAPI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    NewsListService {
    override suspend fun getNewsList(): NewsListService.GetApiResult = withContext(dispatcher) {
        try {
            val response = apiService.getNewsList()
            NewsListService.GetApiResult.Success(response)
        } catch (e: Exception) {
            NewsListService.GetApiResult.Error
        }
    }
}