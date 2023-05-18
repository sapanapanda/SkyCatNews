package com.example.catnews.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catnews.network.storydetail.StoryDetailService
import com.example.catnews.network.storydetail.StoryViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class StoryViewModel @Inject constructor(private val storyDetailService: StoryDetailService) :
    ViewModel() {

    private val _responseLiveData = MutableLiveData<StoryViewState>()

    val responseLiveData: LiveData<StoryViewState> = _responseLiveData

    fun getStory(id: String) {
        if (responseLiveData.value == StoryViewState.Loading) return

        viewModelScope.launch {
            updateStoryViewState(StoryViewState.Loading)
            when (val result = storyDetailService.getStoryDetail(id)) {
                is StoryDetailService.GetApiResult.Error -> {
                    updateStoryViewState(
                        StoryViewState.Error
                    )

                }

                is StoryDetailService.GetApiResult.Success -> {
                    updateStoryViewState(
                        StoryViewState.Success(result.response)
                    )
                }
            }
        }
    }

    private fun updateStoryViewState(StoryViewState: StoryViewState) {
        _responseLiveData.postValue(StoryViewState)
    }

}