package com.example.catnews.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catnews.network.response.NewsListResponse
import com.example.catnews.network.storylist.NewsListService
import com.example.catnews.network.storylist.ViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val newsListService: NewsListService) :
    ViewModel() {

    private val advert = "advert"

    private val _responseLiveData = MutableLiveData<ViewState>()

    val responseLiveData: LiveData<ViewState> = _responseLiveData

    fun getNews() {
        if (responseLiveData.value == ViewState.Loading) return

        viewModelScope.launch {
            updateViewState(ViewState.Loading)
            when (val result = newsListService.getNewsList()) {
                is NewsListService.GetApiResult.Error -> {
                    updateViewState(
                        ViewState.Error
                    )
                }

                is NewsListService.GetApiResult.Success -> {
                    val filteredResponse = filterResponse(result.response)
                    updateViewState(
                        ViewState.Success(filteredResponse)
                    )
                }
            }
        }
    }

    private fun filterResponse(response: NewsListResponse): NewsListResponse {
        val data = response.data.filter { it.type != advert }
        return response.copy(data = data)
    }

    private fun updateViewState(viewState: ViewState) {
        _responseLiveData.postValue(viewState)
    }

}