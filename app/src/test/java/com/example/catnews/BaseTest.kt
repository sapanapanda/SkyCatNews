package com.example.catnews

import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTest {

//    // Set the main coroutines dispatcher for unit testing
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule =  MainCoroutineRule()

    //Execute each task synchronously using Architecture Components
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


}