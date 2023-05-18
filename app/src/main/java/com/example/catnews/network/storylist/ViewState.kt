package com.example.catnews.network.storylist

import com.example.catnews.network.response.NewsListResponse

sealed class ViewState {

    object Loading : ViewState()

    data class Success(val response: NewsListResponse) : ViewState()

    object Error : ViewState()

}