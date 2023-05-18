package com.example.catnews.network.storydetail

import com.example.catnews.network.response.CatStory

sealed class StoryViewState {

    object Loading : StoryViewState()

    data class Success(val response: CatStory) : StoryViewState()

    object Error : StoryViewState()

}