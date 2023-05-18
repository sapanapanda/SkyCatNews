package com.example.catnews.di

import android.app.Application
import android.content.Context
import com.example.catnews.CatNewsApplication
import com.example.catnews.network.storydetail.StoryDetailService
import com.example.catnews.network.storydetail.StoryDetailServiceImpl
import com.example.catnews.network.storylist.NewsListService
import com.example.catnews.network.storylist.NewsListServiceImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesServiceInteractor(catApiServiceInteractorImpl: NewsListServiceImpl): NewsListService {
        return catApiServiceInteractorImpl
    }

    @Singleton
    @Provides
    fun provideStoryDetailInteractor(storyDetailServiceInteractorImpl: StoryDetailServiceImpl): StoryDetailService {
        return storyDetailServiceInteractorImpl
    }

    @Singleton
    @Provides
    fun providesApplication(catNewsApplication: CatNewsApplication): Application {
        return catNewsApplication
    }

    @Singleton
    @Provides
    fun providesApplicationContext(catNewsApplication: CatNewsApplication): Context {
        return catNewsApplication.applicationContext
    }

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}