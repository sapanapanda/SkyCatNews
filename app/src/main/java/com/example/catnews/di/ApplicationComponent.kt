package com.example.catnews.di

import com.example.catnews.CatNewsApplication
import com.example.catnews.ui.MainActivity
import com.example.catnews.ui.NewsListFragment
import com.example.catnews.ui.StoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: NewsListFragment)
    fun inject(fragment: StoryFragment)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: CatNewsApplication): Builder
    }
}