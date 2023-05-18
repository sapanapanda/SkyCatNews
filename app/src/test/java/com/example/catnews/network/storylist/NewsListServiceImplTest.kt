package com.example.catnews.network.storylist

import com.example.catnews.BaseTest
import com.example.catnews.network.response.CatNewsItem
import com.example.catnews.network.response.NewsListResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class NewsListServiceImplTest:BaseTest() {

    @MockK
    private lateinit var apiMockk: NewsListAPI

    @MockK
    private lateinit var newsListResponse: NewsListResponse

    private lateinit var service: NewsListService

    private val catNewsItem = CatNewsItem(
        "1", "headline", "teaser text", null, null, null,
        null, null, null, null
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        service = NewsListServiceImpl(apiMockk, Dispatchers.Unconfined)
    }

    @Test
    fun `getNewsList returns success when  response is success`() {
        runTest  {

            coEvery { newsListResponse.title } returns "Cat News"
            coEvery { newsListResponse.data } returns listOf(catNewsItem)
            coEvery { apiMockk.getNewsList() } returns newsListResponse

            val result = service.getNewsList()

            val expectedResult = NewsListService.GetApiResult.Success(newsListResponse)

            assertEquals(result, expectedResult)
            assertEquals(
                (result as NewsListService.GetApiResult.Success).response.title,
                "Cat News"
            )
        }
    }

    @Test
    fun `getNewsList returns error when  response is error`() {
        runTest  {

            coEvery { apiMockk.getNewsList() } throws  IOException()

            val result = service.getNewsList()

            val expectedResult = NewsListService.GetApiResult.Error

            assertEquals(expectedResult, result)
        }
    }

    @After
    fun tearDown() {
    }
}