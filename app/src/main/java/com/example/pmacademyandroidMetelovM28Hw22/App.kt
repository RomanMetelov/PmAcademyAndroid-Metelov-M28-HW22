package com.example.pmacademyandroidMetelovM28Hw22

import android.app.Application
import com.example.pmacademyandroidMetelovM28Hw22.di.DispatcherModule
import com.example.pmacademyandroidMetelovM28Hw22.di.AppComponent
import com.example.pmacademyandroidMetelovM28Hw22.di.AppModule
import com.example.pmacademyandroidMetelovM28Hw22.di.DaggerAppComponent
import com.example.pmacademyandroidMetelovM28Hw22.di.RoomModule

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
