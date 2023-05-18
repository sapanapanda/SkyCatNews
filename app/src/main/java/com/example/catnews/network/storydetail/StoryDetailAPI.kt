package com.example.catnews.network.storydetail

import com.example.catnews.network.response.CatStory
import retrofit2.http.GET
import retrofit2.http.Query

interface StoryDetailAPI {
    @GET("/story/")
    suspend fun getStoryDetail(@Query("id") id:String): CatStory
}
