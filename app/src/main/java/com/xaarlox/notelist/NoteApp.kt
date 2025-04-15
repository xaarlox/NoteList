package com.xaarlox.notelist

import android.app.Application
import com.xaarlox.notelist.di.AppComponent
import com.xaarlox.notelist.di.DaggerAppComponent

class NoteApp : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}