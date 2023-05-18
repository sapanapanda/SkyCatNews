package com.example.catnews.di

import android.content.Context
import com.example.catnews.BuildConfig
import com.example.catnews.mock.MockInterceptor
import com.example.catnews.network.storydetail.StoryDetailAPI
import com.example.catnews.network.storylist.NewsListAPI
import com.example.catnews.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        return if (BuildConfig.FLAVOR == "mock") {
            OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .build()
        } else {
            OkHttpClient.Builder().build()
        }
    }

    @Provides
    fun provideMockInterceptor(context: Context): MockInterceptor {
        return MockInterceptor(context)
    }


    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): NewsListAPI {
        return retrofit.create(NewsListAPI::class.java)
    }

    @Provides
    fun providesStoryDetailService(retrofit: Retrofit): StoryDetailAPI {
        return retrofit.create(StoryDetailAPI::class.java)
    }
}