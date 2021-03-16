package com.example.pmacademyandroid_metelov_m28_hw22.di

import android.content.Context
import com.example.pmacademyandroid_metelov_m28_hw22.presentation.newPostFragment.CreateNewPostFragment
import com.example.pmacademyandroid_metelov_m28_hw22.presentation.mainActivity.MainActivity
import com.example.pmacademyandroid_metelov_m28_hw22.presentation.feedFragment.ShowAllPostsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [],
    modules = [AppModule::class, RoomModule::class, DispatcherModule::class]
)
interface AppComponent {
    val context: Context

    fun inject(fragment: ShowAllPostsFragment)
    fun inject(fragment: CreateNewPostFragment)
    fun inject(activity: MainActivity)
}