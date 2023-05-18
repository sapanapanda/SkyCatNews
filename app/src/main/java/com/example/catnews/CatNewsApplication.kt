package com.example.catnews

import android.app.Application
import com.example.catnews.di.ApplicationComponent
import com.example.catnews.di.DaggerApplicationComponent


class CatNewsApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this).build()

    }
}