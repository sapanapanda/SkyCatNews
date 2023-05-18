package com.example.catnews.viewModel

import androidx.lifecycle.Observer
import com.example.catnews.BaseTest
import com.example.catnews.network.response.CatNewsItem
import com.example.catnews.network.response.NewsListResponse
import com.example.catnews.network.storylist.NewsListService
import com.example.catnews.network.storylist.ViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verifyOrder
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class NewsListViewModelTest:BaseTest() {
    @MockK
    private lateinit var viewStateObserverMock: Observer<ViewState>

    @MockK
    private lateinit var newsListService: NewsListService


    private lateinit var viewModel: NewsListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        createViewModel()
    }

    @Test
    fun `getNewsList should emit loading and success response`() {
        val catNewsItem = CatNewsItem(
            "1", "headline", "teaser text", null, null, null,
            null, null, null, null
        )
        val newsListResponse = NewsListResponse("title", listOf(catNewsItem))
        coEvery { newsListService.getNewsList() } returns
                NewsListService.GetApiResult.Success(newsListResponse)

        viewModel.getNews()

        coVerify(exactly = 1) {
            newsListService.getNewsList()
        }

        val state1 = slot<ViewState>()
        val state2 = slot<ViewState>()
        verifyOrder {
            viewStateObserverMock.onChanged(capture(state1))
            viewStateObserverMock.onChanged(capture(state2))
        }
//
        assertEquals(ViewState.Loading, state1.captured)
        assertEquals(ViewState.Success(newsListResponse), state2.captured)
    }


    @Test
    fun `getNewsList should emit loading and error response`() {

        coEvery { newsListService.getNewsList() } returns
                NewsListService.GetApiResult.Error

        viewModel.getNews()

        coVerify(exactly = 1) {
            newsListService.getNewsList()
        }

        val state1 = slot<ViewState>()
        val state2 = slot<ViewState>()
        verifyOrder {
            viewStateObserverMock.onChanged(capture(state1))
            viewStateObserverMock.onChanged(capture(state2))
        }

        assertEquals(ViewState.Loading, state1.captured)
        assertEquals(ViewState.Error, state2.captured)
    }


    private fun createViewModel() {
        viewModel = NewsListViewModel(newsListService)

        viewModel.responseLiveData.observeForever(viewStateObserverMock)
    }

    @After
    fun tearDown() {
    }

}


