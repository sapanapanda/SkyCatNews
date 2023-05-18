package com.example.catnews.network.storydetail

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoryDetailServiceImpl @Inject constructor(
    private val storyDetailAPI: StoryDetailAPI,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : StoryDetailService {

    override suspend fun getStoryDetail(id: String): StoryDetailService.GetApiResult =
        withContext(dispatcher) {
            try {
                val response = storyDetailAPI.getStoryDetail(id)
                StoryDetailService.GetApiResult.Success(response)
            } catch (exception: Exception) {
                StoryDetailService.GetApiResult.Error
            }
        }

}