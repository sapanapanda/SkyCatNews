package com.example.catnews.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catnews.arch.ViewModelFactory
import com.example.catnews.viewModel.NewsListViewModel
import com.example.catnews.viewModel.StoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(NewsListViewModel::class)
    internal abstract fun bindCatsViewModel(newsListViewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(StoryViewModel::class)
    internal abstract fun bindStoryViewModel(storyViewModel: StoryViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}