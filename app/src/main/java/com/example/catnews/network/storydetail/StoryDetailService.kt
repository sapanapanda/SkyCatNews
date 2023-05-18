package com.example.catnews.network.storydetail

import com.example.catnews.network.response.CatStory

interface StoryDetailService {
    suspend fun getStoryDetail(id:String): GetApiResult
    sealed class GetApiResult{
        data class Success(
           val response: CatStory
        ): GetApiResult()

        object  Error: GetApiResult()
    }
}