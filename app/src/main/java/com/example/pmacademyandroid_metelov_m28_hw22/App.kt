package com.example.pmacademyandroid_metelov_m28_hw22

import android.app.Application
import com.example.pmacademyandroid_metelov_m28_hw22.di.*

class App : Application() {

    private lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .dispatcherModule(DispatcherModule)
            .build()
    }

    fun getComponent(): AppComponent {
        return daggerComponent
    }
}